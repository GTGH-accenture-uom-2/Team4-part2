package com.example.Team4.Controllers;

import com.example.Team4.Models.Doctor;
import com.example.Team4.Models.Reservation;
import com.example.Team4.Models.Timeslot;
import com.example.Team4.Services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    @Autowired
    ReservationService reservationService;
    @PostMapping()
    public List<Reservation> addReservation(@RequestBody Reservation reservation) {
        return reservationService.addReservation(reservation);
    }

    @PutMapping("/ChangeReservation")
    public Reservation changeReservation(@RequestParam Long amka,
                                         @RequestBody Timeslot timeslot,
                                         @RequestBody Doctor doctor ){
        return reservationService.changeReservation(amka,timeslot,doctor);

    }

}
