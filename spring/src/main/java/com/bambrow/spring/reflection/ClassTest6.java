package com.bambrow.spring.reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Dynamic proxy.
 */

public class ClassTest6 {
    public static void main(String[] args) {
        Hello hello = new HelloWorld();
        hello.hello("David"); // hello David

        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println(method);
                if (method.getName().equals("hello")) {
                    System.out.println("hello " + args[0]);
                }
                return null;
            }
        };
        /*
        InvocationHandler handler = (proxy, method, args1) -> {
            System.out.println(method);
            if (method.getName().equals("hello")) {
                System.out.println("hello " + args1[0]);
            }
            return null;
        };
         */

        Hello hello2 = (Hello) Proxy.newProxyInstance(
                Hello.class.getClassLoader(),
                new Class[] { Hello.class },
                handler
        );

        hello.hello("David"); // hello David
    }
}

interface Hello {
    void hello(String str);
}

class HelloWorld implements Hello {
    @Override
    public void hello(String str) {
        System.out.println("hello " + str);
    }
}
