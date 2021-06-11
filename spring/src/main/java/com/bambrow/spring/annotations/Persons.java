package com.bambrow.spring.annotations;

/**
 * An example of Containing Annotation. It must have a value with array type.
 */

public @interface Persons {
    Person[] value();
}
