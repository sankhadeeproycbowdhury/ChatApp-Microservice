package org.sankha.chatservice.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long senderId;
    private String content;
    private LocalDateTime timestamp;

    @ManyToOne
    private ChatRoom chatRoom; // Reference to the chat room.

    public Message() {}

    public Message(Long id, Long senderId, String content, LocalDateTime timestamp, ChatRoom chatRoom) {
        this.id = id;
        this.senderId = senderId;
        this.content = content;
        this.timestamp = timestamp;
        this.chatRoom = chatRoom;
    }
}
