package com.freedom.misc.monitoring.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MonitorAspect extends BaseAspect {


	/**
	 * This PointCut limits matching to join points where the join point has the
	 * given annotation.
	 * 
	 * Methods annotated with @Monitor
	 */
	@Pointcut("@annotation(com.paytm.bank.aeps.monitoring.annotation.Monitor)")
	public void methodAnnotatedWithMonitor() {
		/* Pointcut Expression */
	}

	/**
	 * This PointCut limits matching to join points within types that have the given
	 * annotation.
	 * 
	 * For all methods of classes having annotation @Monitor on them
	 */
	@Pointcut("@within(com.paytm.bank.aeps.monitoring.annotation.Monitor)")
	public void classAnnotatedWithMonitor() {
		/* Pointcut Expression */
	}

	@Pointcut("methodAnnotatedWithMonitor() || classAnnotatedWithMonitor()")
	public void monitorAnnotated() {
		/* Pointcut Expression */
	}

	@Around("monitorAnnotated()")
	@Override
	public Object executeAround(ProceedingJoinPoint joinPoint) throws Throwable {
		return super.executeAround(joinPoint);
	}

	@AfterThrowing(pointcut = "monitorAnnotated()", throwing = "e")
	@Override
	public void executeAfterThrowing(JoinPoint joinPoint, Throwable e) {

		super.executeAfterThrowing(joinPoint, e);
	}

}