package com.bambrow.spring.annotations;

import java.lang.annotation.Repeatable;

/**
 * An example of Repeatable Annotation.
 */

@Repeatable(Persons.class)
public @interface Person {
    String id();
}
