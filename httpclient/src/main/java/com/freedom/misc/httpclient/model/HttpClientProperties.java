package com.freedom.misc.httpclient.model;

import com.freedom.misc.httpclient.template.properties.RestTemplatePropertiesSpecification;
import lombok.Data;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.web.client.RestTemplate;

@Data
public class HttpClientProperties {

    private String clientName;

    private RestTemplatePropertiesSpecification templateProperties;

    private RestTemplate restTemplate;

    private PoolingHttpClientConnectionManager connManager;

}