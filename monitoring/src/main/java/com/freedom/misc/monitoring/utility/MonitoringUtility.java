package com.freedom.misc.monitoring.utility;

import com.freedom.misc.monitoring.constants.MetricSubmissionType;
import com.freedom.misc.monitoring.entity.Metric;
import com.freedom.misc.monitoring.submission.DefaultMetricSubmissionUtility;
import com.freedom.misc.monitoring.submission.MetricSubmissionUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Component
public class MonitoringUtility {

	@Autowired(required= false)
	private List<MetricSubmissionUtility> metricSubmissionServices ;
	
	@Autowired
	private DefaultMetricSubmissionUtility defaultSubmissionService ;
	
	private Map<MetricSubmissionType, MetricSubmissionUtility> metricSubmissionServicesMap = new EnumMap<>(MetricSubmissionType.class);
	
	@PostConstruct
	private void init() {

		if (!CollectionUtils.isEmpty(metricSubmissionServices)) {

			metricSubmissionServices.stream()
					.forEach(service -> metricSubmissionServicesMap.put(service.submissionType(), service));

		}

	}
	
	public MetricSubmissionUtility getMetricSubmissionUtility(MetricSubmissionType submissionType ) {
		
		return metricSubmissionServicesMap.getOrDefault(submissionType, defaultSubmissionService);
	}
	
	public void submitMetric(Metric metric) {
		
		MetricSubmissionUtility submissionService = getMetricSubmissionUtility(metric.getMetricSubmissionType());
		submissionService.publishMetric(metric);
	}
	
	public void submitMetrics(List<Metric> metrics) {
		
		metrics.stream().forEach(this :: submitMetric);
	}

}