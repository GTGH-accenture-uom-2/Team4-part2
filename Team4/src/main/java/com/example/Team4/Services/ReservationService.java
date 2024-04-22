package com.example.Team4.Services;

import com.example.Team4.Models.Doctor;
import com.example.Team4.Models.Reservation;
import com.example.Team4.Models.Timeslot;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
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

    public List<Reservation> getUpcomingReservation() {
        LocalDate currentDay = LocalDate.now();

        List<Reservation> upcomingReservations = new ArrayList<>();
        for (Reservation reservation : reservations) {
            LocalDate reservationDate = LocalDate.of(reservation.getTimeslot().getYear(), reservation.getTimeslot().getMonth(),reservation.getTimeslot().getDay());
            if (reservationDate.isAfter(currentDay) || reservationDate.isEqual(currentDay))
                upcomingReservations.add(reservation);
        }
        return upcomingReservations;
    }

    public List<Reservation> getReservationsByDay(@RequestParam int day) {
        LocalDate currentDate = LocalDate.now();
        LocalDate requestDate = currentDate.withDayOfMonth(day);
        List<Reservation> ReservationByDay = new ArrayList<>();
        for (Reservation reservation : reservations) {
            LocalDate reservationDate = LocalDate.of(reservation.getTimeslot().getYear(), reservation.getTimeslot().getMonth(),reservation.getTimeslot().getDay());
            if(reservationDate.equals(requestDate)) {
                ReservationByDay.add(reservation);
            }
        }
        return ReservationByDay;
    }


    public Reservation changeReservation(Long amka, Timeslot timeslot, Doctor doctor) {
        for(Reservation reservation:reservations){
            if(amka==reservation.getInsured().getAmka()){
                if(reservation.getInsured().getReservationChangeCount()<3){
                    reservation.getTimeslot().setFree(true);
                    reservation.setTimeslot(timeslot);
                    reservation.setDoctor(doctor);
                    reservation.getInsured().addPlusOne();
                    reservation.getTimeslot().setFree(false);
                    return reservation;
                }
                else{
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You can't change the reservation more than 2 times.");
                }
            }
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Insured with amka: " +amka + "doesnt exist");


        }
        return null;
    }
}
