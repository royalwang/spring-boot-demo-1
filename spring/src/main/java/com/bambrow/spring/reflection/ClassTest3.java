package com.bambrow.spring.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * Invoking methods of Class.
 */

public class ClassTest3 {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<Person2> c1 = Person2.class;
        Method m1 = c1.getMethod("getName");
        // public java.lang.String com.bambrow.spring.reflection.Person2.getName()
        Method m2 = c1.getDeclaredMethod("setName", String.class);
        // private void com.bambrow.spring.reflection.Person2.setName(java.lang.String)
        System.out.println(m1);
        System.out.println(m2);

        System.out.println(m2.getName()); // setName
        System.out.println(m2.getReturnType()); // void
        for (Class para : m2.getParameterTypes()) {
            System.out.println(para);
        }
        // class java.lang.String
        for (Parameter para : m2.getParameters()) {
            System.out.println(para);
        }
        // java.lang.String arg0
        System.out.println(m2.getModifiers()); // 2

        String str = "hello world";
        Method m3 = String.class.getMethod("substring", int.class);
        String sub = (String) m3.invoke(str, 6);
        System.out.println(sub); // world

        Method m4 = Integer.class.getMethod("parseInt", String.class);
        Integer i = (Integer) m4.invoke(null, "123");
        System.out.println(i); // 123

        Person2 p2 = new Person2("David");
        m2.setAccessible(true); // access private method; this is highly unrecommended!
        m2.invoke(p2, "Bob");
        System.out.println(p2.getName()); // Bob

        Class<Student2> c2 = Student2.class;
        Method m5 = c2.getMethod("speak");
        m5.invoke(new Student2("David")); // hello professor
    }
}

class Student2 extends Person2 {
    public Student2(String name) {
        super(name);
    }

    @Override
    public void speak() {
        System.out.println("hello professor");
    }
}

class Person2 {
    private String name;

    public Person2(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public void speak() {
        System.out.println("hello world");
    }
}