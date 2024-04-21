package com.example.Team4.Services;

import com.example.Team4.Models.Timeslot;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TimeslotService {
    List<Timeslot> timeslots = new ArrayList<>();

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
}
