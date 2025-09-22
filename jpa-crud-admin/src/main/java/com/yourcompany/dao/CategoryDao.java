package com.yourcompany.dao;

import com.yourcompany.entity.Category;
import com.yourcompany.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class CategoryDao extends AbstractDao<Category> {

    public CategoryDao() {
        super(Category.class);
    }

    /**
     * Tìm kiếm Category theo tên.
     * @param name Tên category cần tìm (có thể là một phần của tên).
     * @return Danh sách các Category phù hợp.
     */
    public List<Category> findByName(String name) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            String jpql = "SELECT c FROM Category c WHERE c.cateName LIKE :name";
            TypedQuery<Category> query = em.createQuery(jpql, Category.class);
            query.setParameter("name", "%" + name + "%");
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}