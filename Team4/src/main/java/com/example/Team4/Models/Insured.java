package com.example.Team4.Models;

public class Insured {
    //den evalla vaccination kai vacCenter gia na to doume stin poreia
    private Long afm;
    private Long amka;
    private String name;
    private String birthdate;
    private String surname;
    private String email;
    private int reservationChangeCount;

    public Insured(Long afm, Long amka, String name, String birthdate, String surname, String email) {
        this.afm = afm;
        this.amka = amka;
        this.name = name;
        this.birthdate = birthdate;
        this.surname = surname;
        this.email = email;
        reservationChangeCount = 0;
    }

    public Long getAfm() {
        return afm;
    }

    public void setAfm(Long afm) {
        this.afm = afm;
    }

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

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getReservationChangeCount() {
        return reservationChangeCount;
    }

    public void setReservationChangeCount(int reservationChangeCount) {
        this.reservationChangeCount = reservationChangeCount;
    }
    public void addPlusOne(){
        reservationChangeCount = reservationChangeCount+1;
    }
}
