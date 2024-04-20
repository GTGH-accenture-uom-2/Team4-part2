package com.example.Team4.Models;

public class Reservation {
    //den evala vacCenter gia na to doume stin poreia
    private Insured insured;
    private Timeslot timeslot;

    public Reservation(Insured insured, Timeslot timeslot) {
        this.insured = insured;
        this.timeslot = timeslot;
    }

    public Insured getInsured() {
        return insured;
    }

    public void setInsured(Insured insured) {
        this.insured = insured;
    }

    public Timeslot getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(Timeslot timeslot) {
        this.timeslot = timeslot;
    }
}
