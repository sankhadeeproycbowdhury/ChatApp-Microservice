package org.sankha.chatservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MessageDto {
    private Long senderId;
    private String content;
    private LocalDateTime timestamp;
    private Long roomId;

    public MessageDto() {}

    public MessageDto(Long senderId, String content, LocalDateTime timestamp, Long roomId) {
        this.senderId = senderId;
        this.content = content;
        this.timestamp = timestamp;
        this.roomId = roomId;
    }
}
