package com.example.jwt.service;
import com.example.jwt.entity.User;
import com.example.jwt.repository.UserRepository;
import com.example.jwt.security.AppUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class AppUserDetailsService implements UserDetailsService {
    private final UserRepository repo;
    public AppUserDetailsService(UserRepository repo){this.repo=repo;}
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u=repo.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("not found"));
        return new AppUserDetails(u);
    }
}
