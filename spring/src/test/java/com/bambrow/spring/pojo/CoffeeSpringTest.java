package com.bambrow.spring.pojo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CoffeeSpringTest {
    @Test
    public void coffeeTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] { "coffee_context.xml" }
        );
        Coffee coffee = (Coffee) context.getBean("coffee1");
        assert(coffee.getMilk().equals("whole milk"));
        assert(coffee.getSugar().equals("half sugar"));
        assert(coffee.getSize().equals("large size"));
    }

    @Test
    public void coffeeMakerTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] { "coffee_context.xml" }
        );
        Coffee coffee = (Coffee) context.getBean("coffee1");
        CoffeeMaker coffeeMaker = (CoffeeMaker) context.getBean("coffeeMaker");
        assert(coffeeMaker.getCoffee().equals(coffee));
        System.out.println(coffeeMaker.makeCoffee());
        // A coffee with whole milk and half sugar and large size
    }

    @Test
    public void coffeeMakerAutowiredTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] { "coffee_context.xml" }
        );
        Coffee coffee = context.getBean("coffee1", Coffee.class);
        CoffeeMakerAutowired coffeeMaker = context.getBean("coffeeMakerAutowired", CoffeeMakerAutowired.class);
        assert(coffeeMaker.getCoffee().equals(coffee));
    }
}
