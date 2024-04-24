package com.example.Team4.Controllers;

import com.example.Team4.Dtos.ReservationDTO;
import com.example.Team4.Dtos.SelectReservationDTO;
import com.example.Team4.Models.Doctor;
import com.example.Team4.Models.Reservation;
import com.example.Team4.Models.Timeslot;
import com.example.Team4.Services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    @Autowired
    ReservationService reservationService;

    /*@GetMapping()
    public Reservation getReservation(){
        return  reservationService.getReservation();
    }*/
    @PostMapping()
    public List<Reservation> addReservation(@RequestBody Reservation reservation) {
        return reservationService.addReservation(reservation);
    }
    @GetMapping("/all")
    public List<Reservation> getAllReservations(){
        return reservationService.getAllReservation();
    }
    @GetMapping("/upcoming")
    public List<ReservationDTO> getUpcomingReservations() {
        return reservationService.getUpcomingReservation();
    }
    /*@GetMapping()
    public List<Reservation> getUpcomingReservations(@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                                                     @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        return reservationService.getUpcomingReservations(pageNo,pageSize);
    }

     */

    @GetMapping("/byday")
    public List<ReservationDTO> getReservationsByDay(@RequestParam int day) {
       return reservationService.getReservationsByDay(day);
    }
    @PutMapping("/ChangeReservation")
    public Reservation changeReservation(@RequestParam Long insuredAmka,
                                         @RequestParam Long timeslotCode,
                                         @RequestParam Long doctorAmka ){
        return reservationService.changeReservation(insuredAmka,timeslotCode,doctorAmka);

    }

    @PostMapping("/selectReservation")
    public List<Reservation> selectReservation(@RequestBody SelectReservationDTO selectReservationDTO){
        return reservationService.selectReservation(selectReservationDTO);
    }

}
