package com.example.Team4.Services;

import com.example.Team4.Models.Doctor;
import com.example.Team4.Models.Vaccination;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorService {

    List<Doctor> doctors = new ArrayList<>();

    public List<Doctor> addDoctor(Doctor doctor){
        doctors.add(doctor);
        return doctors;
    }


}
