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
    List<Timeslot> timeslots = new ArrayList<>();//xreiazetai??
    @Autowired
    private VaccinationCenterService vaccinationCenterService;
    @Autowired
    List<Timeslot> timeslots1;
    @Autowired
    List<Timeslot> timeslots2;


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
        for (Timeslot timeslot : timeslots1) {
            if (timeslot.isFree() &&
                    timeslot.getDay() == timeslotDto.getDay() &&
                    timeslot.getMonth() == timeslotDto.getMonth() &&
                    timeslot.getYear() == timeslotDto.getYear()) {
                freeTimeslot.add(timeslot);
            }

        }
        for (Timeslot timeslot : timeslots2) {
            if (timeslot.isFree() &&
                    timeslot.getDay() == timeslotDto.getDay() &&
                    timeslot.getMonth() == timeslotDto.getMonth()
                    && timeslot.getYear() == timeslotDto.getYear()) {
                freeTimeslot.add(timeslot);
            }

        }
        if (freeTimeslot.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error, Not Available");
        return freeTimeslot;
    }

    public List<Timeslot> getTimeslots1() {
        return timeslots1;
    }

    public void setTimeslots1(List<Timeslot> timeslots1) {
        this.timeslots1 = timeslots1;
    }

    public List<Timeslot> getTimeslots2() {
        return timeslots2;
    }

    public void setTimeslots2(List<Timeslot> timeslots2) {
        this.timeslots2 = timeslots2;
    }
}
