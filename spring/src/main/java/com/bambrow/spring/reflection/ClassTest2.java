package com.bambrow.spring.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Accessing fields of Class.
 */

public class ClassTest2 {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Class c1 = Student.class;
        System.out.println(c1.getField("score")); // public int com.bambrow.spring.reflection.Student.score
        System.out.println(c1.getField("name")); // public java.lang.String com.bambrow.spring.reflection.Person.name
        System.out.println(c1.getDeclaredField("grade")); // private int com.bambrow.spring.reflection.Student.grade

        Field field = String.class.getDeclaredField("value");
        System.out.println(field.getName()); // value
        System.out.println(field.getType()); // class [B
        System.out.println(field.getModifiers()); // 18
        System.out.println(Modifier.isFinal(field.getModifiers())); // true
        System.out.println(Modifier.isPublic(field.getModifiers())); // false
        System.out.println(Modifier.isProtected(field.getModifiers())); // false
        System.out.println(Modifier.isPrivate(field.getModifiers())); // true
        System.out.println(Modifier.isStatic(field.getModifiers())); // false

        Student s = new Student("David", 100, 10);
        Class c2 = s.getClass();
        Field f2 = c2.getDeclaredField("grade");
        f2.setAccessible(true); // access private field; this is highly unrecommended!
        Object v2 = f2.get(s);
        System.out.println(v2); // 10

        f2.set(s, 20);
        System.out.println(s.getGrade()); // 20
    }
}

class Student extends Person {
    public int score;
    private int grade;

    public Student(String name, int score, int grade) {
        super(name);
        this.score = score;
        this.grade = grade;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}

class Person {
    public String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
