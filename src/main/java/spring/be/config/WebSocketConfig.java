package spring.be.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker // 메시지 브로커가 지원하는 웹소켓 활성화
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // 메시지를 구독(수신)하는 요청의 prefix.
        // /topic으로 시작하는 메시지는 브로커가 가로채서 구독자들에게 뿌려줌
        config.enableSimpleBroker("/topic");

        // 클라이언트에서 서버로 메시지를 보낼 때 붙이는 prefix.
        // /app/chat 이런 식으로 보냄
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 클라이언트가 웹소켓 서버에 연결할 엔드포인트. ws://localhost:8080/ws 로 연결하게 됨
        // 1. 엔드포인트: /ws
        // 2. CORS: 모든 도메인 허용
        // 3. SockJS: 웹소켓을 지원하지 않는 브라우저나 환경을 위한 설정
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("*") // CORS 허용
                // 프론트엔드 코드에서 http://localhost:8080/ws로 연결 요청을 보내도,
                // 내부적으로 핸드쉐이크를 통해 ws://(웹소켓) 연결로 업그레이드해주거나
                // 웹소켓을 지원하지 않는 환경(IE, 방화벽 등)이면 http 기반의 다른 통신 방식(Long Polling 등)으로 대체하여 연결함
                .withSockJS(); // SockJS 지원 (웹소켓 지원 안하는 브라우저를 위해)
    }
}
