package com.example.Team4.Dtos;

public class ReservationDTO {

    private InsuredDTO insuredDTO;
    private TimeslotDTO timeslotDTO;
    private DoctorDTO doctorDTO;

    public ReservationDTO(InsuredDTO insuredDTO, TimeslotDTO timeslotDTO, DoctorDTO doctorDTO) {
        this.insuredDTO = insuredDTO;
        this.timeslotDTO = timeslotDTO;
        this.doctorDTO = doctorDTO;
    }

    public InsuredDTO getInsuredDTO() {
        return insuredDTO;
    }

    public void setInsuredDTO(InsuredDTO insuredDTO) {
        this.insuredDTO = insuredDTO;
    }

    public TimeslotDTO getTimeslotDTO() {
        return timeslotDTO;
    }

    public void setTimeslotDTO(TimeslotDTO timeslotDTO) {
        this.timeslotDTO = timeslotDTO;
    }

    public DoctorDTO getDoctorDTO() {
        return doctorDTO;
    }

    public void setDoctorDTO(DoctorDTO doctorDTO) {
        this.doctorDTO = doctorDTO;
    }
}
