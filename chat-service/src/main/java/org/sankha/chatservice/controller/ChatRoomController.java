package org.sankha.chatservice.controller;

import org.sankha.chatservice.dto.ChatRoomDto;
import org.sankha.chatservice.model.ChatRoom;
import org.sankha.chatservice.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("chatroom")
@CrossOrigin(origins = "http://localhost:63342")
public class ChatRoomController {
    @Autowired
    ChatRoomService chatRoomService;

    @GetMapping("get")
    public ResponseEntity<List<ChatRoomDto>> getRooms() {
        return chatRoomService.getRooms();
    }

    @GetMapping("get/{id}")
    public ResponseEntity<ChatRoom> getRoom(@PathVariable long id) {
        return chatRoomService.getRoomById(id);
    }

    @GetMapping("find/{name}")
    public ResponseEntity<ChatRoom> findRoom(@PathVariable String name) {
        return chatRoomService.getRoomByName(name);
    }

    @PostMapping("add")
    public ResponseEntity<ChatRoom> addRoom(@RequestBody ChatRoomDto chatRoom) {
        return chatRoomService.createChatRoom(chatRoom);
    }

    @PostMapping("addMember")
    public ResponseEntity<String> addMember(@RequestBody Long memberId, @RequestBody String roomName) {
        return chatRoomService.addMember(memberId, roomName);
    }
}
