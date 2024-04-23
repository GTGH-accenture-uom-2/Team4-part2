package com.example.Team4.Services;

import com.example.Team4.Dtos.SelectReservationDTO;
import com.example.Team4.Models.Doctor;
import com.example.Team4.Models.Insured;
import com.example.Team4.Models.Reservation;
import com.example.Team4.Models.Timeslot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ReservationService {
    List<Reservation> reservations = new ArrayList<>();
    @Autowired
    List<Doctor> doctors;

    @Autowired
    List<Timeslot> timeslots1;

    @Autowired
    List<Timeslot> timeslots2;

    @Autowired
    private InsuredService insuredService;


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
    //δοκιμή για pagination (δεν χτυπά error)
    /*public List<Reservation> getUpcomingReservations(@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,

                                                     @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        LocalDate currentDay = LocalDate.now();
        Pageable pageable = (Pageable) PageRequest.of(pageNo, pageSize);
        int start = pageNo *pageSize;
        int end = Math.min(start + pageSize, reservations.size());
        List<Reservation> upcomingReservation = reservations
                .stream()
                .skip(start)
                .limit(end - start)
                .filter(reservation -> {
                    LocalDate reservationDate = LocalDate.of(reservation.getTimeslot().getYear(), reservation.getTimeslot().getMonth(), reservation.getTimeslot().getDay());
                    return reservationDate.isAfter(currentDay) || reservationDate.isEqual(currentDay);
                })
                .collect(Collectors.toList());
        return upcomingReservation;
    }

     */

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


    public Reservation changeReservation(Long insuredAmka,String timeslotCode, Long doctorAmka) {
        String insuredAmkaStr = String.valueOf(insuredAmka);
        String doctorAmkaStr = String.valueOf(doctorAmka);

        if (!insuredAmkaStr.matches("\\d+") || !doctorAmkaStr.matches("\\d+")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Insured AMKA and Doctor AMKA must be numeric values.");
        }

        Doctor validDoctor = doctors.stream()
                .filter(dct -> doctorAmka.equals(dct.getAmka()))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Doctor with AMKA: " + doctorAmka + " does not exist"));

        List<Timeslot> concatTimeslotLists = Stream.concat(timeslots1.stream(), timeslots2.stream())
                .collect(Collectors.toList());

        Timeslot newTimeslot = concatTimeslotLists.stream()
                .filter(tmsl -> timeslotCode.equals(tmsl.getCode()) && tmsl.isFree())
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Timeslot with code: " + timeslotCode + " is full or does not exist"));

        Reservation reservation = reservations.stream()
                .filter(r -> insuredAmka.equals(r.getInsured().getAmka()))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Insured with AMKA: " + insuredAmka + " does not exist"));

        if (reservation.getInsured().getReservationChangeCount() >= 3) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You can't change the reservation more than 2 times.");
        }

        reservation.getTimeslot().setFree(true);
        reservation.setTimeslot(newTimeslot);
        reservation.setDoctor(validDoctor);
        reservation.getInsured().addPlusOne();
        newTimeslot.setFree(false);

        return reservation;
    }
        /*String insuredAmkaStr = String.valueOf(insuredAmka);
        String doctorAmkaStr = String.valueOf(doctorAmka);
        if (!insuredAmkaStr.matches("\\d+") || !doctorAmkaStr.matches("\\d+")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Insured AMKA and Doctor AMKA must be numeric values.");
        }

        List<Timeslot> concatTimeslotLists = new ArrayList<>();
        concatTimeslotLists.addAll(timeslots1);
        concatTimeslotLists.addAll(timeslots2);
        Doctor validDoctor = null;
        Timeslot newTimeslot = null;
        for(Doctor dct:doctors){
            if(doctorAmka==dct.getAmka()){
                validDoctor = dct;
                break;
            }else{
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Doctor with amka: " +doctorAmka + "doesnt exist");
            }
        }
        for(Timeslot tmsl:concatTimeslotLists){
            if(timeslotCode.equals(tmsl.getCode())){
                if(tmsl.isFree()){
                newTimeslot = tmsl;
                break;} else {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "Timeslot with code: " +timeslotCode + "is full");
                }
            }else{
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        " Timeslot with code: " +timeslotCode + "doesnt exist");
            }
        }
        for(Reservation reservation:reservations){
            if(insuredAmka==reservation.getInsured().getAmka()){
                if(reservation.getInsured().getReservationChangeCount()<3){
                    reservation.getTimeslot().setFree(true);
                    reservation.setTimeslot(newTimeslot);
                    reservation.setDoctor(validDoctor);
                    reservation.getInsured().addPlusOne();
                    reservation.getTimeslot().setFree(false);
                    return reservation;
                }
                else{
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You can't change the reservation more than 2 times.");
                }
            }
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Insured with amka: " +insuredAmka + "doesnt exist");


        }
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                "Unexpected error occurred while processing the vaccination request");*/
    

 56-make-reservation-ckeck-if-availiable
    ppublic List<Reservation> selectReservation(SelectReservationDTO selectReservationDTO) {
        List<VaccinationCenter> vaccinationCenters = vaccinationCenterService.getAllVaccinationCenters();
        boolean flag= false;
        for (int i = 0; i < vaccinationCenters.size(); i++) {
            for (var elem : vaccinationCenters.get(i).getTimeslots()) {
                if (elem.isFree() && elem.getDay() == selectReservationDTO.getTimeslot().getDay() &&
                        elem.getMonth() == selectReservationDTO.getTimeslot().getMonth()
                        && elem.getYear() == selectReservationDTO.getTimeslot().getYear() &&
                        selectReservationDTO.getDoctor().getAmka() == elem.getDoctor().getAmka()) {
                    flag = true;
                }
            }
        }
        if (flag) {
            Insured newinsured = insuredService.findByAmka(selectReservationDTO.getAmka());
            if (newinsured == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error, wrong amka");
            }
            Reservation newReservation = new Reservation(newinsured, selectReservationDTO.getTimeslot());
            reservations.add(newReservation);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error, not availiable reservation");
        }
        return reservations;
    }
    }


  
}
