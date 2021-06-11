package com.bambrow.spring.dao;

import java.util.List;

/**
 * DAO interface. This is the recommended implementation.
 */

public interface StudentDAO {
    List<Student> getAllStudents();
    Student getStudent(int id);
    boolean containsStudent(int id);
    Student insertStudent(int id, String name);
    Student updateStudent(int id, String name);
    Student deleteStudent(int id);
}
