package com.bambrow.spring.aop;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LeasingTest {
    @Test
    public void leasingTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] { "leasing_context.xml" }
        );
        Lessor lessor = context.getBean("lessor", Lessor.class);
        lessor.service();
        // inspecting apartment
        // confirming rent
        // signing lease
        // receiving rent
        // assigning keys
    }

    @Test
    public void leasingTestAround() {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] { "leasing_context.xml" }
        );
        Lessor lessor = context.getBean("lessor", Lessor.class);
        lessor.serviceAround();
        // same output
    }

    @Test
    public void leasingTestNoAnnotation() {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] { "leasing_context_no_annotation.xml" }
        );
        LessorNoAnnotation lessor = context.getBean("lessor", LessorNoAnnotation.class);
        lessor.service();
        // same output
    }
}
