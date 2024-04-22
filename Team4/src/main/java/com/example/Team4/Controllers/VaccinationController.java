package com.example.Team4.Controllers;

import com.example.Team4.Models.Timeslot;
import com.example.Team4.Models.Vaccination;
import com.example.Team4.Services.VaccinationService;
import com.example.Team4.Util.QRCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
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


    /*
    @GetMapping("/status")
    public String showVaccinationStatus (@RequestParam Long amka){
        return vaccinationService.printVaccinationStatus(vaccinationService.getExpirationDate(amka));
    }

     */

    @GetMapping(value="/status", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> showVaccinationStatusWithQRCode(@RequestParam Long amka) throws Exception {
        String expirationDate = vaccinationService.getExpirationDate(amka);
        String vaccinationStatus = vaccinationService.printVaccinationStatus(expirationDate);

        String qrCodeData = vaccinationStatus + " Expiration Date: " + expirationDate;

        BufferedImage qrCodeImage = generateQRCodeImage(qrCodeData,100,100);
        byte[] qrCodeBytes = convertImageToBytes(qrCodeImage);

        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(qrCodeBytes);

    }

    @PostMapping("/declaration")
    public List<Vaccination> vaccinationDeclarations(@RequestParam String timeslotCode,
                                                      @RequestParam Long amka,
                                                      @RequestParam String expirDate){
        return vaccinationService.vaccinationDeclarations(timeslotCode,amka,expirDate);
    }


}
