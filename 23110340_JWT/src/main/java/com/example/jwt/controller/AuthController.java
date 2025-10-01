package com.example.jwt.controller;
import com.example.jwt.entity.User;
import com.example.jwt.security.JwtService;
import com.example.jwt.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager am;
    private final JwtService jwtService;
    private final UserService userService;
    public AuthController(AuthenticationManager am,JwtService jwtService,UserService userService){
        this.am=am;this.jwtService=jwtService;this.userService=userService;
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String,String> body){
        String username=body.getOrDefault("username","");
        String email=body.getOrDefault("email","");
        String password=body.getOrDefault("password","");
        String role=body.getOrDefault("role","ROLE_USER");
        User u=userService.register(username,email,password,role);
        return ResponseEntity.ok(Map.of("id",u.getId(),"username",u.getUsername(),"role",u.getRole().getName()));
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String,String> body){
        Authentication auth=am.authenticate(new UsernamePasswordAuthenticationToken(body.get("username"),body.get("password")));
        SecurityContextHolder.getContext().setAuthentication(auth);
        String token=jwtService.generate((UserDetails)auth.getPrincipal());
        return ResponseEntity.ok(Map.of("token",token,"type","Bearer"));
    }
}
