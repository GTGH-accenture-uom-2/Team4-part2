package com.example.Team4.Services;

import com.example.Team4.Dtos.TimeslotDTO;
import com.example.Team4.Models.Timeslot;
import com.example.Team4.Models.VaccinationCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TimeslotService {
    List<Timeslot> timeslots = new ArrayList<>();
    @Autowired
    private VaccinationCenterService vaccinationCenterService;

    public List<Timeslot> addTimeslot(Timeslot timeslot) {
        timeslots.add(timeslot);
        return timeslots;
    }

    public List<Timeslot> getAllTimeslots() {
        return timeslots;
    }

    public List<Timeslot> searchTimeslots(int day, int month, int year) {
        LocalDate day1 = LocalDate.of(year, month, day);
        List<Timeslot> notFreeTimeslots = new ArrayList<>();
        for (Timeslot timeslot : timeslots) {
            LocalDate timeslotDate = LocalDate.of(timeslot.getYear(), timeslot.getMonth(), timeslot.getDay());
            if (timeslotDate.equals(day1)) {
                notFreeTimeslots.add(timeslot);
            }

        }
        return notFreeTimeslots;
    }

    //============================================================================
    public List<Timeslot> searchTimeslots(TimeslotDTO timeslotDto) {
        List<Timeslot> freeTimeslot = new ArrayList<>();
        List<VaccinationCenter> vaccinationCenters = vaccinationCenterService.getAllVaccinationCenters();        for(int i=0;i<vaccinationCenters.size();i++) {
            for (var elem : vaccinationCenters.get(i).getTimeslots()) {
                if (elem.isFree() && elem.getDay() == timeslotDto.getDay() && elem.getMonth() == timeslotDto.getMonth() && elem.getYear() == timeslotDto.getYear()) {
                    freeTimeslot.add(elem);
                }
            }
        }
        return freeTimeslot;
    }


//======================================================================================
}
