package com.bambrow.spring.bean;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import com.bambrow.spring.pojo.Coffee;

/**
 * Use @Bean to finish injection. @Configuration is required here.
 *   see CoffeeMakerBeanTest.
 */

@Configuration
public class CoffeeMakerBean {
    // multiple com.bambrow.spring.bean name is allowed
    // scope can be singleton or prototype
    @Bean(name = "getCoffee")
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public Coffee getCoffee() {
        // for testing, directly create Coffee here
        Coffee coffee = new Coffee();
        coffee.setMilk("whole milk");
        coffee.setSugar("half sugar");
        coffee.setSize("large size");
        return coffee;
    }
}
