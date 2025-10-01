package com.example.jwt.controller;
import com.example.jwt.entity.User;
import com.example.jwt.repository.UserRepository;
import com.example.jwt.security.AppUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository repo;
    public UserController(UserRepository repo){this.repo=repo;}
    @GetMapping("/me")
    public ResponseEntity<?> me(@AuthenticationPrincipal UserDetails ud){
        if(ud instanceof AppUserDetails a){User u=a.getUser();return ResponseEntity.ok(Map.of("id",u.getId(),"username",u.getUsername(),"role",u.getRole().getName()));}
        return ResponseEntity.ok(Map.of("username",ud.getUsername()));
    }
}
