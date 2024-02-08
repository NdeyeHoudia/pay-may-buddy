package com.openclassrooms.paymaybuddy.service;

import com.openclassrooms.paymaybuddy.model.Compte;
import com.openclassrooms.paymaybuddy.model.Transaction;
import com.openclassrooms.paymaybuddy.repository.CompteRepository;
import com.openclassrooms.paymaybuddy.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class TransfertDetailsImpl implements ITransfert {
    @Autowired
    CompteRepository compteRepository;
    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public void transfer(Compte compteSource, Compte compteDestination, double montant, String description) {
       try {
           compteSource.retrait(montant);
           compteDestination.depot(montant);
           compteRepository.save(compteSource);
           compteRepository.save(compteDestination);
           Transaction transaction = new Transaction();
          transaction.setCompteDestination(compteDestination);
          transaction.setCompteSource(compteSource);
           transaction.setDescription(description);
           transaction.setMontant(montant);
           transaction.setDate(LocalDateTime.now());
           transactionRepository.save(transaction);
       }catch (Exception e){
           System.out.println("erreur de la méthode transfert" +e);
       }
    }

    public Compte depot(double montant, Compte compte) {
        compte.depot(montant);
       return compteRepository.save(compte);
    }
    public void retrait(double montant, Compte compte) {
        if (compte.getSolde()>=montant){
            compte.retrait(montant);
            compteRepository.save(compte);
        }
        else throw  new RuntimeException("Le compte n'a pas suffisament d'argent");
    }

    public Transaction saveTransfert(Transaction transaction) {
        return transactionRepository.save(transaction);
    }


    @Override
    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(Math.toIntExact(id)).get();
    }
    public List<Transaction> getTransaction(){
        return transactionRepository.findAll();
    }
    public Optional<Transaction> getTransactionById(Integer id){
        return  transactionRepository.findById(id);
    }

}
