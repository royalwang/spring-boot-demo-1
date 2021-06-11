package com.bambrow.spring.reflection;

import java.io.Closeable;
import java.io.DataInputStream;
import java.io.Serializable;

/**
 * Getting inheritance hierarchy of Class.
 */

public class ClassTest5 {
    public static void main(String[] args) {
        Class c1 = String.class;
        Class c2 = c1.getSuperclass();
        System.out.println(c2); // class java.lang.Object

        Class c3 = c2.getSuperclass();
        System.out.println(c3); // null

        for (Class ci : c1.getInterfaces()) {
            System.out.println(ci);
        }
        /*
        interface java.io.Serializable
        interface java.lang.Comparable
        interface java.lang.CharSequence
        interface java.lang.constant.Constable
        interface java.lang.constant.ConstantDesc
         */

        Class c4 = Integer.class;
        Class c5 = c4.getSuperclass();
        System.out.println(c5); // class java.lang.Number

        for (Class ci : c5.getInterfaces()) {
            System.out.println(ci);
        }
        // interface java.io.Serializable

        System.out.println(DataInputStream.class.getSuperclass()); // class java.io.FilterInputStream
        System.out.println(Closeable.class.getSuperclass()); // null

        Object n = Integer.valueOf(123);
        System.out.println(n instanceof Double); // false
        System.out.println(n instanceof Integer); // true
        System.out.println(n instanceof Number); // true
        System.out.println(n instanceof Serializable); // true

        System.out.println(Integer.class.isAssignableFrom(Integer.class)); // true
        System.out.println(Number.class.isAssignableFrom(Integer.class)); // true
        System.out.println(Object.class.isAssignableFrom(Integer.class)); // true
        System.out.println(Integer.class.isAssignableFrom(Number.class)); // false
    }
}
