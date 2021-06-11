package com.bambrow.spring.component;

import org.springframework.context.annotation.ComponentScan;

/**
 * Use @ComponentScan to get Student and StudentService exposed to Spring IoC.
 *   see StudentComponentTest.
 */

@ComponentScan(basePackages = {"com.bambrow.spring.component", "com.bambrow.spring.service"})
// @ComponentScan(basePackageClasses = { com.bambrow.spring.component.Student.class })
public class StudentConfig {
}
