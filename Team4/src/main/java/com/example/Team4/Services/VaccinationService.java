package com.example.Team4.Services;

import com.example.Team4.Models.Insured;
import com.example.Team4.Models.Reservation;
import com.example.Team4.Models.Timeslot;
import com.example.Team4.Models.Vaccination;
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

    static List<Vaccination> vaccinations = new ArrayList<>();
    Vaccination vaccination;

    @Autowired
     static List<Timeslot> timeslots1;

    @Autowired
     static List<Timeslot> timeslots2;
    private static   List<Insured> insureds; //gemisame sta static

    @Autowired
    public void setInsuredList(List<Insured> insureds) {
        this.insureds = insureds;
    }

    public static List<Vaccination> vaccinationDeclarations(String timeslotCode, Long amka, String expirDate) {
        Optional<Insured> selectedInsured = insureds.stream().filter(x->x.getAmka().equals(amka)).findFirst();
        Timeslot timeslot = null;
        boolean state1= false;
        boolean state2=false;
        for(Timeslot tmslt:timeslots1){
            if(tmslt.getCode().equals(timeslotCode)){
                timeslot = tmslt;
                state1=true;
            }
        }
        for(Timeslot tmslt:timeslots2){
            if(tmslt.getCode().equals(timeslotCode)){
                timeslot = tmslt;
                state2 = true;
            }
        }
        if((state1||state2)&&(selectedInsured.isPresent())){
            String vaccDate = timeslot.getFormattedDate();
            Vaccination vacc = new Vaccination(selectedInsured.get(),vaccDate,expirDate);
            vaccinations.add(vacc);
            return vaccinations;
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Insured with Timeslot code: " +timeslotCode + "doesnt exist");
        }





        //return new Vaccination(selectedInsured,vaccDate,expirDate);
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
