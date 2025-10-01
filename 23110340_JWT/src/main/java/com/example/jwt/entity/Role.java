package com.example.jwt.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name="Roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false,unique=true,length=50)
    private String name;
}
