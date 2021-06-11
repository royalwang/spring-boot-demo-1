package com.bambrow.spring.component;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Use @ComponentScan to get Book exposed to IoC.
 * Use @PropertySource to indicate the resource.
 */

// @PropertySource does the same as the following line in xml
// <context:property-placeholder location="book.properties" ignore-unresolvable="true" />
@Configuration
@ComponentScan(basePackageClasses = { com.bambrow.spring.component.Book.class })
@PropertySource("book.properties")
public class BookConfig {
}
