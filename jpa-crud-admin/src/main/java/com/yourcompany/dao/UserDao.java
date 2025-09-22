package com.yourcompany.dao;

import com.yourcompany.entity.User;
import com.yourcompany.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class UserDao extends AbstractDao<User> {

    public UserDao() {
        super(User.class);
    }

    /**
     * Tìm kiếm User theo username hoặc email.
     * @param keyword Từ khóa tìm kiếm.
     * @return Danh sách User phù hợp.
     */
    public List<User> findByUsernameOrEmail(String keyword) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            String jpql = "SELECT u FROM User u WHERE u.username LIKE :keyword OR u.email LIKE :keyword";
            TypedQuery<User> query = em.createQuery(jpql, User.class);
            query.setParameter("keyword", "%" + keyword + "%");
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}