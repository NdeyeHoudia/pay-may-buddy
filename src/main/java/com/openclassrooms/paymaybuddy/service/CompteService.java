package com.openclassrooms.paymaybuddy.service;

import com.openclassrooms.paymaybuddy.model.Compte;
import com.openclassrooms.paymaybuddy.model.Transaction;
import com.openclassrooms.paymaybuddy.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompteService {


    public List<CompteDTO> getListTransaction(Compte compte){
        List<Compte> credits = new ArrayList<>();
        List<Compte> debits = new ArrayList<>();

       return null;
    }
}
