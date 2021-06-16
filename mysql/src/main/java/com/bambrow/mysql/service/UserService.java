package com.bambrow.mysql.service;

import com.bambrow.mysql.entity.User;

import java.util.List;

public interface UserService {

    User getUserById(Long id);

    List<User> getUserByName(String name);

    List<User> getUserByUsername(String username);

    List<User> getAllUsers();

    User insertUser(String name, String username);

    User insertUser(User user);

    boolean deleteUserById(Long id);

    boolean deleteUserByName(String name);

    boolean deleteUserByUsername(String username);

    boolean deleteAllUsers();

    boolean updateUserById(Long id, String name, String username);

    int countValidUsers();

    boolean resetTable();

    boolean fillUsername();

}
