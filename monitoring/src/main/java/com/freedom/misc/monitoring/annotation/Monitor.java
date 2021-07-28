package com.freedom.misc.monitoring.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * This Annotation is used to monitor following things during execution of method:
 * 
 * 1. time taken.
 * 2. Exceptions thrown if any.
 * 3. Input Arguments.
 * 4. Response returned.
 */

@Retention(RUNTIME)
@Target({TYPE, METHOD})
public @interface Monitor {
	
	boolean monitorTime() default false;

	boolean monitorException() default false;
	
	boolean monitorResponse() default false;
	
	boolean monitorArgs() default false ;
	
}