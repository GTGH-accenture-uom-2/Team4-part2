package com.example.Team4.Services;

import com.example.Team4.Dtos.*;
import com.example.Team4.Models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ReservationService {
    @Autowired
    List<Doctor> doctors;
    @Autowired
    List<Timeslot> timeslots1;

    @Autowired
    List<Timeslot> timeslots2;

    @Autowired
    Reservation reservation;

    @Autowired
    private List<Reservation> reservations;

    @Autowired
    private InsuredService insuredService;

    @Autowired
    private VaccinationCenterService vaccinationCenterService;

    @Autowired
    private TimeslotService timeslotService;

    public List<Reservation> addReservation(Reservation reservation) {
        reservations.add(reservation);
        return reservations;
    }

    public List<Reservation> getAllReservation() {
        return reservations;
    }

    public List<ReservationDTO> getUpcomingReservations(int pageNo, int pageSize) {
        LocalDate currentDay = LocalDate.now();
        int start = pageNo * pageSize;
        int end = Math.min(start + pageSize, reservations.size());

        List<ReservationDTO> upcomingReservation = new ArrayList<>();
        for (int i = start; i < end; i++) {
            Reservation reservation = reservations.get(i);
            LocalDate reservationDate = LocalDate.of(reservation.getTimeslot().getYear(), reservation.getTimeslot().getMonth(), reservation.getTimeslot().getDay());

            if (reservationDate.isAfter(currentDay) || reservationDate.isEqual(currentDay)) {
                InsuredDTO insuredObj = new InsuredDTO(reservation.getInsured().getName(), reservation.getInsured().getSurname(), reservation.getInsured().getAmka(), reservation.getInsured().getAfm(), reservation.getInsured().getBirthdate(), reservation.getInsured().getEmail());
                TimeslotDTO2 timeslotObj = new TimeslotDTO2(reservation.getTimeslot().getDay(), reservation.getTimeslot().getMonth(), reservation.getTimeslot().getYear(), reservation.getTimeslot().getHour(), reservation.getTimeslot().getMinutes());
                DoctorDTO doctorObj = new DoctorDTO(reservation.getDoctor().getName(), reservation.getDoctor().getSurname(), reservation.getDoctor().getAmka());
                upcomingReservation.add(new ReservationDTO(insuredObj, timeslotObj, doctorObj));
            }
            if (upcomingReservation.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There are not upcoming reservations");

            }
        }
        return upcomingReservation;
    }

    public List<ReservationDTO> getReservationsByDay(int day, int month, int year){
        List<ReservationDTO> reservationByDay = new ArrayList<>();
        LocalDate reservationDay = LocalDate.of(year,month,day);
        for(Reservation reservation : reservations) {
            LocalDate reservationDate = LocalDate.of(reservation.getTimeslot().getYear(),reservation.getTimeslot().getMonth(),reservation.getTimeslot().getDay());
            if(reservationDate.equals(reservationDay)) {
                InsuredDTO insuredObj = new InsuredDTO(reservation.getInsured().getName(), reservation.getInsured().getSurname(), reservation.getInsured().getAmka(), reservation.getInsured().getAfm(), reservation.getInsured().getBirthdate(), reservation.getInsured().getEmail());
                TimeslotDTO2 timeslotObj = new TimeslotDTO2(reservation.getTimeslot().getDay(), reservation.getTimeslot().getMonth(), reservation.getTimeslot().getYear(), reservation.getTimeslot().getHour(), reservation.getTimeslot().getMinutes());
                DoctorDTO doctorObj = new DoctorDTO(reservation.getDoctor().getName(), reservation.getDoctor().getSurname(), reservation.getDoctor().getAmka());
                reservationByDay.add(new ReservationDTO(insuredObj,timeslotObj,doctorObj));
            }
        }
        return reservationByDay;
    }


    public ReservationDTO changeReservation(Long insuredAmka,Long timeslotCode) {
        reservations.add(reservation);
        String insuredAmkaStr = String.valueOf(insuredAmka);
        System.out.println(insuredAmkaStr);


        if (!insuredAmkaStr.matches("\\d+")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Insured AMKA  must be numeric values.");
        }

        List<Timeslot> concatTimeslotLists = Stream.concat(timeslots1.stream(), timeslots2.stream())
                .collect(Collectors.toList());

        System.out.println(concatTimeslotLists);
        System.out.println(timeslotCode==3);
        System.out.println(timeslotCode.getClass().getName());
        Timeslot newTimeslot = concatTimeslotLists.stream()
                .filter(tmsl -> (timeslotCode==(tmsl.getCode())) && tmsl.isFree())
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Timeslot with code: " + timeslotCode + " is full or does not exist"));



        Reservation reservation = reservations.stream()
                .filter(r -> (insuredAmka.equals(r.getInsured().getAmka())))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Insured with AMKA: " + insuredAmka + " does not exist"));

        System.out.println("I find the insured");

        if (reservation.getInsured().getReservationChangeCount() >= 2) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You can't change the reservation more than 2 times.");
        }

        reservation.getTimeslot().setFree(true);
        reservation.setTimeslot(newTimeslot);
        reservation.setDoctor(newTimeslot.getDoctor());
        reservation.getInsured().addPlusOne();
        newTimeslot.setFree(false);

        InsuredDTO insuredDtoObj = new InsuredDTO(reservation.getInsured().getName(),
                reservation.getInsured().getSurname(),reservation.getInsured().getAmka(),
                reservation.getInsured().getAfm(),reservation.getInsured().getBirthdate(),
                reservation.getInsured().getEmail());
        TimeslotDTO2 timeslotDtoTwoObj = new TimeslotDTO2(reservation.getTimeslot().getDay(),
                reservation.getTimeslot().getMonth(),reservation.getTimeslot().getYear()
                ,reservation.getTimeslot().getHour(),
                reservation.getTimeslot().getMinutes());
        DoctorDTO dctDto = new DoctorDTO(reservation.getDoctor().getName(),
                reservation.getDoctor().getSurname(),reservation.getDoctor().getAmka());


        return new ReservationDTO(insuredDtoObj,timeslotDtoTwoObj,dctDto);
    }

    public List<Reservation> selectReservation(SelectReservationDTO selectReservationDTO) {
        boolean flag= false;
        for (Timeslot tmsl : timeslotService.getTimeslots1()) {
            if (tmsl.isFree() && tmsl.getDay() == selectReservationDTO.getTimeslot().getDay() &&
                    tmsl.getMonth() == selectReservationDTO.getTimeslot().getMonth()
                    && tmsl.getYear() == selectReservationDTO.getTimeslot().getYear() &&
                    Objects.equals(selectReservationDTO.getDoctor().getAmka(), tmsl.getDoctor().getAmka())) {
                flag = true;
                tmsl.setFree(false);
            }
        }
        for (Timeslot tmsl : timeslotService.getTimeslots2()) {
            if (tmsl.isFree() && tmsl.getDay() == selectReservationDTO.getTimeslot().getDay() &&
                    tmsl.getMonth() == selectReservationDTO.getTimeslot().getMonth()
                    && tmsl.getYear() == selectReservationDTO.getTimeslot().getYear() &&
                    Objects.equals(selectReservationDTO.getDoctor().getAmka(), tmsl.getDoctor().getAmka())
                    )
            {
                flag = true;
                tmsl.setFree(false);
            }
        }
        if (flag) {
            Insured newinsured = insuredService.findByAmka(selectReservationDTO.getAmka());
            if (newinsured == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error, wrong amka");
            }
            Reservation newReservation = new Reservation(newinsured, selectReservationDTO.getTimeslot(), selectReservationDTO.getDoctor());
            reservations.add(newReservation);

        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error, not availiable reservation");
        }
        return reservations;
    }

    public Reservation getReservation() {
        return reservation;
    }
}


  

