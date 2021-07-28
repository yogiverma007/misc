package com.freedom.misc.logging.annotation;

import org.springframework.boot.logging.LogLevel;

import java.lang.annotation.*;

/**
 * @usage Use this annotation for logging request, response and Exception logs on the
 * basis of requirement. It has following values: 1. isRequestEnabled: default true, if
 * set too false request related logging will be off. 2. requestLevel: default INFO, if
 * set to some level then all request related logging will be done on that level. 3.
 * isResponseEnabled: default true, if set too false response related logging will be off.
 * 4. responseLevel: default INFO, if set to some level then all response related logging
 * will be done on that level. 5. isExceptionEnabled: default true, if set too false
 * exception related logging will be off. 6. exceptionLevel: default ERROR, if set to some
 * level then all exception related logging will be done on that level.
 * <p>
 * e.g. if on a method, someone put @Logged(isRequestEnabled=false) , it will not do
 * logging for request statement with their level.
 * <p>
 * Consider the following example.
 *
 * <pre>
 *        {@code
 *
 *         		&#64;Logged(isRequestEnabled=true, requestLevel=LogLevel.DEBUG)
 *         		public void testIt(int x)
 *         		{
 *         		 	log.info("Method Under Test");
 *         		}
 *         }
 * </pre>
 *
 * <pre>
 *       on calling TestIt Method with number 1  as an argument will print following logging statement in log file.
 *
 *        DEBUG : Request Method Signature: public void testIt(int )
 *        DEBUG : Request Method Parameter Values in sequence:
 *        DEBUG : 1
 *        INFO : Method Under Test
 *
 * </pre>
 */
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface Logged {

    boolean isRequestEnabled() default true;

    LogLevel requestLevel() default LogLevel.INFO;

    boolean isResponseEnabled() default true;

    LogLevel responseLevel() default LogLevel.INFO;

    boolean isExceptionEnabled() default true;

    LogLevel exceptionLevel() default LogLevel.ERROR;

    boolean printStackTraceEnabled() default false;

}