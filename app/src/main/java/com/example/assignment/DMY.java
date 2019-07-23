package com.example.assignment;

public class DMY {
    private String day,month;

    public DMY() {
    }

    public DMY(String day, String month) {
        this.day = day;
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
