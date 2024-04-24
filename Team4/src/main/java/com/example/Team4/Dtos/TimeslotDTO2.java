package com.example.Team4.Dtos;

public class TimeslotDTO2 {

    private int day;
    private int month;
    private int year;

    private int hour;
    private int minutes;

    public TimeslotDTO2(int day, int month, int year, int hour, int minutes) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minutes = minutes;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public int getHour() {
        return hour;
    }

    public int getMinutes() {
        return minutes;
    }
}
