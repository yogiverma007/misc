package com.freedom.misc.monitoring.entity;

import com.freedom.misc.monitoring.constants.MetricSubmissionType;
import com.freedom.misc.monitoring.constants.MetricType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Metric {

	private Map<String, String> tags = new HashMap<>();
	
	private final String metricName;
	
	private String value = "1";
	
	private double sampleRate = 1.0d;
	
	private final MetricType metricType;
	
	private final MetricSubmissionType metricSubmissionType ;

	public void setTag(String tagName, String tagValue) {
		tags.put(tagName, tagValue);
	}
	
	public String[] getTags() {
		
		return getTags(tags);
	}
	
	public static String[] getTags(Map<String, String> tags) {
		
		String[] tagsArray = new String[tags.size()];

		int currentElements = 0;
		for (Entry<String, String> entry : tags.entrySet()) {

			String tagValue = entry.getKey() + ":" + entry.getValue();
			tagsArray[currentElements] = tagValue;
			currentElements++;
		}

		return tagsArray;
	}
}