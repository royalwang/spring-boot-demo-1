package com.bambrow.mysql.service;

import com.bambrow.mysql.entity.User;
import com.bambrow.mysql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserById(Long id) {
        Optional<User> optional = userRepository.findById(id);
        return optional.orElse(null);
    }

    @Override
    public List<User> getUserByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public List<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>((Collection<? extends User>) userRepository.findAll());
    }

    @Override
    public User insertUser(String name, String username) {
        return userRepository.save(User.builder().name(name).username(username).build());
    }

    @Override
    public User insertUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean deleteUserById(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteUserByName(String name) {
        userRepository.deleteByName(name);
        return true;
    }

    @Override
    public boolean deleteUserByUsername(String username) {
        userRepository.deleteByUsername(username);
        return true;
    }

    @Override
    public boolean deleteAllUsers() {
        userRepository.deleteAll();
        return true;
    }

    @Override
    public boolean updateUserById(Long id, String name, String username) {
        User user = getUserById(id);
        if (user == null) {
            return false;
        } else {
            user.setName(name);
            user.setUsername(username);
            userRepository.save(user);
            return true;
        }
    }

    @Override
    public int countValidUsers() {
        return userRepository.countValidUsers();
    }

    @Override
    public boolean resetTable() {
        deleteAllUsers();
        insertUser("Amy", "amy");
        insertUser("Bob", "bob");
        insertUser("Cathy", null);
        insertUser("David", null);
        insertUser("Eva", null);
        return true;
    }

    @Override
    public boolean fillUsername() {
        userRepository.fillUsername();
        return true;
    }
}
