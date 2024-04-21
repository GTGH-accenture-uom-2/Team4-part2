package com.example.Team4.Models;

import java.util.ArrayList;

public class Doctor extends Person{
    //den evala lists apo reservations, timeslots kai vaccinations gia na to doume stin poreia

    private  ArrayList<Timeslot> timeslots;

   /* public Doctor(String name, String surname, Long amka, ArrayList<Timeslot> timeslots) {
        super(name, surname, amka);
        this.timeslots = timeslots;
    }

    */

    public Doctor(String name, String surname, Long amka) {
        super(name, surname, amka);
    }

    /*
    public Doctor() {
        super();
    }

     */


    public ArrayList<Timeslot> getTimeslots() {
        return timeslots;
    }

    public void setTimeslots(ArrayList<Timeslot> timeslots) {
        this.timeslots = timeslots;
    }
}
