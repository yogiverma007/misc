package com.freedom.misc.monitoring.submission;

import com.freedom.misc.monitoring.constants.MetricSubmissionType;
import com.freedom.misc.monitoring.entity.Metric;
import org.springframework.stereotype.Component;

import static com.freedom.misc.monitoring.constants.MetricSubmissionType.GAUGE;
import static com.freedom.misc.monitoring.constants.MetricType.LONG;

@Component
public class RecordGauge extends MetricSubmissionUtility {

	@Override
	public void submitMetric(Metric metric) {
		if (metric.getMetricType().equals(LONG)) {
			statsdClient.gauge(metric.getMetricName(), Long.parseLong(metric.getValue()),
					metric.getSampleRate(), metric.getTags());
		} else {
			statsdClient.gauge(metric.getMetricName(), Double.parseDouble(metric.getValue()),
					metric.getSampleRate(), metric.getTags());
		}
	}

	@Override
	public MetricSubmissionType submissionType() {
		return GAUGE;
	}

}