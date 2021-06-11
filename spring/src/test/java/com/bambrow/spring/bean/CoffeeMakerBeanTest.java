package com.bambrow.spring.bean;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.bambrow.spring.pojo.Coffee;

public class CoffeeMakerBeanTest {
    @Test
    public void beanAnnotationTest() {
        ApplicationContext context = new AnnotationConfigApplicationContext("com.bambrow.spring.bean");
        Coffee coffee = context.getBean("getCoffee", Coffee.class);
        assert(coffee.getMilk().equals("whole milk"));
        assert(coffee.getSugar().equals("half sugar"));
        assert(coffee.getSize().equals("large size"));
    }
}
