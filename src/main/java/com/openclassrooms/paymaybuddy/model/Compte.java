package com.openclassrooms.paymaybuddy.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "compte")
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_compte")
    private int idCompte;
    @OneToOne
    @JoinColumn(name = "id_client")
    private User client;
    @Column(name = "solde")
    private double solde;
    @Column(name = "numero_compte_bancaire")
    private String numberAccountBank;
    @OneToMany(mappedBy = "compteSource")
    private List<Transaction> transactionsSortantes = new ArrayList<>() ;
    @OneToMany(mappedBy = "compteDestination")
    private List<Transaction> transactionsEntrantes = new ArrayList<>();

    public List<Transaction> getTransactionsEntrantes() {
        return transactionsEntrantes;
    }

    public void setTransactionsEntrantes(List<Transaction> transactionsEntrantes) {
        this.transactionsEntrantes = transactionsEntrantes;
    }

    public User getClient() {
        return client;
    }
    public void setClient(User client) {
        this.client = client;
    }

    public double getSolde() {
        return solde;
    }
    public void setSolde(double solde) {
        this.solde = solde;
    }
    public String getNumberAccountBank() {
        return numberAccountBank;
    }
    public void setNumberAccountBank(String numberAccountBank) {
        this.numberAccountBank = numberAccountBank;
    }
    public List<Transaction> getTransactionsSortantes() {
        return transactionsSortantes;
    }
    public void setTransactionsSortantes(List<Transaction> transactionsSortantes) {
        this.transactionsSortantes = transactionsSortantes;
    }

    public Compte(double solde, String numberAccountBank, User client,List<Transaction> transactions) {
        this.solde = solde;
        this.numberAccountBank = numberAccountBank;
        this.client = client;
        this.transactionsSortantes = transactions;
    }

    public Compte() {}

    public void retrait(double montant) {
        if (solde >= montant) {
            solde = solde - montant;
        } else throw new RuntimeException("Solde insuffisant");
    }
   public void depot(double montant) {
        solde = solde + montant;
    }

}
