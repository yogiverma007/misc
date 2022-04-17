package com.freedom.misc.monitoring.utility;

import com.freedom.misc.monitoring.annotation.Monitor;
import com.freedom.misc.monitoring.constants.MetricTagName;
import com.freedom.misc.monitoring.entity.Metric;
import com.freedom.misc.monitoring.entity.MonitoredEntity;
import com.freedom.misc.monitoring.submission.DefaultMetricSubmissionUtility;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class AspectUtility {

    public String getMethodName(JoinPoint joinPoint) {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getMethod().getName();
        String simpleClassName = signature.getMethod().getDeclaringClass().getSimpleName();

        methodName = simpleClassName + "." + methodName;

        return methodName;

    }

    public String getAPINameIfAvailable() {

        try {
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            if (requestAttributes == null) {
                return null;
            }
            else {
                return ((ServletRequestAttributes) requestAttributes).getRequest() == null
                        ? null
                        : ((ServletRequestAttributes) requestAttributes).getRequest().getRequestURI();
            }
        } catch (Exception e) {
            return null;
        }

    }

    public void addContextRelatedTags(JoinPoint joinPoint, Metric metric) {

        String methodName = getMethodName(joinPoint);
        String apiName = getAPINameIfAvailable();

        if (!(methodName == null || "".equals(methodName))) {
            metric.setTag(MetricTagName.METHOD.getTagName(), methodName);
        }

        if (!(apiName == null || "".equals(apiName))) {
            metric.setTag(MetricTagName.API.getTagName(), apiName);
        }

    }

    public Monitor getMonitorAnnotation(JoinPoint joinPoint) {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Class<?> declaringClass = method.getDeclaringClass();

        if (method.isAnnotationPresent(Monitor.class)) {

            return method.getAnnotation(Monitor.class);
        } else if (declaringClass.isAnnotationPresent(Monitor.class)) {

            return declaringClass.getAnnotation(Monitor.class);
        } else {
            return getDefaultMonitorAnnotation();
        }

    }

    public Monitor getDefaultMonitorAnnotation() {

        return DefaultMetricSubmissionUtility.class.getAnnotation(Monitor.class);
    }

    public List<Metric> getArgumentMetrics(JoinPoint joinPoint) {

        List<Metric> metrics = new ArrayList<>();

        Object[] params = joinPoint.getArgs();

        if (params.length > 0) {

            for (Object parameter : params) {

                if (parameter instanceof MonitoredEntity) {

                    MonitoredEntity entity = (MonitoredEntity) parameter;
                    metrics.addAll(entity.getMetrics());
                }
            }

        }

        return metrics;
    }


}
