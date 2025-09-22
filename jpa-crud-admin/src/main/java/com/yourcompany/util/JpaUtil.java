package com.yourcompany.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {
    private static final EntityManagerFactory factory;

    static {
        factory = Persistence.createEntityManagerFactory("my-persistence-unit");
    }

    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }
}