package com.bambrow.spring.pojo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StudentSpringTest {
    @Test
    public void studentTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] { "student_context.xml" }
        );
        Student s1 = context.getBean("student1", Student.class);
        Student s2 = context.getBean("student2", Student.class);
        StudentNoConstructor s3 = context.getBean("student3", StudentNoConstructor.class);
        assert(s1.getName().equals("David") && s1.getId() == 101);
        assert(s2.getName().equals("Eva") && s2.getId() == 102);
        assert(s3.getName().equals("Fred") && s3.getId() == 103);
    }
}
