package com.freedom.misc.monitoring.submission;

import com.freedom.misc.monitoring.constants.MetricSubmissionType;
import com.freedom.misc.monitoring.entity.Metric;
import org.springframework.stereotype.Component;

import static com.freedom.misc.monitoring.constants.MetricSubmissionType.RECORD_EXECUTION_TIME;

@Component
public class RecordExecutionTime extends MetricSubmissionUtility {

	@Override
	public void submitMetric(Metric metric) {

		statsdClient.recordExecutionTime(metric.getMetricName(), Long.parseLong(metric.getValue()),
				metric.getSampleRate(), metric.getTags());
	}

	@Override
	public MetricSubmissionType submissionType() {
		return RECORD_EXECUTION_TIME;
	}
}