package ru.ramazanov.CRUD_User_App.services;


import ru.ramazanov.CRUD_User_App.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void addUser(User user);
    void deleteUserById(int id);
    void updateUserById(int id, User user);
    Optional<User> getUserById(int id);
    List<User> getUsers();
}
