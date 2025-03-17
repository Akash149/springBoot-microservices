package com.user.service.services;

import java.util.List;

import com.user.service.entities.User;

public interface UserService {

    User saveUser(User user);
    List<User> getUsers();
    User getUserById(String id);
    User updateUser(User user);
    void deleteUser(String id);

}
