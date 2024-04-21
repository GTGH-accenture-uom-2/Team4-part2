package com.example.Team4.Controllers;

import com.example.Team4.Models.Timeslot;
import com.example.Team4.Models.VaccinationCenter;
import com.example.Team4.Services.InsuredService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/insured")
//mipws na valoume path variable edw??
public class InsuredController {

    @Autowired
    InsuredService insuredService;

    @GetMapping("searchTimeslot")
    public Timeslot insuredSearchTimeslot (@RequestParam int day,
                                           @RequestParam int month,
                                           @RequestParam int year){
        //το βαζω για να μην βγαζει error στην insuredservice
        // αλλα δεν μου βγαζει νοημα να βρισκεται εδω να βρω κατι αλλο
        //και να βγαλω το vaccenter απο την μεθοδο
        VaccinationCenter vaccinationCenter = new VaccinationCenter("acode", "anaddress");
        return insuredService.searchTimeslot(day, month, year, vaccinationCenter);
    }
    @PostMapping("/selectTimeslot")
    public Timeslot InsuredSelectTimeslot (@RequestParam Long insuredAmka,
                                           @RequestBody Timeslot timeslot,
                                           @RequestParam Long doctorAmka){
        return insuredService.selectTimeslot(insuredAmka, timeslot, doctorAmka);
    }



}
