package com.example.jwt.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name="Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false,unique=true,length=50)
    private String username;
    @Column(length=100)
    private String email;
    @Column(nullable=false,length=200)
    private String password;
    @ManyToOne
    @JoinColumn(name="role_id")
    private Role role;
}
