package com.example.Team4.Models;

public class Vaccine {
    private String name;
    private int monthlyDuration;

    public Vaccine(String name) {
        this.name = name;
        if((this.name).equals("pfizer")){
            monthlyDuration = 9;
        } else if((this.name).equals("moderna")){
            monthlyDuration = 10;
        } else if((this.name).equals("astrazeneca")){
            monthlyDuration = 8;
        } else if ((this.name).equals("johnson")) {
            monthlyDuration = 6;
        }

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
