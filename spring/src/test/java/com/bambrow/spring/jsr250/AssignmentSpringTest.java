package com.bambrow.spring.jsr250;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AssignmentSpringTest {
    @Test
    public void assignmentTest() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AssignmentConfig.class);
        Assignment assignment = context.getBean("assignment", Assignment.class);
        System.out.println(assignment);
        ((ConfigurableApplicationContext) context).close();
        // calling constructor
        // calling init
        // Assignment{student=Student{name='David', id=1}, grade=100}
        // calling destroy
    }
}
