package com.nl.logiceacards.infrastructure.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogExecutionTime {
    
    String additionalMessage() default "";
    
}
