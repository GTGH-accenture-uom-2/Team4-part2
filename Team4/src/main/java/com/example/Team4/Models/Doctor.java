package com.example.Team4.Models;

import java.util.ArrayList;

public class Doctor {
    //den evala lists apo reservations, timeslots kai vaccinations gia na to doume stin poreia
    private Long amka;
    private String name;
    private String surname;
    private  ArrayList<Timeslot> timeslots;

    public Doctor(Long amka, String name, String surname) {
        this.amka = amka;
        this.name = name;
        this.surname = surname;
        timeslots = new ArrayList<Timeslot>();
    }

    public Doctor() {}

    public Long getAmka() {
        return amka;
    }

    public void setAmka(Long amka) {
        this.amka = amka;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public ArrayList<Timeslot> getTimeslots() {
        return timeslots;
    }

    public void setTimeslots(ArrayList<Timeslot> timeslots) {
        this.timeslots = timeslots;
    }
}
