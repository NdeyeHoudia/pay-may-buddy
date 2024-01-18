package com.openclassrooms.paymaybuddy.controller;

import com.openclassrooms.paymaybuddy.model.User;
import com.openclassrooms.paymaybuddy.repository.UserRepository;
import com.openclassrooms.paymaybuddy.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class MainController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private  UserServiceImpl userService;

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/")
	public String home() { return "index";}

	@GetMapping("/showAllUsers")
	public String listConnection(Model model) {
		model.addAttribute("registration", userService.getAllUsers());
		return "registration";
	}
	@PostMapping("/showAllUsers")
	public String addConnection (@ModelAttribute("addConnection") User friend, String username, Model model) {
		User user = userRepository.findByUsername(username);
		//model.addAttribute(user.getListFriends());
		model.addAttribute("addConnection", userService.addConnexion(user,friend));
		return "redirect:/registration";
	}

}
