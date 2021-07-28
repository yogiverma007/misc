package com.freedom.misc.monitoring.submission;

import com.freedom.misc.monitoring.constants.MetricSubmissionType;
import com.freedom.misc.monitoring.entity.Metric;
import org.springframework.stereotype.Component;

@Component
public class DefaultMetricSubmissionUtility extends MetricSubmissionUtility {

	@Override
	public void submitMetric(Metric metric) {
	}

	@Override
	public MetricSubmissionType submissionType() {
		return MetricSubmissionType.DEFAULT ;
	}


}
