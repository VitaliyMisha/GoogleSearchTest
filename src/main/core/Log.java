package core;

import org.apache.log4j.Logger;

public class Log extends Logger{

    protected Log(String name) {
        super(name);
    }
    private static String getCallerClassName() {
        StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
        for (int i = 1; i < stElements.length; i++) {
            StackTraceElement ste = stElements[i];
            if (!ste.getClassName().equals(Log.class.getName())
                    && ste.getClassName().indexOf("java.lang.Thread") != 0) {
                return ste.getClassName();
            }
        }
        return null;
    }

    public static void info(String message) {
        String name = getCallerClassName();
        Logger log = Log.getLogger(name);
        log.info(message);
    }

    public static void error(String message) {
        String name = getCallerClassName();
        Logger log = Logger.getLogger(name);
        log.error(message);
    }
}

