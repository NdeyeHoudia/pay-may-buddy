package com.openclassrooms.paymaybuddy.controller;
import com.openclassrooms.paymaybuddy.model.Compte;
import com.openclassrooms.paymaybuddy.model.Transaction;
import com.openclassrooms.paymaybuddy.model.User;
import com.openclassrooms.paymaybuddy.repository.CompteRepository;
import com.openclassrooms.paymaybuddy.repository.TransactionRepository;
import com.openclassrooms.paymaybuddy.repository.UserRepository;
import com.openclassrooms.paymaybuddy.service.TransactionDTO;
import com.openclassrooms.paymaybuddy.service.TransfertDetailsImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class TransactionController {
   @Autowired
    private TransfertDetailsImpl transfertDetails;
   @Autowired
   public CompteRepository compteRepository;
   @Autowired
   private TransactionRepository transactionRepository;
   @Autowired
   private UserRepository userRepository;
    @GetMapping("/load_form_transaction/depot")
    public String depot(@RequestParam(value = "montant",required = false) Double montant,Model model,HttpServletRequest request){
        // session.setAttribute("msg", "depot Added Sucessfully..");
       String name =request.getUserPrincipal().getName();
       Long idUser = userRepository.findByEmail(name).get().getId();
       model.addAttribute("transactions", idUser);
       Optional<Compte> compteOptional =  compteRepository.findById(Math.toIntExact(idUser));
       transfertDetails.depot(montant, compteOptional.get());
       return "redirect:/load_form_transaction";
   }
    @GetMapping("/debit")
    public String depotMoney(){ return "debit";}
    @GetMapping("/load_form_transaction/retrait")
    public String retrait(@RequestParam(value = "montant",required = false) Double montant,HttpServletRequest request,Model model){
        String name =request.getUserPrincipal().getName();
        Long idUser = userRepository.findByEmail(name).get().getId();
        if(montant != null){
            Optional<Compte> compteOptional =  compteRepository.findById(Math.toIntExact(idUser));
            transfertDetails.retrait(montant, compteOptional.get());
            model.addAttribute("transactions");
        }
        else throw  new RuntimeException("Le montant est null");
        return "redirect:/load_form_transaction";
    }
    @GetMapping("/credit")
    public String creditMoney(){return "credit";}
    @PostMapping("/load_form_transaction")
    public String saveTransactions(Model model,Transaction transactions) {
       /* List<Transaction>  transaction = transfertDetails.getTransaction();
        transactionRepository.save(transactions);
        model.addAttribute("transactions", transactions);
        //transactionRepository.save(transaction);*/
        return "redirect:/load_form_transaction";
    }
    @GetMapping("/load_form_transaction")
    public String showAllTransactions(Model model, HttpServletRequest request) {
        String name =request.getUserPrincipal().getName();
        User user = userRepository.findByEmail(name).get();
        Compte compte = compteRepository.findByClient(user);
        List<TransactionDTO> transactionDTOS = transfertDetails.getTransaction(compte);

        model.addAttribute("transactionDTOS", transactionDTOS);
        return "add";
    }
    @GetMapping("/transfert")
    public String transfert(
            @RequestParam  int idCompteEmetteur,
            @RequestParam int idCompteRecepteur,
            @RequestParam double montant,
            @RequestParam String description,Model model,HttpServletRequest request) {
        String name =request.getUserPrincipal().getName();
        Long idUser = userRepository.findByEmail(name).get().getId();
       Optional<Compte>  compteEmetteur =  compteRepository.findById(Math.toIntExact(idUser));
       Optional<Compte> compteRecepteur = compteRepository.findById(idCompteRecepteur);
       if(compteRecepteur.isPresent() && compteEmetteur.isPresent()){
           transfertDetails.transfer(compteEmetteur.get(),compteRecepteur.get(),montant,description);
           model.addAttribute("transfert");
       }
       else throw  new RuntimeException("Un des compte n'a pas été trouvé");
     return "index";
    }

    @PostMapping("/transactions")
    public String addTransfert (@ModelAttribute("addTransaction") Model model, Transaction transaction) {
        model.addAttribute("addTransaction", transfertDetails.saveTransfert(transaction));
        return "redirect:/transactions";
    }
}
