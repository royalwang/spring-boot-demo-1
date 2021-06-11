package com.bambrow.spring.pojo;

/**
 * A typical CoffeeMaker com.bambrow.spring.pojo.
 *   see coffee_context.xml
 */

public class CoffeeMaker {
    private Coffee coffee = null;

    public Coffee getCoffee() {
        return coffee;
    }

    public void setCoffee(Coffee coffee) {
        this.coffee = coffee;
    }

    public String makeCoffee() {
        return "A coffee with " + coffee.getMilk() + " and " + coffee.getSugar() + " and " + coffee.getSize();
    }
}
