package com.dearme.util;

public class NameStore {
    private static String name = null;
    private static boolean isInitialized = false;

    private NameStore() {}

    public static String getName() {
        return name;
    }

    public static boolean isSet() {
        return isInitialized;
    }

    public static void setName(String newName) {
        if (!isInitialized) {
            name = newName;
            isInitialized = true;
        }
    }

    public static void resetName() {
        name = null;
        isInitialized = false;
    }
}
