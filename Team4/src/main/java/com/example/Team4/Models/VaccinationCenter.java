package com.example.Team4.Models;

import java.util.ArrayList;

public class VaccinationCenter {
    //den evala reservations gia na to doume stin poreia
    private String code;
    private String address;
    private ArrayList<Timeslot> timeslots;

    public VaccinationCenter(String code, String address, ArrayList<Timeslot> timeslots) {
        this.code = code;
        this.address = address;
        this.timeslots = timeslots;
    }

    public VaccinationCenter(String code, String address) {
        this.code = code;
        this.address = address;
    }

    public ArrayList<Timeslot> getTimeslots() {
        return timeslots;
    }
}
