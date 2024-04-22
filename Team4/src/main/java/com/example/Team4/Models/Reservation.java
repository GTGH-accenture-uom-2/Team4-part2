package com.example.Team4.Models;

import com.example.Team4.Dtos.SelectReservationDTO;

public class Reservation {
    //den evala vacCenter gia na to doume stin poreia
    private Insured insured;
    private Timeslot timeslot;

    private  Doctor doctor;


    public Reservation(Insured insured, Timeslot timeslot) {
        this.insured = insured;
        this.timeslot = timeslot;
    }

    public Reservation(SelectReservationDTO selectReservationDTO) {
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

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
