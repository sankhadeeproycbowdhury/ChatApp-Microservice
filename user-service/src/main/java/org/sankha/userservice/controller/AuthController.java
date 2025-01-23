package org.sankha.userservice.controller;

import org.sankha.userservice.dto.AuthResponseDto;
import org.sankha.userservice.dto.LoginDto;
import org.sankha.userservice.dto.SignupDto;
import org.sankha.userservice.model.Role;
import org.sankha.userservice.model.User;
import org.sankha.userservice.repository.RoleRepo;
import org.sankha.userservice.repository.UserRepo;
import org.sankha.userservice.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("auth")
@CrossOrigin(origins = "http://localhost:63342")
public class AuthController {
    @Autowired
     UserRepo userRepo;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("signup")
    public ResponseEntity<String> signup(@RequestBody SignupDto user) {
        if(userRepo.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setEmail(user.getEmail());
        newUser.setOnline(false);

        Role userRole = roleRepo.findByName("USER");
        newUser.setRoles(Collections.singletonList(userRole));

        userRepo.save(newUser);
        return ResponseEntity.ok("New User created !!");
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto user) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = JwtService.generateToken(user.getUsername());
            userRepo.setOnlineByUsername(user.getUsername(), true);
            return ResponseEntity.ok(new AuthResponseDto(true, "Login successful", token));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(new AuthResponseDto(false, e.getMessage(), null));
        }
    }

    @GetMapping("logout/{username}")
    public ResponseEntity<String> logout(@PathVariable String username) {
        userRepo.setOnlineByUsername(username, false);
        return ResponseEntity.ok("Logout successful");
    }
}
