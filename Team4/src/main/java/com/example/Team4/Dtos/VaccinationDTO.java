package com.example.Team4.Dtos;

public class VaccinationDTO {
    private InsuredDTO insured;
    private DoctorDTO doctor;
    private String vaccinationDate;
    private String expirationDate;
    private VaccineDTO vaccine;

    public VaccinationDTO(InsuredDTO insured, DoctorDTO doctor, String vaccinationDate, String expirationDate, VaccineDTO vaccine) {
        this.insured = insured;
        this.doctor = doctor;
        this.vaccinationDate = vaccinationDate;
        this.expirationDate = expirationDate;
        this.vaccine = vaccine;
    }

    public InsuredDTO getInsured() {
        return insured;
    }

    public DoctorDTO getDoctor() {
        return doctor;
    }

    public String getVaccinationDate() {
        return vaccinationDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public VaccineDTO getVaccine() {
        return vaccine;
    }

    public void setInsured(InsuredDTO insured) {
        this.insured = insured;
    }

    public void setDoctor(DoctorDTO doctor) {
        this.doctor = doctor;
    }

    public void setVaccinationDate(String vaccinationDate) {
        this.vaccinationDate = vaccinationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setVaccine(VaccineDTO vaccine) {
        this.vaccine = vaccine;
    }
}

