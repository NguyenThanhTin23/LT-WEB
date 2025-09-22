package com.yourcompany.dao;

import com.yourcompany.entity.Video;
import com.yourcompany.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class VideoDao extends AbstractDao<Video> {

    public VideoDao() {
        super(Video.class);
    }

    /**
     * Tìm kiếm Video theo tiêu đề.
     * @param title Tiêu đề video cần tìm.
     * @return Danh sách các Video phù hợp.
     */
    public List<Video> findByTitle(String title) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            String jpql = "SELECT v FROM Video v WHERE v.title LIKE :title";
            TypedQuery<Video> query = em.createQuery(jpql, Video.class);
            query.setParameter("title", "%" + title + "%");
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}