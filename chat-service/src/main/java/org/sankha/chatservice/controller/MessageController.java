package org.sankha.chatservice.controller;

import org.sankha.chatservice.dto.MessageDto;
import org.sankha.chatservice.model.Message;
import org.sankha.chatservice.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("message")
@CrossOrigin(origins = "http://localhost:63342")
public class MessageController {
    @Autowired
    SimpMessagingTemplate messagingTemplate;

    @Autowired
    MessageService messageService;

    @GetMapping("get/{id}")
    public ResponseEntity<List<Message>> getMessage(@PathVariable("id") Long roomId) {
        return messageService.getMessages(roomId);
    }

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload MessageDto message) {
        messagingTemplate.convertAndSend("/topic/room/" + message.getRoomId(), message);
        messageService.saveMessage(message);
    }
}
