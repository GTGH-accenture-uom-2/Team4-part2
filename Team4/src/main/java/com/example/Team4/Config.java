package com.example.Team4;

import com.example.Team4.Models.*;
import com.example.Team4.Services.DoctorService;
import com.example.Team4.Services.InsuredService;
import com.example.Team4.Services.ReservationService;
import com.example.Team4.Services.TimeslotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class Config {

    @Autowired
    private Doctor doc1;
    @Autowired
    private Doctor doc2;
    @Autowired
    private Doctor doc3;
    @Autowired
    private Doctor doc4;
    @Autowired
    private ArrayList<Timeslot>timeslots1;
    @Autowired
    private ArrayList<Timeslot>timeslots2;

    @Autowired
    private ArrayList<Doctor>doctors;
    @Autowired
    private Vaccine vaccine1;
    @Autowired
    private Vaccine vaccine2;
    @Autowired
    private Vaccine vaccine3;
    @Autowired
    private Vaccine vaccine4;
    @Autowired
    private ArrayList<Vaccine> vaccines;

    @Bean
    public CommandLineRunner commandLineRunner(InsuredService insuredService) {

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


        };

    }

    @Bean
    public Doctor doc1(){
        return new Doctor("Donatello", "Donatel", 55631L);
    }
    @Bean
    public Doctor doc2(){
        return new Doctor("Rafaello", "Rafael", 90078L);
    }
    @Bean
    public Doctor doc3() {
        return new Doctor("Michail", "Angel", 32212L);
    }
    @Bean
    public Doctor doc4(){
        return new Doctor("Leonardo", "DaVinci", 87099L);
    }

    @Bean
    public ArrayList<Timeslot> timeslots1(){
        ArrayList<Timeslot> timeslots1 = new ArrayList<>();
        timeslots1.add(new Timeslot(10, 4, 2024, 9, 0, 9, 30, doc1));
        timeslots1.add(new Timeslot(10, 4, 2024, 10, 0, 10, 30, doc2));
        timeslots1.add(new Timeslot(10, 4, 2024, 11, 0, 9, 30, doc1));
        timeslots1.add(new Timeslot(10, 4, 2024, 12, 0, 10, 30, doc2));
        timeslots1.add(new Timeslot(10, 4, 2024, 13, 0, 9, 30, doc1));
        timeslots1.add(new Timeslot(10, 4, 2024, 14, 0, 10, 30, doc2));
        timeslots1.add(new Timeslot(10, 4, 2024, 15, 0, 9, 30, doc1));
        timeslots1.add(new Timeslot(10, 4, 2024, 16, 0, 10, 30, doc2));
        timeslots1.add(new Timeslot(10, 4, 2024, 17, 0, 9, 30, doc1));
        timeslots1.add(new Timeslot(10, 4, 2024, 18, 0, 10, 30, doc2));

        return timeslots1;
    }

    @Bean
    public ArrayList<Timeslot> timeslots2(){
        ArrayList<Timeslot> timeslots2 = new ArrayList<>();
        timeslots2.add(new Timeslot(10, 4, 2024, 9, 0, 9, 30, doc3));
        timeslots2.add(new Timeslot(10, 4, 2024, 10, 0, 10, 30, doc4));
        timeslots2.add(new Timeslot(10, 4, 2024, 11, 0, 9, 30, doc3));
        timeslots2.add(new Timeslot(10, 4, 2024, 12, 0, 10, 30, doc4));
        timeslots2.add(new Timeslot(10, 4, 2024, 13, 0, 9, 30, doc3));
        timeslots2.add(new Timeslot(10, 4, 2024, 14, 0, 10, 30, doc4));
        timeslots2.add(new Timeslot(10, 4, 2024, 15, 0, 9, 30, doc3));
        timeslots2.add(new Timeslot(10, 4, 2024, 16, 0, 10, 30, doc4));
        timeslots2.add(new Timeslot(10, 4, 2024, 17, 0, 9, 30, doc3));
        timeslots2.add(new Timeslot(10, 4, 2024, 18, 0, 10, 30, doc4));

        return timeslots2;
    }

    @Bean
    public ArrayList<Doctor> doctors(){
        doctors.add(doc1);
        doctors.add(doc2);
        doctors.add(doc3);
        doctors.add(doc4);
        return doctors;
    }

    @Bean
    public VaccinationCenter vaccCenter1(){
        return new VaccinationCenter("123", "casterly rock",timeslots1);
    }

    @Bean
    public VaccinationCenter vaccCenter2(){
        return new VaccinationCenter("456", "storm's end", timeslots2);
    }

    @Bean
    public Reservation reservation(){
        return new Reservation(new Insured("Ned", "Stark", 875318L, "ned@gmail.com", 986752L, "19/9/1969"),timeslots1.get(1),doc1);
    }

    @Bean
    public Vaccine vaccine1(){
        return new Vaccine("pfizer",9);
    }
    @Bean
    public Vaccine vaccine2(){
        return new Vaccine("johnson",6);
    }
    @Bean
    public Vaccine vaccine3(){
        return new Vaccine("moderna",10);
    }
    @Bean
    public Vaccine vaccine4(){
        return new Vaccine("astrazeneca",8);
    }

    @Bean
    public ArrayList<Vaccine> vaccines(){
        vaccines.add(vaccine1);
        vaccines.add(vaccine2);
        vaccines.add(vaccine3);
        vaccines.add(vaccine4);
        return vaccines;
    }

    @Bean Vaccination vaccination(){
        return new Vaccination(new Insured("Cersei", "Lannister", 876328L, "cersei@gmail.com", 986309L, "1/4/1943"), doc1, "12/10/2023","12/07/2024",vaccine1);
    }


}
