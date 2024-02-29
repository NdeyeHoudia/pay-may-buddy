package com.openclassrooms.paymaybuddy.controller;
import com.openclassrooms.paymaybuddy.model.Compte;
import com.openclassrooms.paymaybuddy.model.Transaction;
import com.openclassrooms.paymaybuddy.model.User;
import com.openclassrooms.paymaybuddy.repository.CompteRepository;
import com.openclassrooms.paymaybuddy.repository.TransactionRepository;
import com.openclassrooms.paymaybuddy.repository.UserRepository;
import com.openclassrooms.paymaybuddy.service.TransfertDetailsImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    public String depot(@RequestParam(value = "montant",required = false) Double montant, Model model, HttpServletRequest request, HttpSession session){
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
    public String retrait(@RequestParam(value = "montant",required = false) Double montant,HttpServletRequest request,Model model,HttpSession session){
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
        return "redirect:/load_form_transaction";
    }
    @GetMapping("/load_form_transaction")
    public String showAccount(Model model, HttpServletRequest request) {
        String name =request.getUserPrincipal().getName();
        User user = userRepository.findByEmail(name).get();
        Compte currentAccount = compteRepository.findByClient(user);
        model.addAttribute("currentAccount", currentAccount);
        return "add";
    }
    @GetMapping("/transfer")
    public String transfer(
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
     return "virement effectué avec succés";
    }
}
