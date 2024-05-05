package ru.ramazanov.CRUD_User_App.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ramazanov.CRUD_User_App.dao.UserDao;
import ru.ramazanov.CRUD_User_App.models.User;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public void deleteUserById(int id) {
        userDao.deleteUserById(id);
    }

    @Override
    public void updateUserById(int id, User user) {
        userDao.updateUserById(id, user);
    }

    @Override
    public Optional<User> getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }
}
