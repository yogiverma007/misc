package com.freedom.misc.monitoring.submission;

import com.freedom.misc.monitoring.constants.MetricSubmissionType;
import com.freedom.misc.monitoring.entity.Metric;
import org.springframework.stereotype.Component;

import static com.freedom.misc.monitoring.constants.MetricSubmissionType.RECORD_SET_VALUE;

@Component
public class RecordSetValue extends MetricSubmissionUtility {

	@Override
	public void submitMetric(Metric metric) {
		statsdClient.recordSetValue(metric.getMetricName(), metric.getValue(), metric.getTags());
	}

	@Override
	public MetricSubmissionType submissionType() {
		return RECORD_SET_VALUE;
	}
}