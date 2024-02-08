package com.openclassrooms.paymaybuddy.payload.response;

import lombok.Data;

import java.util.List;
@Data
public class JwtResponse {
    private String token;
    private String type = "customer";
    private Long id;
    private String username;
    private String email;
    private List<String> roles;
   // private List<String> comptes;

    public JwtResponse(Long id, String username, String email, List<String> roles) {
      //  this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
      //  this.comptes = comptes;
    }
}
