package com.freedom.misc.monitoring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * All Requests and responses which need to be monitored have to provide
 * implementation of the same.
 *
 */
public interface MonitoredEntity {

	@JsonIgnore
	List<Metric> getMetrics();

}