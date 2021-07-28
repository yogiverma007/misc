package com.freedom.misc.monitoring.entity;

import com.freedom.misc.monitoring.constants.MetricNameEnum;
import com.freedom.misc.monitoring.constants.MetricSubmissionType;
import com.freedom.misc.monitoring.constants.MetricType;

public class TimeMetric extends Metric {

	private long startTime ;
	
	public TimeMetric() {
		
		super(MetricNameEnum.EXEC_TIME.getMetricName(), MetricType.LONG, MetricSubmissionType.RECORD_EXECUTION_TIME);
		startTime = System.currentTimeMillis();
	}
	
	public void startTimer() {
		startTime = System.currentTimeMillis();
	}

	
	public void stopTimer() {
		
		long duration = System.currentTimeMillis() - startTime ;
		this.setValue(String.valueOf(duration));
	}
	
}
