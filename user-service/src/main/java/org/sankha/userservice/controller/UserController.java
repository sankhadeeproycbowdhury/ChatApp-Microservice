package org.sankha.userservice.controller;

import org.sankha.userservice.dto.ResponseDto;
import org.sankha.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
@CrossOrigin(origins = "http://localhost:63342")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("getAll")
    public ResponseEntity<List<ResponseDto>> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("get/{id}")
    public ResponseEntity<ResponseDto> getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @GetMapping("find/{username}")
    public ResponseEntity<ResponseDto> getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @PostMapping("fetch")
    public ResponseEntity<List<ResponseDto>> fetchUsers(@RequestBody List<Integer> userIds) {
        return userService.getUsersById(userIds);
    }

    @PutMapping("update")
    public ResponseEntity<ResponseDto> updateUser(@RequestBody ResponseDto user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("remove")
    public ResponseEntity<String> removeUser(@RequestBody int userId) {
        return userService.removeUser(userId);
    }
}
