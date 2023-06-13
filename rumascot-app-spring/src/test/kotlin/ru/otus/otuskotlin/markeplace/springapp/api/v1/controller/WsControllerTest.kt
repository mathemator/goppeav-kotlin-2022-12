package ru.otus.goppeav.rumascot.springapp.api.v1.controller

import io.kotest.common.runBlocking
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import ru.otus.goppeav.rumascot.api.v1.apiV1Mapper
import ru.otus.goppeav.rumascot.api.v1.models.*
import java.net.URI
import javax.websocket.*


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WsControllerTest {

    @LocalServerPort
    private var port: Int = 0

    private lateinit var container: WebSocketContainer
    private lateinit var client: TestWebSocketClient

    @BeforeEach
    fun setup() {
        container = ContainerProvider.getWebSocketContainer()
        client = TestWebSocketClient()
    }

    @Test
    fun initSession() {
        runBlocking {
            withContext(Dispatchers.IO) {
                container.connectToServer(client, URI.create("ws://localhost:${port}/ws/v1"))
            }

            withTimeout(3000) {
                while (client.session?.isOpen != true) {
                    delay(100)
                }
            }
            assert(client.session?.isOpen == true)
            withTimeout(3000) {
                val response = client.receive()
                val message = apiV1Mapper.readValue(response, IResponse::class.java)
                assert(message is AdInitResponse)
                assert(message.result == ResponseResult.SUCCESS)
            }
        }
    }

    @Test
    fun response() {
        runBlocking {
            withContext(Dispatchers.IO) {
                container.connectToServer(client, URI.create("ws://localhost:${port}/ws/v1"))
            }

            withTimeout(3000) {
                while (client.session?.isOpen != true) {
                    delay(100)
                }
            }
            assert(client.session?.isOpen == true)

            withTimeout(30000) {
                client.receive() // игнорируем инит
                val request = apiV1Mapper.writeValueAsString(AdReadRequest(requestId = "1234", requestType = "read"))
                client.session?.basicRemote?.sendText(request)

                val response = client.receive()
                val responseMessage = apiV1Mapper.readValue(response, IResponse::class.java)
                assert(responseMessage is AdReadResponse)
                assert(responseMessage.result == ResponseResult.SUCCESS)

                val adReadResponse = responseMessage as AdReadResponse
                assertEquals("Фигурка Java Duke", adReadResponse.ad?.title)
                assertEquals(DealSide.PROPOSAL, adReadResponse.ad?.dealSide)
            }
        }
    }
}

@ClientEndpoint
class TestWebSocketClient {
    var session: Session? = null
    private val messages: MutableList<String> = mutableListOf()

    @OnOpen
    fun onOpen(session: Session?) {
        this.session = session
    }

    @OnClose
    fun onClose(session: Session?, reason: CloseReason) {
        println("Session is closed due to ${reason.reasonPhrase}")
        this.session = null
    }

    @OnMessage
    fun onMessage(message: String) {
        println("Received text message: $message")
        messages.add(message)
    }

    suspend fun receive(): String {
        while (messages.isEmpty()) {
            delay(100)
        }
        return messages.removeFirst()
    }
}
