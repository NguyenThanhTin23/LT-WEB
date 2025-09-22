package com.yourcompany.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "[User]") // 'User' là từ khóa trong SQL, nên đặt trong dấu ngoặc vuông
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "is_admin")
    private boolean isAdmin;

    @OneToMany(mappedBy = "user")
    private List<Video> videos;

    // Getters and Setters
}