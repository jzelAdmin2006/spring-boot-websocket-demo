package com.jzel.springbootwebsocketdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class WebSocketController {

    private final SimpMessagingTemplate template;

    @Autowired
    public WebSocketController(final SimpMessagingTemplate template) {
        this.template = template;
    }

    @PostMapping("/send")
    public void sendMsgToWebSocket(@RequestBody final String message) {
        this.template.convertAndSend("/topic/message", message);
    }

    @MessageMapping("/send/message")
    @SendTo("/topic/message")
    public String broadcastMessage(final String message) {
        return message;
    }
}
