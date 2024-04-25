package com.example.Team4.Services;

import com.example.Team4.Models.Doctor;
import com.example.Team4.Models.Insured;
import com.example.Team4.Models.Timeslot;
import com.example.Team4.Models.Vaccination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorService {
    @Autowired
    List<Timeslot> timeslots1;

    @Autowired
    List<Timeslot> timeslots2;

    @Autowired
    ArrayList<Doctor> doctors;

    @Autowired
    private TimeslotService timeslotService;

    //List<Doctor> doctors = new ArrayList<>();

    public List<Doctor> addDoctor(Doctor doctor){
        doctors.add(doctor);
        return doctors;
    }

    public Doctor findByAmka(long amka){
        for (var elem: doctors){
            if (elem.getAmka() == amka)
                return elem;
            else
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error, Insured not found");
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error, Insured not found");
    }


    public void printPdf(Long amka) throws IOException {
        Doctor doctor;
        if((doctor=findByAmka(amka))!=null){
            List<Timeslot> timeslots = timeslotService.getTimeslotsByDoctor(doctor);
            System.out.println(timeslots);
            try (PDDocument document = new PDDocument()) {
                PDPage page = new PDPage();
                document.addPage(page);

                try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                    contentStream.setFont(PDType1Font.HELVETICA, 7);
                    contentStream.beginText();
                    contentStream.newLineAtOffset(25, 700);

                    for (Timeslot timeslot : timeslots) {
                        contentStream.showText(timeslot.toString());
                        contentStream.newLine();
                    }

                    contentStream.endText();
                }

                document.save("vaccination.pdf");
            }
        }

    }
    }

