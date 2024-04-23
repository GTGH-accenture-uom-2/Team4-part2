package com.example.Team4.Services;

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

    List<Vaccination> vaccinations = new ArrayList<>();
    Vaccination vaccination;

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

    public  Vaccination vaccinationDeclarations(String timeslotCode, Long insuredAmka, Long doctorAmka, String vaccineName) {
        Insured selectedInsured = null;
        for(Insured insured: insureds)
            if(insured.getAmka().equals(insuredAmka)) {
                selectedInsured = insured;
                break;
            }
        if(selectedInsured==null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Insured with amka: " + insuredAmka + " doesn't exist");


        Timeslot timeslot = null;
        Doctor doctor = null;
        boolean state1= false;
        boolean state2=false;
        boolean state3= false;
        for(Timeslot tmslt:timeslots1){
            if(tmslt.getCode().equals(timeslotCode)){
                timeslot = tmslt;
                state1=true;
                break;
            }
        }
        for(Timeslot tmslt:timeslots2){
            if(tmslt.getCode().equals(timeslotCode)){
                timeslot = tmslt;
                state2 = true;
                break;
            }
        }

        for(Doctor dcr:doctors){
            if(dcr.getAmka()==doctorAmka){
                doctor = dcr;
                state3 = true;
            }
        }
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

        

        if((state1||state2)&&(state3)&&addMonths>0){
            String vaccDate = timeslot.getFormattedDate();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = LocalDate.parse(vaccDate, formatter);
            // Add months
            LocalDate expirationDate = date.plusMonths(addMonths);
            // Format the expiration date back to a string
            String formattedExpirationDate = expirationDate.format(formatter);
            return  new Vaccination(selectedInsured,doctor,vaccDate,formattedExpirationDate,selectedVaccine);
        } else if(state1==false||state2==false) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Timeslot with code: " +timeslotCode + "doesnt exist");
        } else if (state3==false) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Doctor with amka : " +doctorAmka + "doesnt exist");
        }else{
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Unexpected error occurred while processing the vaccination request");
        }

    }

    public List<Vaccination> addVaccination(Vaccination vaccination){
        vaccinations.add(vaccination);
        return vaccinations;
    }

    public String getExpirationDate(Long amka){
        if(!vaccination.getInsured().getAmka().equals(amka)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Insured with amka: " +amka + "doesnt exist");
        }

        return vaccination.getExpirationDate();
    }

    public String printVaccinationStatus(String expirationDate){
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate expirationDateFormat = LocalDate.parse(expirationDate,formatter);

        if(currentDate.isAfter(expirationDateFormat))
            return "Your vaccination has expired!";
        else
            return "You have an active vaccination until: " +expirationDate;
    }



}
