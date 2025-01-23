package org.sankha.userservice.service;

import org.sankha.userservice.dto.ResponseDto;
import org.sankha.userservice.repository.UserRepo;
import org.sankha.userservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;


    public ResponseEntity<List<ResponseDto>> getAllUsers() {
        List<ResponseDto> users = new ArrayList<>();
        for (User user : userRepo.findAll()) {
            users.add(new ResponseDto(user.getId(), user.getUsername(), user.getEmail(), user.isOnline()));
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    public ResponseEntity<ResponseDto> getUserById(int id) {
        User user = userRepo.findById(id).get();
        return new ResponseEntity<>(new ResponseDto(user.getId(), user.getUsername(), user.getEmail(), user.isOnline()), HttpStatus.OK);
    }


    public ResponseEntity<ResponseDto> updateUser(ResponseDto user) {
        User updatedUser = userRepo.findById(user.getId()).get();
        updatedUser.setUsername(user.getUsername());
        updatedUser.setEmail(user.getEmail());
        userRepo.save(updatedUser);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    public ResponseEntity<String> removeUser(int userId) {
        userRepo.deleteById(userId);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }


    public ResponseEntity<ResponseDto> getUserByUsername(String username) {
        User user = userRepo.findByUsername(username);
        return new ResponseEntity<>(new ResponseDto(user.getId(), user.getUsername(), user.getEmail(), user.isOnline()),HttpStatus.OK);
    }

    public ResponseEntity<List<ResponseDto>> getUsersById(List<Integer> userIds) {
        List<ResponseDto> users = new ArrayList<>();
        for (Integer id : userIds) {
            User u = userRepo.findById(id).get();
            users.add(new ResponseDto(u.getId(), u.getUsername(), u.getEmail(), u.isOnline()));
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
