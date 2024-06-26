package com.example.Team4.Controllers;

import com.example.Team4.Dtos.TimeslotDTO2;
import com.example.Team4.Models.Timeslot;
import com.example.Team4.Services.TimeslotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/timeslot")
public class TimeslotController {
    @Autowired
    TimeslotService timeslotService;

    @PostMapping()
    public List<Timeslot> addTimeslot(@RequestBody Timeslot timeslot) {
        return timeslotService.addTimeslot(timeslot);
    }
    @GetMapping("/all")
    public List<Timeslot> getAllTimeslots() {
        return timeslotService.getAllTimeslots();
    }

    @GetMapping("/searchTimeslot")
    public List<Timeslot> searchTimeslots(@RequestParam int day, @RequestParam int month, @RequestParam int year) {
        return timeslotService.searchTimeslots(day, month, year);
    }

    @GetMapping("/permonth")
    public List<TimeslotDTO2> searchTimeslotsByMonth(@RequestParam Integer month){
        return timeslotService.searchTimeslotsByMonth(month);
    }

}



