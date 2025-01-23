package org.sankha.chatservice.repository;

import org.sankha.chatservice.model.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRoomRepo extends JpaRepository<ChatRoom, Long> {
    ChatRoom findByName(String name);
}
