package com.example.Team4.Services;

import com.example.Team4.Dtos.DoctorDTO;
import com.example.Team4.Dtos.InsuredDTO;
import com.example.Team4.Dtos.VaccinationDTO;
import com.example.Team4.Dtos.VaccineDTO;
import com.example.Team4.Models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VaccinationService {

    private final List<Vaccination> vaccinations;

    @Autowired
    public VaccinationService(List<Vaccination> vaccinations) {
        this.vaccinations = vaccinations;
    }

    @Autowired
    List<Timeslot> timeslots1;

    @Autowired
    List<Timeslot> timeslots2;

    @Autowired
    List<Doctor> doctors;
    @Autowired
    List<Vaccine> vaccines;

    List<Insured> insureds;

    @Autowired
    public void setInsuredList(List<Insured> insureds) {
        this.insureds = insureds;
    }

    public  VaccinationDTO vaccinationDeclarations(Long timeslotCode, Long insuredAmka, String vaccineName) {
        Insured selectedInsured = null;
        for(Insured insured: insureds)
            if(insured.getAmka().equals(insuredAmka)) {
                selectedInsured = insured;
                break;
            }
        if(selectedInsured==null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Insured with amka: " + insuredAmka + " doesn't exist");


        Timeslot timeslot = null;
        for(Timeslot tmslt:timeslots1){
            if(tmslt.getCode()==(timeslotCode)){
                timeslot = tmslt;
                break;
            }
        }

        for(Timeslot tmslt:timeslots2){
            if(tmslt.getCode()==(timeslotCode)){
                timeslot = tmslt;
                break;
            }
        }

        if(timeslot==null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Insured with timeslotCode : " + timeslotCode + " doesn't exist");


        int addMonths = 0;
        Vaccine selectedVaccine=null;
        for(Vaccine vaccine: vaccines) {
            if (vaccine.getName().equals(vaccineName)) {
                selectedVaccine = vaccine;
                addMonths = selectedVaccine.getMonthlyDuration();
                break;
            }
        }
        if(selectedVaccine==null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Vaccine: " + vaccineName + " doesn't exist");




        String vaccDate = timeslot.getFormattedDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(vaccDate, formatter);
        // Add months
        LocalDate expirationDate = date.plusMonths(addMonths);
        // Format the expiration date back to a string
        String formattedExpirationDate = expirationDate.format(formatter);
        InsuredDTO insureDtoObj = new InsuredDTO(selectedInsured.getName(),selectedInsured.getSurname(),
                selectedInsured.getAmka(),selectedInsured.getAfm(),selectedInsured.getBirthdate(),
                selectedInsured.getEmail());
        DoctorDTO doctordtoObj = new DoctorDTO(timeslot.getDoctor().getName(),
                timeslot.getDoctor().getSurname(),timeslot.getDoctor().getAmka());

        VaccineDTO vaccinedtoObj = new VaccineDTO(vaccineName);

        return  new VaccinationDTO(insureDtoObj,doctordtoObj,vaccDate,formattedExpirationDate,vaccinedtoObj);
    }

    public List<Vaccination> addVaccination(Vaccination vaccination){
        vaccinations.add(vaccination);
        return vaccinations;
    }

    public String getExpirationDate(Long amka){
        for(Vaccination vaccination: vaccinations) {
            if (vaccination.getInsured().getAmka().equals(amka))
                return vaccination.getExpirationDate();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Insured with amka: " + amka + " is not vaccinated");
    }

    public String printVaccinationStatus(String expirationDate){
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate expirationDateFormat = LocalDate.parse(expirationDate,formatter);

        if(currentDate.isAfter(expirationDateFormat))
            return "Your vaccination has expired!";
        else
            return "You have an active vaccination ";
    }



}
