package com.example.Team4.Services;

import com.example.Team4.Models.Reservation;
import com.example.Team4.Models.Timeslot;
import com.example.Team4.Models.VaccinationCenter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class InsuredService {
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
