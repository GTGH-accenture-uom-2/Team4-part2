package com.example.Team4.Controllers;

import com.example.Team4.Models.Vaccination;
import com.example.Team4.Services.VaccinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vaccination")
public class VaccinationController {

    @Autowired
    VaccinationService vaccinationService;


    @GetMapping("/status")
    public String showVaccinationStatus (@RequestParam Long amka){
        return vaccinationService.printVaccinationStatus(vaccinationService.getExpirationDate(amka));
    }



}
