package com.ute.category_management.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 200)
    private String name;

    private Double price;

    @Column(nullable = false)
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}
