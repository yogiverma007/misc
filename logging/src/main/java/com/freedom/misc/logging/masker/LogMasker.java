package com.freedom.misc.logging.masker;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.pattern.ConverterKeys;

@Plugin(name = "logmask", category = "Converter")
@ConverterKeys({ "msg" })
public class LogMasker extends AbstractLogMasker {

    public LogMasker(String[] options) {

        super("msg", "msg");
    }

    public static LogMasker newInstance(final String[] options) {

        return new LogMasker(options);
    }

    protected LogMasker(String name, String style) {

        super(name, style);
    }

    @Override
    protected Level getPermittedLogLevelForApplication() {
        return Level.INFO;
    }
}