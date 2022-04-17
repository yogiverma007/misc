package com.freedom.misc.logging.service;

import com.freedom.misc.logging.constants.LogsMaskingEnums;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class LogsMaskingService {

    private final static Map<Integer, String> MASK_CACHE = new HashMap<>();

    public static String mask(String log) {
        for (LogsMaskingEnums maskEnum : LogsMaskingEnums.values()) {
            log = replaceWithMask(log, maskEnum);
        }
        return log;
    }

    private static String getMask(LogsMaskingEnums logsMaskingEnums) {

        int maskLength = logsMaskingEnums.getMaskLength();

        String maskString = MASK_CACHE.get(maskLength);

        if (StringUtils.isEmpty(maskString)) {

            maskString = StringUtils.repeat('X', maskLength);
            MASK_CACHE.put(maskLength, maskString);
        }

        return maskString;
    }

    private static String replaceWithMask(String log, LogsMaskingEnums logsMaskingEnums) {

        for (Pattern pattern : logsMaskingEnums.getPatternsToMask()) {

            Matcher m = pattern.matcher(log);

            if(m.find()) {
                log = m.replaceAll(createReplaceString(logsMaskingEnums));
            }
        }
        return log;
    }

    private static String createReplaceString(LogsMaskingEnums logsMaskingEnums) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\"");
        stringBuilder.append(logsMaskingEnums.getReplacedMaskName());
        stringBuilder.append("\": \"");
        stringBuilder.append(getMask(logsMaskingEnums));
        stringBuilder.append("\",");

        return stringBuilder.toString();
    }


}
