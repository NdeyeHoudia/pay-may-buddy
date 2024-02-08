package com.openclassrooms.paymaybuddy.controller;

import  com.openclassrooms.paymaybuddy.model.User;
import com.openclassrooms.paymaybuddy.repository.CompteRepository;
import com.openclassrooms.paymaybuddy.repository.TransactionRepository;
import com.openclassrooms.paymaybuddy.repository.UserRepository;
import com.openclassrooms.paymaybuddy.service.TransfertDetailsImpl;
import com.openclassrooms.paymaybuddy.service.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class MainController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private  UserServiceImpl userService;
	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private TransfertDetailsImpl transfertDetails;
	@Autowired
	public CompteRepository compteRepository;

	@GetMapping("/")
	public String login() {
		return "login";
	}
	@GetMapping("/index")
	public String home() {

		/*User user =  (User) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		System.out.println(user.getListFriends() );*/
		return "index";
	}
	@GetMapping("/showAllUsers")
		public String listConnection(Model model, User user, HttpServletRequest request) {

		HttpSession session = request.getSession();
		session.setAttribute("listFriend", user.getListFriends());

		String name =request.getUserPrincipal().getName();

		List<User>   listFriend = userRepository.findByEmail(name).get().getListFriends();
		System.out.println(listFriend);

		model.addAttribute("listFriend", listFriend );
		return "registration";
	}

	@PostMapping("/showAllUsers")
	public String addConnection (User friend, String username, Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = userRepository.findByEmail(username).orElseThrow();
		model.addAttribute("addConnection", userService.addConnexion(user,friend));
		return "redirect:/registration";
	}
	@GetMapping("/getData")
	public String getData(HttpSession session, User user) {
		String username = (String) session.getAttribute("username");
		return "index" + username;
	}

}
