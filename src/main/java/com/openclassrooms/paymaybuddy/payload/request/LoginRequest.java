package com.openclassrooms.paymaybuddy.payload.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
