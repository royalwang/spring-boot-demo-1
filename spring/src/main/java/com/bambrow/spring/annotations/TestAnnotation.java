package com.bambrow.spring.annotations;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;
import java.lang.annotation.*;

/**
 * An example of self-defined Annotation.
 */

@Target({METHOD, TYPE})
@Retention(RUNTIME)
@Documented
@Inherited
public @interface TestAnnotation {
    String name() default "";
    String date() default "";
}
