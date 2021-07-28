package com.freedom.misc.logging.masker;

import com.freedom.misc.logging.constants.LogsMaskingEnums;
import com.freedom.misc.logging.service.LogsMaskingService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.pattern.LogEventPatternConverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.logging.log4j.Level.*;

@Slf4j
public abstract class AbstractLogMasker extends LogEventPatternConverter {

    private static Map<Level, List<Level>> logLevelHierarchy = new HashMap<>();

    static {

        List<Level> logLevelList = new ArrayList<>();

        logLevelList.add(OFF);
        logLevelHierarchy.put(OFF, new ArrayList<>(logLevelList));

        logLevelList.add(FATAL);
        logLevelHierarchy.put(FATAL, new ArrayList<>(logLevelList));

        logLevelList.add(ERROR);
        logLevelHierarchy.put(ERROR, new ArrayList<>(logLevelList));

        logLevelList.add(WARN);
        logLevelHierarchy.put(WARN, new ArrayList<>(logLevelList));

        logLevelList.add(INFO);
        logLevelHierarchy.put(INFO, new ArrayList<>(logLevelList));

        logLevelList.add(DEBUG);
        logLevelHierarchy.put(DEBUG, new ArrayList<>(logLevelList));

        logLevelList.add(TRACE);
        logLevelHierarchy.put(TRACE, new ArrayList<>(logLevelList));
    }

    protected AbstractLogMasker(String name, String style) {
        super(name, style);
    }

    @Override
    public void format(LogEvent logEvent, StringBuilder appender) {

        String logMessage = logEvent.getMessage().getFormattedMessage();
        String tempMessage;

        try	{
            tempMessage = LogsMaskingService.mask(logMessage);
            if(StringUtils.isNotEmpty(tempMessage)) {
                logMessage = tempMessage;
            }
        } catch (Exception e) {
            log.error("Error in Masking", e);
        }

        appender.append(logMessage);

    }

    private boolean isLoggingForLogLevelEnabled(Level logLevel) {
        return logLevelHierarchy.get(getPermittedLogLevelForApplication()).contains(logLevel);
    }

    protected abstract Level getPermittedLogLevelForApplication();

}