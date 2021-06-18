package com.bambrow.mybatis.service;

import com.bambrow.mybatis.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> selectAll();
    Student select(Long id);
    List<Student> select(String name);
    boolean insert(String name, String username);
    boolean insert(String name, String username, Integer age);
    boolean insert(String name, String username, String email);
    boolean insert(String name, String username, Integer age, String email);
    boolean updateName(Long id, String name);
    boolean updateUsername(Long id, String username);
    boolean updateAge(Long id, Integer age);
    boolean updateEmail(Long id, String email);
    boolean delete(Long id);
    boolean deleteAll();
    boolean reset();
    boolean fillEmail();
    int countAdults();
}
