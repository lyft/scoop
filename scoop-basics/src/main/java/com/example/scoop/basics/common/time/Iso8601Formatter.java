package com.example.scoop.basics.common.time;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

public class Iso8601Formatter {

    public static SimpleDateFormat create() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return simpleDateFormat;
    }
}
