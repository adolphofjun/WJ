package com.pluten.utils;

public class StringUtil {
    public static boolean isTrimEmpty(String s) {
        return (s == null || s.trim().length() == 0);
    }
}
