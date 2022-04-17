package com.freedom.misc.monitoring.config;

import com.freedom.misc.monitoring.constants.MetricTagName;
import com.freedom.misc.monitoring.entity.Metric;
import com.timgroup.statsd.NonBlockingStatsDClient;
import com.timgroup.statsd.StatsDClient;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Data
@Configuration
public class StatsDConfig {
	
	@Value("${statsd.server.host:localhost}")
	private String statsDHost;

	@Value("${statsd.server.port:8125}" )
	private Integer statsDPort;

	@Value("${datadog.metrics.prefix:aeps_switch}")
	private String metricsPrefix;

	@Value("${datadog.switch:true}")
	private boolean datadogSwitch;
	
	@Value("${log.datadog.metric:true}")
	private boolean logMetric;
	
	@Value("${server.hostname}")
	private String host;
	
	@Value("${container.hostname}")
	private String container;

	@Bean
	public StatsDClient statsDClient() {
		return new NonBlockingStatsDClient(metricsPrefix, statsDHost,statsDPort, getConstantTags());
	}

	private String[] getConstantTags() {
		
		Map<String, String> tagsMap = new HashMap<>();
		
		tagsMap.put(MetricTagName.HOST.getTagName(), host);
		tagsMap.put(MetricTagName.CONTAINER.getTagName(), container);
		
		return Metric.getTags(tagsMap);
	}
}