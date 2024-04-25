package com.example.Team4.Services;

import com.example.Team4.Dtos.TimeslotDTO;
import com.example.Team4.Dtos.TimeslotDTO2;
import com.example.Team4.Models.Doctor;
import com.example.Team4.Models.Timeslot;
import com.example.Team4.Models.VaccinationCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimeslotService {
    List<Timeslot> timeslots = new ArrayList<>();
    @Autowired
    private VaccinationCenterService vaccinationCenterService;
    @Autowired
    private ArrayList<Timeslot> timeslots1;
    @Autowired
    private ArrayList<Timeslot> timeslots2;

    @Autowired
    public TimeslotService(ArrayList<Timeslot> timeslots1, ArrayList<Timeslot> timeslots2) {
        this.timeslots1 = timeslots1;
        this.timeslots2 = timeslots2;
    }


    public List<Timeslot> addTimeslot(Timeslot timeslot) {
        timeslots.add(timeslot);
        return timeslots;
    }

    public List<Timeslot> getAllTimeslots() {
        return timeslots;
    }

    public List<Timeslot> searchTimeslots(int day, int month, int year) {
        List<Timeslot> freeTimeslot = new ArrayList<>();
        for (Timeslot timeslot : timeslots1) {
            if (timeslot.isFree() && timeslot.getDay() == day && timeslot.getMonth() == month && timeslot.getYear() == year) {
                freeTimeslot.add(timeslot);
            }

        }
        for (Timeslot timeslot : timeslots2) {
            if (timeslot.isFree() && timeslot.getDay() == day && timeslot.getMonth() == month && timeslot.getYear() == year) {
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
        this.timeslots1 = (ArrayList<Timeslot>) timeslots1;
    }

    public List<Timeslot> getTimeslots2() {
        return timeslots2;
    }

    public void setTimeslots2(List<Timeslot> timeslots2) {
        this.timeslots2 = (ArrayList<Timeslot>) timeslots2;
    }

    public List<Timeslot> getTimeslotsByDoctor(Doctor doctor) {
        List<Timeslot> DocTimeslots = new ArrayList<>();
        for (var elem : timeslots1) {
            if (elem.getDay() == LocalDate.now().getDayOfMonth()
                    && elem.getMonth() == LocalDate.now().getMonthValue()
                    && elem.getYear() == LocalDate.now().getYear()
                    && elem.getDoctor().getAmka() == doctor.getAmka()// erroooooooooooooooor
                    && !elem.isFree()) {
                DocTimeslots.add(elem);
            }
        }
        for (var elem : timeslots2) {
            if (elem.getDay() == LocalDate.now().getDayOfMonth()
                    && elem.getMonth() == LocalDate.now().getMonthValue()
                    && elem.getYear() == LocalDate.now().getYear()
                    && elem.getDoctor().getAmka() == doctor.getAmka()
                    && !elem.isFree()) {
                DocTimeslots.add(elem);
            }
        }

        return DocTimeslots;
    }

    public List<TimeslotDTO2>searchTimeslotsByMonth(int month){
        List<TimeslotDTO2> timeslotDTOS = new ArrayList<>();
        for(Timeslot timeslot: timeslots1){
            if(timeslot.isFree()&&timeslot.getMonth() == month) {
                timeslotDTOS.add(new TimeslotDTO2(timeslot.getDay(), timeslot.getMonth(), timeslot.getYear(),
                        timeslot.getHour(), timeslot.getMinutes()));
            }
        }
        for(Timeslot timeslot: timeslots2){
            if(timeslot.isFree()&&timeslot.getMonth() == month) {
                timeslotDTOS.add(new TimeslotDTO2(timeslot.getDay(), timeslot.getMonth(), timeslot.getYear(),
                        timeslot.getHour(), timeslot.getMinutes()));
            }
        }
        return timeslotDTOS;
    }
}