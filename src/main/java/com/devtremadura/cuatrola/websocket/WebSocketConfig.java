package com.devtremadura.cuatrola.websocket;

import com.devtremadura.cuatrola.websocket.handlers.CuatrolaWSHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    final CuatrolaWSHandler cuatrolaWSHandler;

    public WebSocketConfig(CuatrolaWSHandler cuatrolaWSHandler){
        this.cuatrolaWSHandler = cuatrolaWSHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(cuatrolaWSHandler, "/ws/{roomCode}/*")
                .setAllowedOrigins("*").addInterceptors(handShakeInterceptor());
    }

    private HandshakeInterceptor handShakeInterceptor() {
        return new HandshakeInterceptor() {
            @Override
            public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
                request.getHeaders().get("X-USER-ID");

                return true;
            }

            @Override
            public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

            }
        };
    }
}
