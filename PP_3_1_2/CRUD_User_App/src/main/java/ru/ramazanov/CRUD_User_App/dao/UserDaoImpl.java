package ru.ramazanov.CRUD_User_App.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.ramazanov.CRUD_User_App.models.User;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserDaoImpl implements UserDao {
    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public UserDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    @Transactional()
    public void addUser(User user) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional()
    public void deleteUserById(int id) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            User user = entityManager.find(User.class, id);
            entityManager.remove(user);
            entityManager.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional()
    public void updateUserById(int id, User user) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            User updatedUser = entityManager.find(User.class, id);
            updatedUser.setName(user.getName());
            updatedUser.setLastName(user.getLastName());
            updatedUser.setAge(user.getAge());
            updatedUser.setEmail(user.getEmail());
            entityManager.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> getUserById(int id) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            return Optional.of(entityManager.find(User.class, id));
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getUsers() {
        List<User> list = new ArrayList<>();
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            list = entityManager.createQuery("from User", User.class).getResultList();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return list;
    }
}