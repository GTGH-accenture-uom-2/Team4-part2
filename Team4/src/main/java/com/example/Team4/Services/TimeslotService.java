package com.example.Team4.Services;

import com.example.Team4.Dtos.TimeslotDTO;
import com.example.Team4.Models.Timeslot;
import com.example.Team4.Models.VaccinationCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    /*public List<Timeslot> searchTimeslots(int day, int month, int year) {
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

     */

    public List<Timeslot> searchTimeslots(TimeslotDTO timeslotDto) {
        List<Timeslot> freeTimeslot = new ArrayList<>();
            for (var i=0; i<= timeslots.size(); i++){
                if (timeslots.get(i).isFree() &&
                        timeslots.get(i).getDay() == timeslotDto.getDay() &&
                        timeslots.get(i).getMonth() == timeslotDto.getMonth()
                        && timeslots.get(i).getYear() == timeslotDto.getYear()) {
                    freeTimeslot.add(timeslots.get(i));
                }
                if (!freeTimeslot.isEmpty())
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error, Not Available");
            }

        return freeTimeslot;
    }
}
