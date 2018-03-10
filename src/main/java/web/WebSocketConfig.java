package web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import tcp_ip.Constants;
import web.sockethandlers.AgentSocketHandler;
import web.sockethandlers.UsersSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Bean
    Constants getConstants(){
        return new Constants();
    }
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new UsersSocketHandler(), "/usersocket");
        registry.addHandler(new AgentSocketHandler(), "/agentsocket");
    }
}