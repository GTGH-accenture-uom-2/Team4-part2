package com.example.Team4.Services;

import com.example.Team4.Models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class InsuredService {

    //List<Insured> insureds = new ArrayList<>();
    @Autowired
    List<Insured> insureds;
    public List<Insured> addInsured(String name, String surname, Long amka, Long afm, String birthdate, String email){
        insureds.add(new Insured(name,surname,amka,email,afm,birthdate));
        return insureds;
    }

    public Insured findByAmka(long amka){
        for (var elem: insureds){
            if (elem.getAmka() == amka)
                return elem;
            else
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error, Insured not found");
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error, Insured not found");
    }

    public List<Insured> getInsureds() {
        return insureds;
    }
}
