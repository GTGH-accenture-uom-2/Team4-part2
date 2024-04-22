package com.example.Team4.Services;

import com.example.Team4.Models.VaccinationCenter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VaccinationCenterService {
    VaccinationCenter vacCenter;
    List<VaccinationCenter> vaccinationCenters= new ArrayList<>();


    public List<VaccinationCenter> addVaccinationCenter(VaccinationCenter vacCenter){
        vaccinationCenters.add(vacCenter);
        return vaccinationCenters;
    }
    public List<VaccinationCenter> getAllVaccinationCenters() {
        return vaccinationCenters;
    }
}

