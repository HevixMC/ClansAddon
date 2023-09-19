package dev.a8kj.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    private DateUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String getFormattedDate() {
        return new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date());
    }

}
