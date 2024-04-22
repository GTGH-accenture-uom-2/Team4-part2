package com.example.Team4.Controllers;

import com.example.Team4.Models.VaccinationCenter;
import com.example.Team4.Services.VaccinationCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vaccinationCenter")
public class VaccinationCenterController {
    @Autowired
    VaccinationCenterService vaccinationCenterService;

    @GetMapping("/all")
    public List<VaccinationCenter> getAllVaccinationCenters() {
        return vaccinationCenterService.getAllVaccinationCenters();
    }
}

