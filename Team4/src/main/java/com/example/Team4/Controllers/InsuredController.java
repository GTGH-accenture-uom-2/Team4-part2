package com.example.Team4.Controllers;

import com.example.Team4.Models.Insured;
import com.example.Team4.Models.Timeslot;
import com.example.Team4.Models.VaccinationCenter;
import com.example.Team4.Services.InsuredService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/insured")
//mipws na valoume path variable edw??
public class InsuredController {

    @Autowired
    InsuredService insuredService;

    @PostMapping()
    public List<Insured> addInsured(@RequestParam String name,
                                    @RequestParam String surname,
                                    @RequestParam Long amka,
                                    @RequestParam Long afm,
                                    @RequestParam String birthdate,
                                    @RequestParam String email) {
        return insuredService.addInsured(name,surname,amka,afm,birthdate,email);
    }


}
