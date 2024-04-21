package com.example.Team4;

import com.example.Team4.Models.Insured;
import com.example.Team4.Services.DoctorService;
import com.example.Team4.Services.InsuredService;
import com.example.Team4.Services.ReservationService;
import com.example.Team4.Services.VaccinationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public CommandLineRunner commandLineRunner(InsuredService insuredService, DoctorService doctorService,
                                               ReservationService reservationService, VaccinationService vaccinationService) {

        return args -> {
            insuredService.addInsured(new Insured("Petyr", "Baelish", 128975L, "petyr@gmail.com", 673553L, "11/03/1935"));
            insuredService.addInsured(new Insured("Lord", "Varys", 373598L, "lord@gmail.com", 846338L, "17/03/1972"));
            insuredService.addInsured(new Insured("Theon", "Greyjoy", 83635L, "theon@gmail.com", 83625L, "7/08/1945"));
            insuredService.addInsured(new Insured("Sandor", "Clegane", 823627L, "sandor@gmail.com", 927156L, "5/12/1988"));
            insuredService.addInsured(new Insured("Brienne", "of Tarth", 987534L, "brienne@gmail.com", 82615L, "15/12/1999"));
            insuredService.addInsured(new Insured("Arya", "Stark", 765439L, "arya@gmail.com", 76754L, "10/1/2010"));
            insuredService.addInsured(new Insured("Sansa", "Stark", 6535978L, "sansa@gmail.com", 787530L, "8/11/2011"));
            insuredService.addInsured(new Insured("Jon", "Snow", 898674L, "jon@gmail.com", 876430L, "18/4/1980"));
            insuredService.addInsured(new Insured("Daenerys", "Targaryen", 875643L, "daeneys@gmail.com", 998764L, "1/5/1989"));
            insuredService.addInsured(new Insured("Tyrion", "Lannister", 7635234L, "tyrion@gmail.com", 926254L, "10/7/1970"));
            insuredService.addInsured(new Insured("Cersei", "Lannister", 876328L, "cersei@gmail.com", 986309L, "1/4/1943"));
            insuredService.addInsured(new Insured("Ned", "Stark", 875318L, "ned@gmail.com", 986752L, "19/9/1969"));


        };

    }
}
