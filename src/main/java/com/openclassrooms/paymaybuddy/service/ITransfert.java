package com.openclassrooms.paymaybuddy.service;
import com.openclassrooms.paymaybuddy.model.Compte;
import com.openclassrooms.paymaybuddy.model.Transaction;

public interface ITransfert {
    public void transfer(Compte compteEmetteur, Compte compteRecepteur, double montant, String description);

    Transaction saveTransfert(Transaction transaction);

    public Transaction getTransactionById(Long id);

}
