package com.example.scoop.basics.common;

import java.util.List;

public class Strings {

    public static String joinWithDelimiter(String delimiter, List<String> strings) {
        return joinWithDelimiter(delimiter, strings.toArray(new String[strings.size()]));
    }

    public static String joinWithDelimiter(String delimiter, String... strings) {
        StringBuilder sb = new StringBuilder();
        String loopDelimiter = "";
        for (String s : strings) {
            if (!Strings.isNullOrEmpty(s)) {
                sb.append(loopDelimiter);
                sb.append(s);

                loopDelimiter = delimiter;
            }
        }

        return sb.toString();
    }

    public static boolean isNullOrEmpty(String string) {
        return string == null || string.length() == 0; // string.isEmpty() in Java 6
    }

    public static boolean isNullOrBlank(String string) {
        return string == null || string.trim().length() == 0; // string.isEmpty() in Java 6
    }

    public static String nullToEmpty(String string) {
        return nullOrEmptyToDefault(string, "");
    }

    public static String emptyToNull(String string) {
        if (isNullOrEmpty(string)) {
            return null;
        } else {
            return string;
        }
    }

    public static String nullOrEmptyToDefault(String string, String defaultString) {
        return isNullOrEmpty(string) ? defaultString : string;
    }

    public static boolean hasAnyPrefix(String number, String... prefixes) {
        if (number == null) {
            return false;
        }
        for (String prefix : prefixes) {
            if (number.startsWith(prefix)) {
                return true;
            }
        }
        return false;
    }

    public static boolean equalsIgnoreCase(String first, String second) {
        if (first == null) {
            return second == null;
        } else {
            return first.equalsIgnoreCase(second);
        }
    }
}
