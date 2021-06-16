package com.bambrow.mysql.controller;

import cn.hutool.core.lang.Dict;
import com.bambrow.mysql.entity.User;
import com.bambrow.mysql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/insert")
    public Dict insert(@RequestBody User user) {
        User save = userService.insertUser(user);
        return Dict.create().set("code", save.equals(user) ? "200" : "500").set("data", save);
    }

    @RequestMapping("/get/{id}")
    public Dict getUser(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return Dict.create().set("code", user != null ? "200" : "500").set("data", user);
    }

    @RequestMapping("/getName/{name}")
    public Dict getUsersByName(@PathVariable String name) {
        List<User> userList = userService.getUserByName(name);
        return Dict.create().set("code", "200").set("data", userList);
    }

    @RequestMapping("/get")
    public Dict getUsers() {
        List<User> userList = userService.getAllUsers();
        return Dict.create().set("code", "200").set("data", userList);
    }

    @RequestMapping("/delete/{id}")
    public Dict deleteUser(@PathVariable Long id) {
        boolean delete = userService.deleteUserById(id);
        return Dict.create().set("code", delete ? "200" : "500").set("message", delete ? "Success" : "Fail");
    }

    @RequestMapping("/deleteName/{name}")
    public Dict deleteUserByName(@PathVariable String name) {
        boolean delete = userService.deleteUserByName(name);
        return Dict.create().set("code", delete ? "200" : "500").set("message", delete ? "Success" : "Fail");
    }

    @RequestMapping("/deleteAll")
    public Dict deleteAllUsers() {
        boolean delete = userService.deleteAllUsers();
        return Dict.create().set("code", delete ? "200" : "500").set("message", delete ? "Success" : "Fail");
    }

    @RequestMapping("/reset")
    public Dict reset() {
        boolean reset = userService.resetTable();
        return Dict.create().set("code", reset ? "200" : "500").set("message", reset ? "Success" : "Fail");
    }

    @RequestMapping("/countValid")
    public Dict countValid() {
        int count = userService.countValidUsers();
        return Dict.create().set("code", "200").set("count", count);
    }

    @RequestMapping("/fillUsername")
    public Dict fillUsername() {
        boolean fill = userService.fillUsername();
        return Dict.create().set("code", fill ? "200" : "500").set("message", fill ? "Success" : "Fail");
    }

}
