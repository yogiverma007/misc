package com.freedom.misc.monitoring.aspects;

import com.freedom.misc.monitoring.annotation.Monitor;
import com.freedom.misc.monitoring.constants.MetricTagName;
import com.freedom.misc.monitoring.entity.Metric;
import com.freedom.misc.monitoring.entity.MonitoredEntity;
import com.freedom.misc.monitoring.entity.TimeMetric;
import com.freedom.misc.monitoring.utility.AspectUtility;
import com.freedom.misc.monitoring.utility.MonitoringUtility;
import lombok.Data;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static com.freedom.misc.monitoring.constants.MetricNameEnum.EXCEPTION;
import static com.freedom.misc.monitoring.constants.MetricSubmissionType.INCREMENT_COUNTER;
import static com.freedom.misc.monitoring.constants.MetricType.LONG;

@Data
public abstract class BaseAspect {
	
	@Autowired
	private AspectUtility aspectUtility;

	@Autowired
	private MonitoringUtility monitoringUtility;


	public Object executeAround(ProceedingJoinPoint joinPoint) throws Throwable {

		Monitor monitor = aspectUtility.getMonitorAnnotation(joinPoint);
		
		TimeMetric timeMetric = new TimeMetric();
		List<Metric> metrics =  new ArrayList<>();

		Object retVal = null;
		try {
			retVal = joinPoint.proceed();
		} finally {

			timeMetric.stopTimer();
			
			if (monitor.monitorTime()) {
				metrics.add(timeMetric);
			}
			
			if (monitor.monitorResponse() && retVal instanceof MonitoredEntity) {
				List<Metric> responseMetrics = ((MonitoredEntity) retVal).getMetrics();
				metrics.addAll(responseMetrics);
			}
			
			if (monitor.monitorArgs()) {
				metrics.addAll(aspectUtility.getArgumentMetrics(joinPoint));
			}
			
			metrics.forEach(metric -> aspectUtility.addContextRelatedTags(joinPoint, metric));

			monitoringUtility.submitMetrics(metrics);
		}
		return retVal;

	}

	public void executeAfterThrowing(JoinPoint joinPoint, Throwable e) {

		Monitor monitor = aspectUtility.getMonitorAnnotation(joinPoint);

		if (monitor.monitorException()) {
			
			Metric exceptionMetric = new Metric(EXCEPTION.getMetricName(), LONG, INCREMENT_COUNTER);
			exceptionMetric.setTag(MetricTagName.EXCEPTION.getTagName(), e.getClass().getName());
			
			aspectUtility.addContextRelatedTags(joinPoint,exceptionMetric);

			monitoringUtility.submitMetric(exceptionMetric);
		}
	}
}