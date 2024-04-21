package com.example.Team4.Models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Vaccination {
    private Insured insured;
    private Doctor doctor;
    private String vaccinationDate;
    private String expirationDate;

    public Vaccination(Insured insured, String vaccinationDate, Doctor doctor) {
        this.insured = insured;
        this.vaccinationDate = vaccinationDate;
        this.doctor = doctor;
    }

    public Insured getInsured() {
        return insured;
    }

    public void setInsured(Insured insured) {
        this.insured = insured;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getVaccinationDate() {
        return vaccinationDate;
    }

    public void setVaccinationDate(String vaccinationDate) {
        this.vaccinationDate = vaccinationDate;
    }

    public String getExpirationDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate vaccinationDatetolocalDate = LocalDate.parse(vaccinationDate, formatter);
        LocalDate expirationDateNotFormatted = vaccinationDatetolocalDate.plusMonths(9);
        expirationDate =  expirationDateNotFormatted.format(formatter);

        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
}
