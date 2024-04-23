package com.example.Team4.Models;

public class Vaccine {
    private String name;
    private int monthlyDuration;

    public Vaccine(String name, int monthlyDuration) {
        this.name = name;
        this.monthlyDuration = monthlyDuration;
    }



    public String getName() {
        return name;
    }

    public int getMonthlyDuration() {
        return monthlyDuration;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMonthlyDuration(int monthlyDuration) {
        this.monthlyDuration = monthlyDuration;
    }
}
