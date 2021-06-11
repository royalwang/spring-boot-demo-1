package com.bambrow.spring.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of DAO interface.
 */

public class StudentDAOImpl implements StudentDAO {
    // for simplicity, map works as a database here
    private Map<Integer, Student> students;

    public StudentDAOImpl() {
        students = new HashMap<>();
    }

    @Override
    public List<Student> getAllStudents() {
        return new ArrayList<>(students.values());
    }

    @Override
    public Student getStudent(int id) {
        if (students.containsKey(id)) {
            return students.get(id);
        } else {
            System.out.println("This id does not exist!");
            return null;
        }
    }

    @Override
    public boolean containsStudent(int id) {
        if (students.containsKey(id)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Student insertStudent(int id, String name) {
        if (students.containsKey(id)) {
            System.out.println("This id already exists!");
            return students.get(id);
        } else {
            Student student = new Student(name, id);
            students.put(id, student);
            return student;
        }
    }

    @Override
    public Student updateStudent(int id, String name) {
        if (students.containsKey(id)) {
            Student student = new Student(name, id);
            students.put(id, student);
            return student;
        } else {
            System.out.println("This id does not exist!");
            return students.get(id);
        }
    }

    @Override
    public Student deleteStudent(int id) {
        if (students.containsKey(id)) {
            Student student = students.get(id);
            students.remove(id);
            return student;
        } else {
            System.out.println("This id does not exist!");
            return null;
        }
    }
}
