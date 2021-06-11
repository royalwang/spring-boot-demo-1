package com.bambrow.spring.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Introduction of Class class.
 */

public class ClassTest {
    public static void main(String[] args) throws ClassNotFoundException {
        Class c1 = String.class;
        Class c2 = "hello".getClass();
        Class c3 = Class.forName("java.lang.String");
        System.out.println(c1 == c2); // true
        System.out.println(c2 == c3); // true

        Integer n = 1;
        boolean b1 = n instanceof Integer;
        boolean b2 = n instanceof Number;
        boolean b3 = n.getClass() == Integer.class;
        // boolean b4 = n.getClass() == Number.class;
        System.out.println(b1 && b2 && b3); // true
        // System.out.println(b4);

        System.out.println(c1.getName()); // java.lang.String
        System.out.println(c1.getSimpleName()); // String
        System.out.println(c1.getPackageName()); // java.lang
        System.out.println(c1.isInterface()); // false
        System.out.println(c1.isEnum()); // false
        System.out.println(c1.isArray()); // false
        System.out.println(c1.isPrimitive()); // false
        System.out.println("----");
        for (Field field : c1.getFields()) {
            System.out.println(field);
        }
        // public static final java.util.Comparator java.lang.String.CASE_INSENSITIVE_ORDER
        System.out.println("----");
        for (Method method : c1.getMethods()) {
            System.out.println(method);
        }
        /*
        public boolean java.lang.String.equals(java.lang.Object)
        public int java.lang.String.length()
        public java.lang.String java.lang.String.toString()
        public int java.lang.String.hashCode()
        public void java.lang.String.getChars(int,int,char[],int)
        public int java.lang.String.compareTo(java.lang.String)
        public int java.lang.String.compareTo(java.lang.Object)
        public int java.lang.String.indexOf(java.lang.String,int)
        public int java.lang.String.indexOf(int)
        public int java.lang.String.indexOf(int,int)
        public int java.lang.String.indexOf(java.lang.String)
        public static java.lang.String java.lang.String.valueOf(int)
        public static java.lang.String java.lang.String.valueOf(float)
        public static java.lang.String java.lang.String.valueOf(boolean)
        public static java.lang.String java.lang.String.valueOf(long)
        public static java.lang.String java.lang.String.valueOf(double)
        public static java.lang.String java.lang.String.valueOf(java.lang.Object)
        public static java.lang.String java.lang.String.valueOf(char)
        public static java.lang.String java.lang.String.valueOf(char[])
        public static java.lang.String java.lang.String.valueOf(char[],int,int)
        public java.util.stream.IntStream java.lang.String.codePoints()
        public boolean java.lang.String.isEmpty()
        public char java.lang.String.charAt(int)
        public int java.lang.String.codePointAt(int)
        public int java.lang.String.codePointBefore(int)
        public int java.lang.String.codePointCount(int,int)
        public int java.lang.String.offsetByCodePoints(int,int)
        public byte[] java.lang.String.getBytes(java.nio.charset.Charset)
        public void java.lang.String.getBytes(int,int,byte[],int)
        public byte[] java.lang.String.getBytes(java.lang.String) throws java.io.UnsupportedEncodingException
        public byte[] java.lang.String.getBytes()
        public boolean java.lang.String.contentEquals(java.lang.StringBuffer)
        public boolean java.lang.String.contentEquals(java.lang.CharSequence)
        public boolean java.lang.String.equalsIgnoreCase(java.lang.String)
        public int java.lang.String.compareToIgnoreCase(java.lang.String)
        public boolean java.lang.String.regionMatches(boolean,int,java.lang.String,int,int)
        public boolean java.lang.String.regionMatches(int,java.lang.String,int,int)
        public boolean java.lang.String.startsWith(java.lang.String,int)
        public boolean java.lang.String.startsWith(java.lang.String)
        public boolean java.lang.String.endsWith(java.lang.String)
        public int java.lang.String.lastIndexOf(int)
        public int java.lang.String.lastIndexOf(java.lang.String,int)
        public int java.lang.String.lastIndexOf(java.lang.String)
        public int java.lang.String.lastIndexOf(int,int)
        public java.lang.String java.lang.String.substring(int,int)
        public java.lang.String java.lang.String.substring(int)
        public java.lang.CharSequence java.lang.String.subSequence(int,int)
        public java.lang.String java.lang.String.concat(java.lang.String)
        public java.lang.String java.lang.String.replace(java.lang.CharSequence,java.lang.CharSequence)
        public java.lang.String java.lang.String.replace(char,char)
        public boolean java.lang.String.matches(java.lang.String)
        public boolean java.lang.String.contains(java.lang.CharSequence)
        public java.lang.String java.lang.String.replaceFirst(java.lang.String,java.lang.String)
        public java.lang.String java.lang.String.replaceAll(java.lang.String,java.lang.String)
        public java.lang.String[] java.lang.String.split(java.lang.String)
        public java.lang.String[] java.lang.String.split(java.lang.String,int)
        public static java.lang.String java.lang.String.join(java.lang.CharSequence,java.lang.Iterable)
        public static java.lang.String java.lang.String.join(java.lang.CharSequence,java.lang.CharSequence[])
        public java.lang.String java.lang.String.toLowerCase()
        public java.lang.String java.lang.String.toLowerCase(java.util.Locale)
        public java.lang.String java.lang.String.toUpperCase()
        public java.lang.String java.lang.String.toUpperCase(java.util.Locale)
        public java.lang.String java.lang.String.trim()
        public java.lang.String java.lang.String.strip()
        public java.lang.String java.lang.String.stripLeading()
        public java.lang.String java.lang.String.stripTrailing()
        public boolean java.lang.String.isBlank()
        public java.util.stream.Stream java.lang.String.lines()
        public java.lang.String java.lang.String.indent(int)
        public java.lang.Object java.lang.String.transform(java.util.function.Function)
        public java.util.stream.IntStream java.lang.String.chars()
        public char[] java.lang.String.toCharArray()
        public static java.lang.String java.lang.String.format(java.util.Locale,java.lang.String,java.lang.Object[])
        public static java.lang.String java.lang.String.format(java.lang.String,java.lang.Object[])
        public static java.lang.String java.lang.String.copyValueOf(char[])
        public static java.lang.String java.lang.String.copyValueOf(char[],int,int)
        public native java.lang.String java.lang.String.intern()
        public java.lang.String java.lang.String.repeat(int)
        public java.util.Optional java.lang.String.describeConstable()
        public java.lang.Object java.lang.String.resolveConstantDesc(java.lang.invoke.MethodHandles$Lookup) throws java.lang.ReflectiveOperationException
        public java.lang.String java.lang.String.resolveConstantDesc(java.lang.invoke.MethodHandles$Lookup)
        public final native void java.lang.Object.wait(long) throws java.lang.InterruptedException
        public final void java.lang.Object.wait(long,int) throws java.lang.InterruptedException
        public final void java.lang.Object.wait() throws java.lang.InterruptedException
        public final native java.lang.Class java.lang.Object.getClass()
        public final native void java.lang.Object.notify()
        public final native void java.lang.Object.notifyAll()
         */
        System.out.println("----");
        System.out.println(c1.getSuperclass());
        // class java.lang.Object
        System.out.println("----");
        for (Constructor cons : c1.getConstructors()) {
            System.out.println(cons);
        }
        /*
        public java.lang.String(byte[])
        public java.lang.String(byte[],int,int)
        public java.lang.String(byte[],java.nio.charset.Charset)
        public java.lang.String(byte[],java.lang.String) throws java.io.UnsupportedEncodingException
        public java.lang.String(byte[],int,int,java.nio.charset.Charset)
        public java.lang.String(java.lang.StringBuilder)
        public java.lang.String(java.lang.StringBuffer)
        public java.lang.String(char[],int,int)
        public java.lang.String(char[])
        public java.lang.String(java.lang.String)
        public java.lang.String()
        public java.lang.String(byte[],int,int,java.lang.String) throws java.io.UnsupportedEncodingException
        public java.lang.String(byte[],int)
        public java.lang.String(byte[],int,int,int)
        public java.lang.String(int[],int,int)
         */

        System.out.println(String[].class.getName()); // [Ljava.lang.String;
        System.out.println(String[].class.getSimpleName()); // String[]
        System.out.println(String[].class.getPackageName()); // java.lang
        System.out.println(String[].class.isArray()); // true

        for (Field field : String[].class.getFields()) {
            System.out.println(field);
        }
        // nothing

        for (Method method : String[].class.getMethods()) {
            System.out.println(method);
        }
        /*
        public final native void java.lang.Object.wait(long) throws java.lang.InterruptedException
        public final void java.lang.Object.wait(long,int) throws java.lang.InterruptedException
        public final void java.lang.Object.wait() throws java.lang.InterruptedException
        public boolean java.lang.Object.equals(java.lang.Object)
        public java.lang.String java.lang.Object.toString()
        public native int java.lang.Object.hashCode()
        public final native java.lang.Class java.lang.Object.getClass()
        public final native void java.lang.Object.notify()
        public final native void java.lang.Object.notifyAll()
         */

        System.out.println(String[].class.getSuperclass());
        // class java.lang.Object
    }
}
