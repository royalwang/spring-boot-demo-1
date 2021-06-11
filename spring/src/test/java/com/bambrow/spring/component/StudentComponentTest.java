package com.bambrow.spring.component;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class StudentComponentTest {
    @Test
    public void studentComponentTest() {
        ApplicationContext context = new AnnotationConfigApplicationContext(StudentConfig.class);
        Student s = context.getBean("student1", Student.class);
        assert(s.getId() == 1 && s.getName().equals("David"));
    }
}
