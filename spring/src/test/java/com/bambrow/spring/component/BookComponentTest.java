package com.bambrow.spring.component;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BookComponentTest {
    @Test
    public void bookTest() {
        ApplicationContext context = new AnnotationConfigApplicationContext(BookConfig.class);
        Book book = context.getBean("book", Book.class);
        assert(book.getUuid() == 10001);
        assert(book.getName().equals("Zach's Life"));
        assert(book.getAuthor().equals("David"));
        assert(book.getYear() == 2019);
        System.out.println(book);
        // Book{uuid=10001, name='Zach's Life', author='David', year=2019}
    }
}
