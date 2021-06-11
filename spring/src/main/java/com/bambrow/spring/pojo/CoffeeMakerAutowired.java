package com.bambrow.spring.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * An autowired implementation of CoffeeMaker.
 * Use @Qualifier to specify which Coffee will be used.
 *   see coffee_context.xml
 */

public class CoffeeMakerAutowired {
    private Coffee coffee = null;

    public Coffee getCoffee() {
        return coffee;
    }

    // @Autowired can be used on setter
    @Autowired(required = false)
    @Qualifier("coffee2")
    public void setCoffee(Coffee coffee) {
        this.coffee = coffee;
    }

    public String makeCoffee() {
        return "A coffee with " + coffee.getMilk() + " and " + coffee.getSugar() + " and " + coffee.getSize();
    }
}
