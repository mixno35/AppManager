package com.xianitt.appmanager.data;

public class InstallLocation {

    public static String string(String type) {
        switch(type) {
            case "auto":
                return "Auto";
            case "internalOnly":
                return "Internal only";
            case "preferExternal":
                return "Prefer external";
            default:
                return "";
        }
    }
}
