package com.dca.spring.src.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/subscribe/weblog-list")
    @SendTo("/topic/weblog-list")
    public String subscribeWeblogList(String message) {
        return message;
    }
}
