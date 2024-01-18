package com.openclassrooms.paymaybuddy.repository;

import com.openclassrooms.paymaybuddy.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

}
