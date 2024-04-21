package com.example.Team4.Services;

import com.example.Team4.Models.Reservation;
import com.example.Team4.Models.Timeslot;
import com.example.Team4.Models.Vaccination;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class VaccinationService {

    List<Vaccination> vaccinations = new ArrayList<>();
    Vaccination vaccination;

    public static List<Vaccination> vaccinationDeclarations(Timeslot timeslot, Long amka, String expirDate) {

    }

    public List<Vaccination> addVaccination(Vaccination vaccination){
        vaccinations.add(vaccination);
        return vaccinations;
    }

    public String getExpirationDate(Long amka){
        if(vaccination.getInsured().getAmka()==amka){
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
