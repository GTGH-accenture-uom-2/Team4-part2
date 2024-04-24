package com.example.Team4.Dtos;

import com.example.Team4.Models.Doctor;
import com.example.Team4.Models.Timeslot;

public class SelectReservationDTO {
    private Long amka;
    private Timeslot timeslot;
    private Doctor doctor;

    public Long getAmka() {
        return amka;
    }

    public void setAmka(Long amka) {
        this.amka = amka;
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
