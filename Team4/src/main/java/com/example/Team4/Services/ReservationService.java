package com.example.Team4.Services;

import com.example.Team4.Models.Doctor;
import com.example.Team4.Models.Reservation;
import com.example.Team4.Models.Timeslot;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {
    List<Reservation> reservations = new ArrayList<>();
    public List<Reservation> addReservation(Reservation reservation) {
        reservations.add(reservation);
        return reservations;
    }
    public List<Reservation> getAllReservation() {
        return reservations;
    }


    public Reservation changeReservation(Long amka, Timeslot timeslot, Doctor doctor) {
        for(Reservation reservation:reservations){
            if(amka==reservation.getInsured().getAmka()){
                if(reservation.getInsured().getReservationChangeCount()<3){
                    Reservation res = this.makeReservation(amka,timeslot,doctor);
                    reservation.getInsured().addPlusOne();
                    return res;
                }
                else{
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You can't change the reservation more than 2 times.");
                }
            }
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Insured with amka: " +amka + "doesnt exist");


        }
    }
}
