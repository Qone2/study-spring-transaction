package com.example.spring_transaction.user;

import com.example.spring_transaction.user.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Repository
public class UserRepositoryEm {

    private final EntityManager em;

    public UserRepositoryEm(EntityManagerFactory entityManagerFactory) {
        this.em = entityManagerFactory.createEntityManager();
    }


    public void save(User user) {
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    public long count() {
        return em.createQuery("SELECT COUNT(u) FROM User u", Long.class).getSingleResult();
    }

    public User getUserById(Long id) {
        return em.find(User.class, id);
    }
}
