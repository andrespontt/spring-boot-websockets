package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebSocketTextController {

    private final static Logger log = LoggerFactory.getLogger(WebSocketTextController.class);

    @Autowired
    private SimpMessagingTemplate template;

    @PostMapping("/send") 
    public ResponseEntity<Void> sendMessage(
        @RequestBody TextMessage textMessage
    ) {
        template.convertAndSend("/topic/message", textMessage);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @MessageMapping("/sendMessage")
    public void receiveMessage(@Payload TextMessage textMessage)  {
        log.info("receiveMessage: {}", textMessage);
    }

    @SendTo("/topic/message")
    public TextMessage broadcaMessage(@Payload TextMessage textMessage) {
        return textMessage;
    }
}
