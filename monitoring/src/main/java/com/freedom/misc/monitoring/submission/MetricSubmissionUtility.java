package com.freedom.misc.monitoring.submission;

import com.freedom.misc.monitoring.config.StatsDConfig;
import com.freedom.misc.monitoring.constants.MetricSubmissionType;
import com.freedom.misc.monitoring.entity.Metric;
import com.timgroup.statsd.StatsDClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class MetricSubmissionUtility {

	private Logger log = LoggerFactory.getLogger("MonitoringLogger");

	@Autowired
	private StatsDConfig config;
	
	@Autowired
	protected StatsDClient statsdClient;

	public abstract void submitMetric(Metric metric);

	public abstract MetricSubmissionType submissionType();

	public void publishMetric(Metric metric) {

		if (config.isLogMetric()) {

			log.info("Metric : {}", metric);
		}
		
		if (config.isDatadogSwitch()) {
			submitMetric(metric);
		}
	}
}