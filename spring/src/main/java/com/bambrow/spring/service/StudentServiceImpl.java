package com.bambrow.spring.service;

import com.bambrow.spring.component.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * StudentService implementation. @Autowired is used for IoC.
 *   See StudentServiceTest.
 */

@Service("studentService")
// @Component("studentService")
public class StudentServiceImpl implements StudentService {
    // Student will be autowired
    @Autowired
    private Student student = null;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public void printStudentInfo() {
        System.out.println(student);
    }
}
