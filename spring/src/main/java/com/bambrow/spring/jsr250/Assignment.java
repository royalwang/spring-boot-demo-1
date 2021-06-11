package com.bambrow.spring.jsr250;

import com.bambrow.spring.component.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 *  An example of JSR-250 com.bambrow.spring.annotations.
 */

@Repository("assignment")
@Lazy
public class Assignment {
    @Resource(name = "student1")
    private Student student;
    @Value("100")
    private int grade;

    // constructor public Assignment(Student student, int grade) will raise error
    public Assignment() {
        System.out.println("calling constructor");
    }

    @PostConstruct
    public void init() {
        System.out.println("calling init");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("calling destroy");
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "student=" + student +
                ", grade=" + grade +
                '}';
    }
}
