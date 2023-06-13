package ru.otus.goppeav.rumascot.springapp.api.v1.websocket

import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry

@Configuration
@EnableWebSocket
class WsSocketConfigV1(val handlerV1: WsAdHandlerV1): WebSocketConfigurer {
    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(handlerV1, "/ws/v1").setAllowedOrigins("*")
    }
}