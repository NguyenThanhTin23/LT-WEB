package com.yourcompany.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Video")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "video_id")
    private int videoId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "poster")
    private String poster;

    @Column(name = "views")
    private int views = 0;

    @Column(name = "description")
    private String description;

    @Column(name = "is_active")
    private boolean isActive = true;

    @Column(name = "created_at")
    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "cate_id")
    private Category category;

	public void setCategory(Category byId) {
		// TODO Auto-generated method stub

	}

	public void setUser(User byId) {
		// TODO Auto-generated method stub

	}

    // Getters and Setters
}