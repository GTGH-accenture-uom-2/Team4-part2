package com.example.Team4.Services;

import com.example.Team4.Models.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InsuredService {

    List<Insured> insureds = new ArrayList<>();

    public List<Insured> addInsured(Insured insured){
        insureds.add(insured);
        return insureds;
    }


    public Timeslot searchTimeslot(int day, int month, int year, VaccinationCenter vaccinationCenter) {
        ArrayList<Timeslot> allTimeslots = vaccinationCenter.getTimeslots();
        //ektupwsi twn free
        for (Timeslot timeslot : allTimeslots) {
            if (timeslot.getYear() == year && timeslot.getMonth() == month &&
                    timeslot.getDay() == day && timeslot.isFree()) {
                System.out.println(timeslot);
            }
        }
        return null;

    }

    public Reservation selectTimeslot(Long insuredAmka, Timeslot timeslot, Long doctorAmka) {
        return null;

    }
}
