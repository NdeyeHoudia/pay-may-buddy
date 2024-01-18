package com.openclassrooms.paymaybuddy.controller;

import com.openclassrooms.paymaybuddy.model.User;
import com.openclassrooms.paymaybuddy.repository.RoleRepository;
import com.openclassrooms.paymaybuddy.repository.UserRepository;
import com.openclassrooms.paymaybuddy.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController implements EnvironmentAware {
    private Environment environment;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/auth")
    public User login(@RequestParam("user") String username, @RequestParam("password") String pwd) {
     //  String token = getJWTToken(username);
        User user = new User();
        user.setUsername(username);
        user.setPassword(pwd);
        userRepository.save(user);

        return user;

    }

  /* private String getJWTToken(String username) {
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");
        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(environment.getProperty("millisecond.expiration"))))
                .signWith(SignatureAlgorithm.HS512,
                        environment.getProperty("secret.key").getBytes()).compact();

        return "Bearer " + token;
    }*/

    @Override
    public void setEnvironment(final Environment environment) {
        this.environment = environment;
    }



/*
    @PostMapping("/signin")

    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        // String jwt = jwtUtils.generateJwtToken(authentication);

        UserServiceImpl userDetails = (UserServiceImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
/*List<String> comptes = userDetails.getAuthorities().stream()
        .map(item ->item.getAuthority())
        .collect(Collectors.toList());*/

    /*    return ResponseEntity.ok(new JwtResponse(
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));*/
      /*  return  ResponseEntity.ok(new User[Math.toIntExact(userDetails.getId()
        )]);
    }*/
}
