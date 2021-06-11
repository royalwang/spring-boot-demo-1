package com.bambrow.spring.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * A typical Student com.bambrow.spring.pojo, using com.bambrow.spring.annotations instead of xml.
 */

// as <com.bambrow.spring.bean name="student1" class="com.bambrow.spring.component.Student">
@Component(value = "student1")
public class Student {
    // as <property name="name" value="David"/>
    @Value("David")
    private String name;
    @Value("1")
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
