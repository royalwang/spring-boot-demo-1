package com.bambrow.spring.bean;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;

/**
 * An example of JavaBean Standard.
 *   1) All properties private (access via getter/setter)
 *   2) A public no-argument constructor
 *   3) implements Serializable
 */

public class BeanBasics {
    public static void main(String[] args) throws IntrospectionException {
        BeanInfo info = Introspector.getBeanInfo(Person.class);
        for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
            System.out.println(pd.getName());
            System.out.println(pd.getReadMethod());
            System.out.println(pd.getWriteMethod());
            System.out.println("----");
        }
    }
    /*
    age
    public int com.bambrow.spring.bean.Person.getAge()
    public void com.bambrow.spring.bean.Person.setAge(int)
    ----
    class
    public final native java.lang.Class java.lang.Object.getClass()
    null
    ----
    name
    public java.lang.String com.bambrow.spring.bean.Person.getName()
    public void com.bambrow.spring.bean.Person.setName(java.lang.String)
    ----
     */
}

class Person implements Serializable {
    private String name;
    private int age;

    public Person() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
