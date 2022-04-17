package com.freedom.misc.logging.annotation.advice;

import com.freedom.misc.logging.annotation.Logged;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@Aspect
public class LoggerAspect {

    @Around("@annotation(logged)")
    public Object log(ProceedingJoinPoint pjp, Logged logged) throws Throwable {

        Logger log = LoggerFactory.getLogger(pjp.getTarget().getClass().getCanonicalName());
        Object response;

        try {

            MethodSignature signature = (MethodSignature) pjp.getSignature();

            if (logged.isRequestEnabled() && !LogLevel.OFF.equals(logged.requestLevel())) {

                String logMessage = "Executing method: " + pjp.getTarget().getClass().getName() + "."
                        + signature.getMethod().getName() + " ::: with param-values: "
                        + StringUtils.arrayToCommaDelimitedString(pjp.getArgs());

                logMessage(log, logged.requestLevel(), logMessage);

            }

            response = pjp.proceed();

            if (logged.isResponseEnabled() && response != null && !LogLevel.OFF.equals(logged.responseLevel())) {

                logMessage(log, logged.responseLevel(), "Response = " + response.toString());
            }

            return response;

        } catch (Throwable th) {
            if (logged.isExceptionEnabled()) {

                if (!LogLevel.OFF.equals(logged.exceptionLevel())) {

                    logMessage(log, logged.exceptionLevel(), "Exception Occurred : " + th.getLocalizedMessage());
                }

                if (logged.printStackTraceEnabled()) {

                    log.error("Exception Stack Trace: ", th);
                }


            }

            throw th;

        }

    }

    /**
     * @param level : define the level of message to be printed in logs.
     * @param message : content to be printed in logs.
     *
     * e.g. logMessage(LogLevel.INFO, "This is a INFO message..."); will print the message
     * "This is a INFO message..." with level INFO or you can say it is equivalent to
     * log.info("This is a INFO message...");
     */
    public void logMessage(Logger log, LogLevel level, String message) {

        switch (level) {
            case TRACE:
                log.trace(message);
                break;
            case DEBUG:
                log.debug(message);
                break;
            case INFO:
                log.info(message);
                break;
            case WARN:
                log.warn(message);
                break;
            case ERROR:
                log.error(message);
                break;
            case OFF:
                break;
            default:
                log.error("Exception Occurred : Please provide one of TRACE, DEBUG, INFO, WARN, ERROR or OFF");
                break;
        }
    }
}