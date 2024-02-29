package com.openclassrooms.paymaybuddy.service;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class CompteDTO {
    String username;
    Double solde;
    LocalDateTime date;
}
