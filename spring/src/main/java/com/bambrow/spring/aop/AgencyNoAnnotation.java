package com.bambrow.spring.aop;

/**
 * Agency class without annotation.
 */

public class AgencyNoAnnotation {
    public void before() {
        System.out.println("inspecting apartment");
        System.out.println("confirming rent");
    }

    public void after() {
        System.out.println("assigning keys");
    }
}
