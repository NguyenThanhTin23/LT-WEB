package com.yourcompany.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cate_id")
    private int cateId;

    @Column(name = "cate_name", nullable = false)
    private String cateName;

    @OneToMany(mappedBy = "category")
    private List<Video> videos;

    // Getters and Setters
}