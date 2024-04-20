package com.example.Team4.Controllers;

import com.example.Team4.Models.Timeslot;
import com.example.Team4.Services.InsuredService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/insured")
public class InsuredController {

    @Autowired
    InsuredService insuredService;

    @GetMapping("searchTimeslot")
    public Timeslot insuredSearchTimeslot (@RequestParam int day,
                                           @RequestParam int month,
                                           @RequestParam int year){
        return insuredService.searchTimeslot(day, month, year);
    }
    @GetMapping("/selectTimeslot")
    public Timeslot InsuredSelectTimeslot (@RequestParam Long insuredAmka,
                                           @RequestBody Timeslot timeslot,
                                           @RequestParam Long doctorAmka){
        return insuredService.selectTimeslot(insuredAmka, timeslot, doctorAmka);
    }

}
