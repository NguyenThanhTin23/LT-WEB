package com.example.jwt.service;
import com.example.jwt.entity.Role;
import com.example.jwt.entity.User;
import com.example.jwt.repository.RoleRepository;
import com.example.jwt.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;
@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    public UserService(UserRepository userRepository,RoleRepository roleRepository,PasswordEncoder encoder){
        this.userRepository=userRepository;this.roleRepository=roleRepository;this.encoder=encoder;
    }
    public User register(String username,String email,String password,String roleName){
        if(userRepository.existsByUsername(username)) throw new RuntimeException("exists");
        Role role=roleRepository.findByName(roleName).orElseGet(()->roleRepository.save(new Role(null,roleName)));
        User u=new User();u.setUsername(username);u.setEmail(email);u.setPassword(encoder.encode(password));u.setRole(role);
        return userRepository.save(u);
    }
    public Optional<User> findByUsername(String username){return userRepository.findByUsername(username);}
}
