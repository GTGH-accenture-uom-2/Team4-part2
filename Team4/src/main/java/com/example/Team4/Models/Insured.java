package com.example.Team4.Models;

public class Insured extends Person{
    //den evalla vaccination kai vacCenter gia na to doume stin poreia
    private Long afm;
    private String birthdate;
    private String email;
    private int reservationChangeCount;

    public Insured(String name, String surname, Long amka,Long afm, String birthdate, String email, int reservationChangeCount) {
        super(name, surname, amka);
        this.afm = afm;
        this.birthdate = birthdate;
        this.email = email;
        reservationChangeCount = 0;
    }

    public Insured(String name, String surname, Long amka, String email, Long afm, String birthdate ) {
        super(name, surname, amka);
        this.email = email;
        this.afm = afm;
        this.birthdate = birthdate;

    }

    public Long getAfm() {
        return afm;
    }

    public void setAfm(Long afm) {
        this.afm = afm;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
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
