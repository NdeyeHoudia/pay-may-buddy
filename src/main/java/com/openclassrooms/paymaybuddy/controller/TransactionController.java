package com.openclassrooms.paymaybuddy.controller;
import com.openclassrooms.paymaybuddy.model.Compte;
import com.openclassrooms.paymaybuddy.model.Transaction;
import com.openclassrooms.paymaybuddy.model.User;
import com.openclassrooms.paymaybuddy.repository.CompteRepository;
import com.openclassrooms.paymaybuddy.service.TransfertDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/trans")
public class TransactionController {
   @Autowired
    private TransfertDetailsImpl transfertDetails;
   @Autowired
   public CompteRepository compteRepository;

    @GetMapping("/depot")
    public String depot(@RequestParam double montant, int idCompte, Model model){
       model.addAttribute("transactions", montant);
       Optional<Compte> compteOptional =  compteRepository.findById(idCompte);
        transfertDetails.depot(montant, compteOptional.get());
        return "transactions";
    }

    @GetMapping("/retrait")
    public String retrait(@RequestParam double montant, int idCompte,Model model){
        model.addAttribute("transactions", montant);
        Optional<Compte> compteOptional =  compteRepository.findById(idCompte);
        transfertDetails.retrait(montant, compteOptional.get());
        return "transactions";
    }
    @GetMapping("/transfert")
    public String transfert(
            @RequestParam  int idCompteEmetteur,
            @RequestParam int idCompteRecepteur,
            @RequestParam double montant,
            @RequestParam String description) {
       Optional<Compte>  compteEmetteur =  compteRepository.findById(idCompteEmetteur);
       Optional<Compte> compteRecepteur = compteRepository.findById(idCompteRecepteur);
       if(compteRecepteur.isPresent() && compteEmetteur.isPresent()){
           transfertDetails.transfer(compteEmetteur.get(),compteRecepteur.get(),montant,description);
       }
       else throw  new RuntimeException("Un des compte n'a pas été trouvé");
     return "le transfert est effectué";
    }

    @GetMapping("/AllTransaction")
    public Iterable<Transaction> getAllTransactionByUser(){
        Compte compte = new Compte();
        compte.getClient();
       return transfertDetails.getTransaction();
    }
}
