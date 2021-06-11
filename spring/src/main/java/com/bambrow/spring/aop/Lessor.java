package com.bambrow.spring.aop;

import org.springframework.stereotype.Component;

/**
 * Lessor class.
 * com.bambrow.spring.service() is used for @Before and @After.
 * serviceAround() is used for @Around.
 */

@Component("lessor")
public class Lessor {
    public void service() {
        System.out.println("signing lease");
        System.out.println("receiving rent");
    }

    // same output, just for testing @Around
    public void serviceAround() {
        System.out.println("signing lease");
        System.out.println("receiving rent");
    }
}
