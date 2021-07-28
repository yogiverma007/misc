package com.freedom.misc.monitoring.aspects;

import com.freedom.misc.monitoring.constants.MetricTagName;
import com.freedom.misc.monitoring.entity.Metric;
import com.freedom.misc.monitoring.entity.TimeMetric;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Aspect
@ConditionalOnProperty(name = "db.repository.monitoring.enable", havingValue = "true")
public class DBRepositoryAspect extends BaseAspect{

	@Pointcut("execution(* org.springframework.data.repository.CrudRepository+.*(..))")
	public void dbRepositoryExecutionPointcut() {
	}

	@Around("dbRepositoryExecutionPointcut()")
	public Object executeAround(ProceedingJoinPoint joinPoint) throws Throwable {
		
		TimeMetric timeMetric = new TimeMetric();
		List<Metric> metrics =  new ArrayList<>();

		Object retVal = null;
		try {
			retVal = joinPoint.proceed();
		} finally {
			timeMetric.stopTimer();
			metrics.add(timeMetric);
			
			metrics.forEach(metric -> getAspectUtility().addContextRelatedTags(joinPoint, metric));
			metrics.forEach(metric -> metric.setTag(MetricTagName.TARGET_CLIENT.getTagName(), "DB"));
			getMonitoringUtility().submitMetrics(metrics);
		}
		return retVal;

	}
}