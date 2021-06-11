package com.bambrow.spring.jsr250;

import org.springframework.context.annotation.ComponentScan;

/**
 * Use @ComponentScan to get Student and Assignment exposed to Spring IoC.
 *   see AssignmentSpringTest.
 */

@ComponentScan(basePackageClasses = { com.bambrow.spring.jsr250.Assignment.class, com.bambrow.spring.component.Student.class })
public class AssignmentConfig {
}
