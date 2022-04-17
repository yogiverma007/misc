package com.freedom.misc.httpclient.template.properties;

public interface RestTemplatePropertiesSpecification {

    int getConnectionRequestTimeout();

    int getConnectionTimeout();

    int getReadTimeout();

    int getMaxTotalConnection();

    int getMaxPerChannel();

    int getStaleConnectionCheckAfterInactivityPeriod();

    String getClientName();

    boolean ifEquals(RestTemplatePropertiesSpecification properties);

}