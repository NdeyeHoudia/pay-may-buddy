package com.openclassrooms.paymaybuddy.service;

import com.openclassrooms.paymaybuddy.model.Compte;
import com.openclassrooms.paymaybuddy.model.Transaction;
import com.openclassrooms.paymaybuddy.repository.CompteRepository;
import com.openclassrooms.paymaybuddy.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    public void transfer(Compte compteSource, Compte compteDestination, double amount, String description) {
       try {
           compteSource.retrait(amount);
           compteDestination.depot(amount);
           compteRepository.save(compteSource);
           compteRepository.save(compteDestination);
           Transaction transaction = new Transaction();
          transaction.setCompteDestination(compteDestination);
          transaction.setCompteSource(compteSource);
           transaction.setDescription(description);
           transaction.setMontant(amount);
           transaction.setDate(LocalDateTime.now());
           transactionRepository.save(transaction);
       }catch (Exception e){
           System.out.println("erreur de la méthode transfert" +e);
       }
    }

    public  List<TransactionDTO>  getTransaction(Compte compte){
        List<Transaction> transactionEntrantes = new ArrayList<>();
        List<Transaction> transactionSortrantes = new ArrayList<>();

        transactionEntrantes.addAll(compte.getTransactionsEntrantes());
        transactionSortrantes.addAll(compte.getTransactionsSortantes());
        List<TransactionDTO>transactionDTOS = new ArrayList<>();

      transactionEntrantes.forEach(transaction -> {
          TransactionDTO transactionDTO = new TransactionDTO();
          transactionDTO.setUsername(transaction.getCompteSource().getClient().getName());
          transactionDTO.setDescription(transaction.getDescription());
          transactionDTO.setMontant(transaction.getMontant());
          transactionDTO.setDate(transaction.getDate());
          transactionDTOS.add(transactionDTO);

      });

        transactionSortrantes.forEach(transaction -> {
            TransactionDTO transactionDTO = new TransactionDTO();
            transactionDTO.setUsername(transaction.getCompteDestination().getClient().getName());
            transactionDTO.setDescription(transaction.getDescription());
            transactionDTO.setMontant(transaction.getMontant());
            transactionDTO.setDate(transaction.getDate());
            transactionDTOS.add(transactionDTO);
        });

      return transactionDTOS;
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
