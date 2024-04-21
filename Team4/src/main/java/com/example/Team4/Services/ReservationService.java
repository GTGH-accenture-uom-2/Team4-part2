package com.example.Team4.Services;

import com.example.Team4.Models.Reservation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {
    List<Reservation> reservations = new ArrayList<>();
    public List<Reservation> addReservation(Reservation reservation) {
        reservations.add(reservation);
        return reservations;
    }

}
