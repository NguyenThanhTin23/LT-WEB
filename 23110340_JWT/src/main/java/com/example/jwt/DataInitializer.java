package com.example.jwt;
import com.example.jwt.entity.Role;
import com.example.jwt.entity.User;
import com.example.jwt.repository.RoleRepository;
import com.example.jwt.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner init(RoleRepository rr, UserRepository ur, PasswordEncoder pe){
        return args->{
            Role admin=rr.findByName("ROLE_ADMIN").orElseGet(()->rr.save(new Role(null,"ROLE_ADMIN")));
            Role user=rr.findByName("ROLE_USER").orElseGet(()->rr.save(new Role(null,"ROLE_USER")));
            ur.findByUsername("admin").orElseGet(()->{
                User u=new User();u.setUsername("admin");u.setEmail("admin@demo.com");u.setPassword(pe.encode("123456"));u.setRole(admin);return ur.save(u);
            });
            ur.findByUsername("user").orElseGet(()->{
                User u=new User();u.setUsername("user");u.setEmail("user@demo.com");u.setPassword(pe.encode("123456"));u.setRole(user);return ur.save(u);
            });
        };
    }
}
