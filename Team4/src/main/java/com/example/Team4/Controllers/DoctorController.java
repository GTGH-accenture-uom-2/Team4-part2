package com.example.Team4.Controllers;

import com.example.Team4.Models.Doctor;
import com.example.Team4.Services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @PostMapping
    public List<Doctor> addDoctor(@RequestBody Doctor doctor){
        return doctorService.addDoctor(doctor);
    }

    @PostMapping("/reservationsPDF")
    public void printPdf(@RequestParam Long amka) throws IOException {
        doctorService.printPdf(amka);
    }
}
