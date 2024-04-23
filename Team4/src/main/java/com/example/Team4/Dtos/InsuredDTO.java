package com.example.Team4.Dtos;

import jakarta.persistence.Persistence;

public class InsuredDTO extends Persistence {
    private Long afm;
    private String birthdate;
    private String email;

    public InsuredDTO(Long afm, String birthdate, String email) {
        this.afm = afm;
        this.birthdate = birthdate;
        this.email = email;
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
}
