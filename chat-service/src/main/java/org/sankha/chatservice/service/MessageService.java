package org.sankha.chatservice.service;

import jakarta.transaction.Transactional;
import org.sankha.chatservice.dto.MessageDto;
import org.sankha.chatservice.model.ChatRoom;
import org.sankha.chatservice.model.Message;
import org.sankha.chatservice.repository.ChatRoomRepo;
import org.sankha.chatservice.repository.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class MessageService {
    @Autowired
    MessageRepo messageRepo;

    @Autowired
    ChatRoomRepo chatRoomRepo;

    public ResponseEntity<List<Message>> getMessages(Long id) {
        return ResponseEntity.ok(messageRepo.findByChatRoomIdOrderByTimestampAsc(id));
    }

    public void saveMessage(MessageDto message) {
        ChatRoom room = chatRoomRepo.findById(message.getRoomId()).get();
        if(!room.getMembers().contains(message.getSenderId()))
            throw new RuntimeException("User must be a member of the chat room to send messages");

        Message m = Message.builder()
                .senderId(message.getSenderId())
                .timestamp(message.getTimestamp())
                .content(message.getContent())
                .chatRoom(room)
                .build();

        messageRepo.save(m);
    }
}
