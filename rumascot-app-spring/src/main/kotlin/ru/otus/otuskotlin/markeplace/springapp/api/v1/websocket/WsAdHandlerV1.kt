package ru.otus.goppeav.rumascot.springapp.api.v1.websocket

import kotlinx.coroutines.runBlocking
import kotlinx.datetime.Clock
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import ru.otus.goppeav.rumascot.api.v1.apiV1Mapper
import ru.otus.goppeav.rumascot.api.v1.models.IRequest
import ru.otus.goppeav.rumascot.common.ContextRum
import ru.otus.goppeav.rumascot.mappers.v1.fromTransport
import ru.otus.goppeav.rumascot.mappers.v1.toTransportAd
import ru.otus.goppeav.rumascot.mappers.v1.toTransportInit
import ru.otus.goppeav.rumascot.stubs.RumAdStub
import ru.otus.goppeav.rumascot.common.helpers.asRumError
import ru.otus.goppeav.rumascot.common.helpers.isUpdatableCommand
import ru.otus.goppeav.rumascot.common.models.StateRum

@Component
class WsAdHandlerV1 : TextWebSocketHandler() {

    private val sessions = mutableMapOf<String, WebSocketSession>()

    override fun afterConnectionEstablished(session: WebSocketSession) {
        sessions.put(session.id, session)

        val contextRum = ContextRum()

        val init = apiV1Mapper.writeValueAsString(contextRum.toTransportInit())
        session.sendMessage(TextMessage(init))
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val ctx = ContextRum(timeStart = Clock.System.now())
        runBlocking {
            try {
                val request = apiV1Mapper.readValue(message.payload, IRequest::class.java)
                ctx.fromTransport(request)

                //todo logic
                ctx.state = StateRum.RUNNING
                ctx.adResponse = RumAdStub.get()

                val result = apiV1Mapper.writeValueAsString(ctx.toTransportAd())
                if (ctx.isUpdatableCommand()) {
                    sessions.values.forEach {
                        it.sendMessage(TextMessage(result))
                    }
                } else {
                    session.sendMessage(TextMessage(result))
                }
            } catch (e: Exception) {
                ctx.errors.add(e.asRumError())

                val response = apiV1Mapper.writeValueAsString(ctx.toTransportInit())
                session.sendMessage(TextMessage(response))
            }
        }
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        sessions.remove(session.id)
    }
}