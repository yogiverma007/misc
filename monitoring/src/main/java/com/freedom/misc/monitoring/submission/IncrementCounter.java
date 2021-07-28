package com.freedom.misc.monitoring.submission;

import com.freedom.misc.monitoring.constants.MetricSubmissionType;
import com.freedom.misc.monitoring.entity.Metric;
import org.springframework.stereotype.Component;

import static com.freedom.misc.monitoring.constants.MetricSubmissionType.INCREMENT_COUNTER;

@Component
public class IncrementCounter extends MetricSubmissionUtility {

	@Override
	public void submitMetric(Metric metric) {
		statsdClient.increment(metric.getMetricName(), metric.getSampleRate(), metric.getTags());
	}

	@Override
	public MetricSubmissionType submissionType() {
		return INCREMENT_COUNTER;
	}
}