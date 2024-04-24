package com.example.Team4.Controllers;

import com.example.Team4.Dtos.TimeslotDTO;
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
    /*@GetMapping()
    public List<Timeslot> searchTimeslots(@RequestParam int day,
                                          @RequestParam int month,
                                          @RequestParam int year){
        return timeslotService.searchTimeslots(day,month,year);
    }

     */


    @GetMapping("/searchTimeslot")
    public List<Timeslot> searchTimeslots(@RequestBody TimeslotDTO timeslotDto) {
        return timeslotService.searchTimeslots(timeslotDto);
    }

    @GetMapping("/permonth")
    public List<TimeslotDTO2> searchTimeslotsByMonth(@RequestParam Integer month){
        List<TimeslotDTO2> timeslotDTOS = new ArrayList<>();
        for(Timeslot timeslot: timeslotService.getTimeslots1()) {
            if(timeslot.getMonth() == month) {
                timeslotDTOS.add(new TimeslotDTO2(timeslot.getDay(), timeslot.getMonth(), timeslot.getYear(),
                        timeslot.getHour(), timeslot.getMinutes()));
            }
        }
        for(Timeslot timeslot: timeslotService.getTimeslots2()) {
            if(timeslot.getMonth() == month) {
                timeslotDTOS.add(new TimeslotDTO2(timeslot.getDay(), timeslot.getMonth(), timeslot.getYear(),
                        timeslot.getHour(), timeslot.getMinutes()));
            }
        }
        return timeslotDTOS;
    }

}



