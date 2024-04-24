package com.example.Team4.Dtos;

import com.example.Team4.Models.Reservation;

public class ReservationDTO {

    private InsuredDTO insuredDTO;
    private TimeslotDTO2 timeslotDtoTwo;
    private DoctorDTO doctorDTO;

    public ReservationDTO(InsuredDTO insuredDTO, TimeslotDTO2 timeslotDtoTwo, DoctorDTO doctorDTO) {
        this.insuredDTO = insuredDTO;
        this.timeslotDtoTwo = timeslotDtoTwo;
        this.doctorDTO = doctorDTO;
    }

    public InsuredDTO getInsuredDTO() {
        return insuredDTO;
    }

    public void setInsuredDTO(InsuredDTO insuredDTO) {
        this.insuredDTO = insuredDTO;
    }

    public TimeslotDTO2 getTimeslotDtoTwo() {
        return timeslotDtoTwo;
    }

    public void setTimeslotDtoTwo(TimeslotDTO2 timeslotDtoTwo) {
        this.timeslotDtoTwo = timeslotDtoTwo;
    }

    public DoctorDTO getDoctorDTO() {
        return doctorDTO;
    }

    public void setDoctorDTO(DoctorDTO doctorDTO) {
        this.doctorDTO = doctorDTO;
    }
}
