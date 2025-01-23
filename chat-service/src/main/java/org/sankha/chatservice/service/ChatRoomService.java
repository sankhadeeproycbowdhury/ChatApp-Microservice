package org.sankha.chatservice.service;

import org.sankha.chatservice.dto.ChatRoomDto;
import org.sankha.chatservice.model.ChatRoom;
import org.sankha.chatservice.repository.ChatRoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatRoomService {
    @Autowired
    ChatRoomRepo chatRoomRepo;

    public ResponseEntity<List<ChatRoomDto>> getRooms() {
        List<ChatRoomDto> rooms = new ArrayList<>();
        for (ChatRoom room : chatRoomRepo.findAll()) {
            rooms.add(new ChatRoomDto(room.getName(), room.isGroup(), room.getMembers()));
        }
        return ResponseEntity.ok(rooms);
    }

    public ResponseEntity<ChatRoom> getRoomById(Long roomId) {
        return ResponseEntity.ok(chatRoomRepo.findById(roomId).get());
    }

    public ResponseEntity<ChatRoom> getRoomByName(String name) {
        return ResponseEntity.ok(chatRoomRepo.findByName(name));
    }

    public ResponseEntity<ChatRoom> createChatRoom(ChatRoomDto chatRoom) {
        ChatRoom room = chatRoomRepo.findByName(chatRoom.getGroupName());
        if (room != null) {
            List<Long> members = room.getMembers();
            if(!members.contains(chatRoom.getMembers().get(0))){
                members.add(chatRoom.getMembers().get(0));
                room.setMembers(members);
                return ResponseEntity.ok(chatRoomRepo.save(room));
            }
            return ResponseEntity.ok(room);
        }

        ChatRoom newRoom = chatRoomRepo.findByName(new StringBuilder(chatRoom.getGroupName()).reverse().toString());
        if (newRoom != null){
            List<Long> members = newRoom.getMembers();
            if(!members.contains(chatRoom.getMembers().get(0))) {
                members.add(chatRoom.getMembers().get(0));
                newRoom.setMembers(members);
                return ResponseEntity.ok(chatRoomRepo.save(newRoom));
            }
            return ResponseEntity.ok(newRoom);
        }

        ChatRoom chatRoomEntity = new ChatRoom();
        chatRoomEntity.setGroup(chatRoom.isGroup());
        chatRoomEntity.setName(chatRoom.getGroupName());
        chatRoomEntity.setMembers(new ArrayList<>(chatRoom.getMembers()));

        return ResponseEntity.ok(chatRoomRepo.save(chatRoomEntity));
    }

    public ResponseEntity<String> addMember(Long memberId, String roomName) {
        ChatRoom chatRoom = chatRoomRepo.findByName(roomName);
        if (chatRoom == null)
            return ResponseEntity.notFound().build();

        List<Long> members = chatRoom.getMembers();
        if(!chatRoom.isGroup() || members.size() < 2) members.add(memberId);
        chatRoom.setMembers(members);
        chatRoomRepo.save(chatRoom);
        return ResponseEntity.ok("Member added Successfully");
    }
}
