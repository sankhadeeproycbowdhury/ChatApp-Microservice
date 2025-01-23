package org.sankha.chatservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChatRoomDto {
    private String groupName;
    private boolean group;
    private List<Long> members;

    public ChatRoomDto() {}

    public ChatRoomDto(String groupName, boolean group, List<Long> members) {
        this.groupName = groupName;
        this.group = group;
        this.members = members;
    }
}
