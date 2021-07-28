package com.freedom.misc.monitoring.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MetricNameEnum {

	EXEC_TIME("execution.time"),
	EXCEPTION("exception"),
	HTTP_STATUS_CODE("http.status.code"),
	RESPONSE_CODE("response.code"),
	RESPONSE_STATUS("response.status"),
	NO_OF_RECORDS_PROCESSED("no.of.records.processed"),
	MISSING_RESPONSE_CODE("missing.response.code"),
	NPCI_REQUEST_RESPONSE("npci.request.response"),
	HTTP_CLIENT_STATS("http.client.stats"),
	NPCI_RESPONSE_AVERTED("npci.response.averted"),

	
	;
	
	private final String metricName; 
	
}