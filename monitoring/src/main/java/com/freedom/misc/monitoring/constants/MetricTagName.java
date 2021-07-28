package com.freedom.misc.monitoring.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MetricTagName {
	
	HOST("hostip"),
	CONTAINER("container"),
	API("api"),
	METHOD("method"),
	EXCEPTION("exception"),
	SOURCE_CLIENT("sourceClient"),
	TARGET_CLIENT("targetClient"),
	RESP_CODE("respCode"),
	RESP_STATUS("respStatus"),
	PROC_CODE("procCode"),
	TYPE("type"),
	HANDLER("handler"),
	TRANSACTION_TYPE("transactionType"),
	MTI("mti"),
	CLIENT("client"),
	PROPERTY("property"),


	
	;
	
	@Getter
	private final String tagName;

	
}