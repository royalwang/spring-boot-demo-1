package com.bambrow.spring.pojo;

import java.util.Objects;

/**
 * A typical Coffee com.bambrow.spring.pojo.
 *   see coffee_context.xml
 */

public class Coffee {
    private String milk;
    private String sugar;
    private String size;

    public String getMilk() {
        return milk;
    }

    public void setMilk(String milk) {
        this.milk = milk;
    }

    public String getSugar() {
        return sugar;
    }

    public void setSugar(String sugar) {
        this.sugar = sugar;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coffee coffee = (Coffee) o;
        return Objects.equals(milk, coffee.milk) &&
                Objects.equals(sugar, coffee.sugar) &&
                Objects.equals(size, coffee.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(milk, sugar, size);
    }
}
