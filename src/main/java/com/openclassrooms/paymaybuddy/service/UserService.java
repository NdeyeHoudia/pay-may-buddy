package com.openclassrooms.paymaybuddy.service;
import com.openclassrooms.paymaybuddy.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
	String addConnexion(User user, User friend);
}
