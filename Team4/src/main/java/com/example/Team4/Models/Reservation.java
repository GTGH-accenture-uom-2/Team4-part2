package com.example.Team4.Models;

import com.example.Team4.Dtos.SelectReservationDTO;

public class Reservation {
    private Insured insured;
    private Timeslot timeslot;
    private  Doctor doctor;


    public Reservation(Insured insured, Timeslot timeslot) {
        this.insured = insured;
        this.timeslot = timeslot;
        this.doctor = timeslot.getDoctor();
    }

    public Reservation(SelectReservationDTO selectReservationDTO) {
    }

    public Reservation(Insured insured, Timeslot timeslot, Doctor doctor) {
        this.insured = insured;
        this.timeslot = timeslot;
        this.doctor = doctor;
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
