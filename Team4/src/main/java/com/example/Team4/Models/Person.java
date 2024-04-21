package com.example.Team4.Models;

public abstract class Person {

    private String name;
    private String surname;
    private Long amka;


    public Person(String name, String surname, Long amka) {
        this.name = name;
        this.surname = surname;
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

    public Long getAmka() {
        return amka;
    }

    public void setAmka(Long amka) {
        this.amka = amka;
    }
}
