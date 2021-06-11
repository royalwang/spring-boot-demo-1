package com.bambrow.spring.component;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.bambrow.spring.service.StudentService;
import com.bambrow.spring.service.StudentServiceImpl;

public class StudentServiceTest {
    @Test
    public void studentServiceTest() {
        ApplicationContext context = new AnnotationConfigApplicationContext(StudentConfig.class);
        StudentService service = context.getBean("studentService", StudentServiceImpl.class);
        service.printStudentInfo();
        // Student{name='David', id=1}
        // without @Autowired this will be null
    }
}
