package com.example.Team4.Services;

import com.example.Team4.Models.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InsuredService {

    List<Insured> insureds = new ArrayList<>();

    public List<Insured> addInsured(Insured insured){
        insureds.add(insured);
        return insureds;
    }
    public Insured findByAmka(long amka){
        for (var elem: insureds){
            if (elem.getAmka() == amka){
                return elem;
            }
        }return null;
    }
}
