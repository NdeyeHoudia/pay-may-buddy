package com.openclassrooms.paymaybuddy.repository;

import com.openclassrooms.paymaybuddy.model.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompteRepository extends JpaRepository<Compte, Integer> {
}
