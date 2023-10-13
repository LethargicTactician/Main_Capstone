package capstone.chatservice.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConfig implements WebSocketMessageBrokerConfigurer {

    //message broker registry
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
    //setting up topics and general registry
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/chatroom", "/user");
        registry.setUserDestinationPrefix("/user");

    }

    //setting up endpoints - this is to be able to have two users talk to eachother
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //enabling cross-origin to be able to pick up previous conversations
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS();


    }
}
