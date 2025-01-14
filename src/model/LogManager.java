package model;

import java.util.logging.*;


public class LogManager {

    public static Logger logger;
    public static FileHandler handler;

    static {
        try {
            logger = Logger.getLogger("Log1");
            handler = new FileHandler("Allionz-IMS-Log.txt", true);
            handler.setFormatter(new SimpleFormatter());
            logger.addHandler(handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Logger getLogger() {
        return logger;
    }

}