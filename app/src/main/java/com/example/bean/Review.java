package com.example.bean;

public class Review {
    private static String[] nameArray;
    private static String[] contentArray;

    public static String[] getNameArray() {
        return nameArray;
    }

    public static void setNameArray(String[] nameArray) {
        Review.nameArray = nameArray;
    }

    public static String[] getContentArray() {
        return contentArray;
    }

    public static void setContentArray(String[] contentArray) {
        Review.contentArray = contentArray;
    }


    public static String getContent(int position) {
        return contentArray[position % nameArray.length];
    }


    public static String getName(int position) {
        return nameArray[position % contentArray.length];
    }
}
