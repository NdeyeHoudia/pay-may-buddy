package com.openclassrooms.paymaybuddy.service;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionDTO {
    String username;
    String description;
    Double montant;
    LocalDateTime date;

}
