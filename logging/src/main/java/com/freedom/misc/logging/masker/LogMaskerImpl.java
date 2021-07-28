package com.freedom.misc.logging.masker;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.pattern.ConverterKeys;

@Plugin(name = "logmask", category = "Converter")
@ConverterKeys({ "msg" })
public class LogMaskerImpl extends AbstractLogMasker {

    public LogMaskerImpl(String[] options) {

        super("msg", "msg");
    }

    public static LogMaskerImpl newInstance(final String[] options) {

        return new LogMaskerImpl(options);
    }

    protected LogMaskerImpl(String name, String style) {

        super(name, style);
    }

    @Override
    protected Level getPermittedLogLevelForApplication() {
        return Level.INFO;
    }
}