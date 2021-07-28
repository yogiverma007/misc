package com.freedom.misc.logging.constants;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Getter
public enum LogsMaskingEnums {

    ACCOUNT_NUMBER_MASK("accNum", "\"accNum\":\"XXXXXXXXXXXXXX",4, "(accNum|\"accNum\")(:|=)[\\s\"]*((.*?,)|(.*?\\))|(.*?\\s))")

    ;

    private int maskLength;
    private String seeThroughMask;
    private String replacedMaskName;

    private List<Pattern> patternsToMask = new ArrayList<>();

    LogsMaskingEnums(String replacedMaskName, String seeThroughMask, int maskLength, String... patterns) {

        for (String pattern : patterns) {
            patternsToMask.add(Pattern.compile(pattern));
        }
        this.seeThroughMask = seeThroughMask;
        this.replacedMaskName = replacedMaskName;
        this.maskLength = maskLength;
    }
}