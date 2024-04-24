package com.example.Team4.Controllers;

import com.example.Team4.Models.Timeslot;
import com.example.Team4.Models.Vaccination;
import com.example.Team4.Models.Vaccine;
import com.example.Team4.Services.VaccinationService;
import com.example.Team4.Util.QRCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.image.BufferedImage;
import java.util.List;

import static com.example.Team4.Util.QRCodeGenerator.convertImageToBytes;
import static com.example.Team4.Util.QRCodeGenerator.generateQRCodeImage;

@RestController
@RequestMapping("/vaccination")
public class VaccinationController {

    @Autowired
    VaccinationService vaccinationService;


    @GetMapping(value="/status", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<byte[]> showVaccinationStatusWithQRCode(@RequestParam Long amka) throws Exception {
        try {
            String expirationDate = vaccinationService.getExpirationDate(amka);
            String vaccinationStatus = vaccinationService.printVaccinationStatus(expirationDate);

            String qrCodeData = vaccinationStatus + " Expiration Date: " + expirationDate;

            BufferedImage qrCodeImage = QRCodeGenerator.generateQRCodeImage(qrCodeData, 300, 300);
            byte[] qrCodeBytes = QRCodeGenerator.convertImageToBytes(qrCodeImage);

            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(qrCodeBytes);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while generating QR code.Try to give another AMKA".getBytes());
        }



    }

    @PostMapping("/declaration")
    public Vaccination vaccinationDeclarations(@RequestParam Long timeslotCode,
                                                      @RequestParam Long insuredAmka,
                                                      @RequestParam Long expirDate,
                                               @RequestParam Long doctorAmka,
                                                @RequestParam String vaccineName){
        return vaccinationService.vaccinationDeclarations(timeslotCode,insuredAmka,doctorAmka,vaccineName);
    }


}
