package com.openclassrooms.paymaybuddy.controller;

import com.openclassrooms.paymaybuddy.model.Compte;
import com.openclassrooms.paymaybuddy.model.Transaction;
import  com.openclassrooms.paymaybuddy.model.User;
import com.openclassrooms.paymaybuddy.repository.CompteRepository;
import com.openclassrooms.paymaybuddy.repository.TransactionRepository;
import com.openclassrooms.paymaybuddy.repository.UserRepository;
import com.openclassrooms.paymaybuddy.service.TransactionDTO;
import com.openclassrooms.paymaybuddy.service.TransfertDetailsImpl;
import com.openclassrooms.paymaybuddy.service.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.List;

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
	public String home(Model model, User user, HttpServletRequest request) {
			String name =request.getUserPrincipal().getName();
			List<User>   listFriend = userRepository.findByEmail(name).get().getListFriends();
			model.addAttribute("listFriend", listFriend);
			User user1 = userRepository.findByEmail(name).get();
			Compte compte = compteRepository.findByClient(user1);
			List<TransactionDTO> transactionDTOS = transfertDetails.getTransaction(compte);
			model.addAttribute("transactionDTOS", transactionDTOS);
			model.addAttribute("transactionDTO", new TransactionDTO());
		return "index";
	}
	@PostMapping("/addTransaction")
	public String addTransaction(@ModelAttribute TransactionDTO transactionDTO, HttpServletRequest request){
		Transaction transaction = new Transaction();
		String email =request.getUserPrincipal().getName();
		User currentUser = userRepository.findByEmail(email).orElseThrow();
		Compte  currentCompte =compteRepository.findByClient(currentUser);
		transaction.setDate(LocalDateTime.now());
		transaction.setCompteSource(currentCompte);
		User userDestination = userRepository.findByEmail(transactionDTO.getUsername()).orElseThrow();
		Compte compteDestination   =compteRepository.findByClient(userDestination);
		transaction.setCompteDestination(compteDestination);
		transaction.setDescription(transactionDTO.getDescription());
		transaction.setMontant(transactionDTO.getMontant());
		transactionRepository.save(transaction);

		return "redirect:/index";
	}
	@GetMapping("/registration")
	public String saveConnection(){return "registration";}
	@PostMapping("/showAllUsers")
	public String addConnection( String email, Model model,HttpServletRequest request) {
			String username = request.getUserPrincipal().getName();
			User currentUser = userRepository.findByEmail(username).orElseThrow();
			List<User>   listFriend = userRepository.findByEmail(username).get().getListFriends();
			User friend = userRepository.findByEmail(email).orElseThrow();

			User addUserWithEmail = currentUser.addConnexion(friend);
			String addConnection = addUserWithEmail.getEmail();
			listFriend.add(addUserWithEmail.addConnexion(currentUser));
		//	userRepository.save(addUserWithEmail);
		//	User addConnection1 = userService.addConnexion(currentUser, friend);
			System.out.println("New friend  " +addConnection);
			model.addAttribute("addConnection", addConnection);

		return "redirect:/index";
	}

}
