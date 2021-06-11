package com.bambrow.spring.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Invoking constructors of Class.
 */

public class ClassTest4 {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        String s1 = new String();
        String s2 = String.class.newInstance();
        System.out.println(s1.equals(s2)); // true

        Constructor c1 = Integer.class.getConstructor(int.class);
        Integer i = (Integer) c1.newInstance(123);
        Constructor c2 = Integer.class.getConstructor(String.class);
        Integer j = (Integer) c2.newInstance("456");
        System.out.println(i); // 123
        System.out.println(j); // 456

        for (Constructor c : Integer.class.getConstructors()) {
            System.out.println(c);
        }
        /*
        public java.lang.Integer(java.lang.String) throws java.lang.NumberFormatException
        public java.lang.Integer(int)
         */

        for (Constructor c : String.class.getDeclaredConstructors()) {
            System.out.println(c);
        }
        /*
        public java.lang.String(byte[])
        public java.lang.String(byte[],int,int)
        public java.lang.String(byte[],java.nio.charset.Charset)
        public java.lang.String(byte[],java.lang.String) throws java.io.UnsupportedEncodingException
        public java.lang.String(byte[],int,int,java.nio.charset.Charset)
        java.lang.String(char[],int,int,java.lang.Void)
        java.lang.String(java.lang.AbstractStringBuilder,java.lang.Void)
        public java.lang.String(java.lang.StringBuilder)
        public java.lang.String(java.lang.StringBuffer)
        java.lang.String(byte[],byte)
        public java.lang.String(char[],int,int)
        public java.lang.String(char[])
        public java.lang.String(java.lang.String)
        public java.lang.String()
        public java.lang.String(byte[],int,int,java.lang.String) throws java.io.UnsupportedEncodingException
        public java.lang.String(byte[],int)
        public java.lang.String(byte[],int,int,int)
        public java.lang.String(int[],int,int)
         */
    }
}
