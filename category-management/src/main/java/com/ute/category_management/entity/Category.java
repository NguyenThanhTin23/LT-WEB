package com.ute.category_management.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data // Lombok: Tự động tạo getter, setter, constructor...
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, length = 100, unique = true)
    private String name;

    @Column(name = "status")
    private boolean status = true; // Ví dụ: true = Active, false = Inactive

    public Integer getId() {
        return this.id;
    }
}
