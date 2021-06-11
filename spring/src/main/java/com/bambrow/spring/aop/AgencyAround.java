package com.bambrow.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * AgencyAround class. Instead of using @Before and @After, use @Around.
 *   see leasing_context.xml
 */

@Component
@Aspect
public class AgencyAround {
    @Around("execution(* com.bambrow.spring.aop.Lessor.serviceAround())")
    public void around(ProceedingJoinPoint joinPoint) {
        System.out.println("inspecting apartment");
        System.out.println("confirming rent");

        try {
            joinPoint.proceed();
        } catch (Throwable t) {
            t.printStackTrace();
        }

        System.out.println("assigning keys");
    }
}
