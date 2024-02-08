package com.openclassrooms.paymaybuddy.controller;
import com.openclassrooms.paymaybuddy.model.Compte;
import com.openclassrooms.paymaybuddy.model.Transaction;
import com.openclassrooms.paymaybuddy.model.User;
import com.openclassrooms.paymaybuddy.repository.CompteRepository;
import com.openclassrooms.paymaybuddy.service.TransfertDetailsImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
//@RequestMapping("/api/trans")
public class TransactionController {
   @Autowired
    private TransfertDetailsImpl transfertDetails;
   @Autowired
   public CompteRepository compteRepository;

   @GetMapping("/transactions/new")
    public String depot(@RequestParam double montant, int idCompte, Model model){
        // recuperer d'abord l'user (idUser)

       model.addAttribute("trans", montant);
       Optional<Compte> compteOptional =  compteRepository.findById(idCompte);
        transfertDetails.depot(montant, compteOptional.get());
        return "transactions";
    }

   @GetMapping("/load_form_transaction/new")
   public String depot(@RequestParam double montant, int idCompte, Model model, HttpSession session){
       // recuperer d'abord l'user (idUser)

       session.setAttribute("msg", "depot Added Sucessfully..");

       model.addAttribute("transactions", montant);
       Optional<Compte> compteOptional =  compteRepository.findById(idCompte);
       transfertDetails.depot(montant, compteOptional.get());
       return "debit";
   }
    @GetMapping("/save_transaction")
    public String saveProducts(Model model) {
        model.addAttribute("transactions", transfertDetails.getTransaction());
        //transactionRepository.save(transaction);
        return "redirect:/load_form_transaction";
    }
    @GetMapping("/load_form_transaction")
    public String loadForm() {
        return "add";
    }

    @GetMapping("/retrait")
    public String retrait(@RequestParam double montant, int idCompte,Model model){
        model.addAttribute("trans", montant);
        Optional<Compte> compteOptional =  compteRepository.findById(idCompte);
        transfertDetails.retrait(montant, compteOptional.get());
        return "debit";
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
     return "transactions";
    }

    @GetMapping("/AllTransaction")
    public Iterable<Transaction> getAllTransactionByUser(){
        Compte compte = new Compte();
        compte.getClient();
       return transfertDetails.getTransaction();
    }
    @PostMapping("/transactions")
    public String addTransfert (@ModelAttribute("addTransaction") Model model, Transaction transaction) {
        model.addAttribute("addTransaction", transfertDetails.saveTransfert(transaction));
        return "redirect:/transactions";
    }

}
