package com.connection;

public class ChildPOJO {
    static String childName;
    static String dob;

    public static String getChildName() {
        return childName;
    }

    public static void setChildName(String childName) {
        ChildPOJO.childName = childName;
    }

    public static String getDob() {
        return dob;
    }

    public static void setDob(String dob) {
        ChildPOJO.dob = dob;
    }
}
