package org.sankha.chatservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Setter
@Getter
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name; // Group name or auto-generate for one-to-one chats.
    private boolean isGroup;

    @ElementCollection
    private List<Long> members; // List of Ids

    @OneToMany
    private List<Message> messages; // Messages belonging to this chat room.

    public ChatRoom() {}

    public ChatRoom(long id, String name, boolean group, List<Long> members, List<Message> messages) {
        this.id = id;
        this.name = name;
        this.isGroup = group;
        this.members = members;
        this.messages = messages;
    }
}
