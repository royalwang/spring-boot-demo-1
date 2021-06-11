package com.bambrow.spring.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Agency class. Use @Aspect annotation.
 * Note @Before and @After can be used directly without @Pointcut.
 * Using @Pointcut can save some code.
 *   see leasing_context.xml
 */

@Component
@Aspect
public class Agency {
    // execution: trigger during execution
    // *: any return type
    // com.bambrow.spring.aop.Lessor.com.bambrow.spring.service(): method intercepted
    // @Pointcut method must have empty body and return void
    @Pointcut("execution(* com.bambrow.spring.aop.Lessor.service())")
    public void service() {
    }

    // @Before("execution(* com.bambrow.spring.aop.Lessor.com.bambrow.spring.service())")
    @Before("service()")
    public void before() {
        System.out.println("inspecting apartment");
        System.out.println("confirming rent");
    }

    // @After("execution(* com.bambrow.spring.aop.Lessor.com.bambrow.spring.service())")
    @After("service()")
    public void after() {
        System.out.println("assigning keys");
    }
}
