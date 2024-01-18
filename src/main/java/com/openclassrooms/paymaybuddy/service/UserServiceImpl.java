package com.openclassrooms.paymaybuddy.service;
import com.openclassrooms.paymaybuddy.model.Role;
import com.openclassrooms.paymaybuddy.model.User;
import com.openclassrooms.paymaybuddy.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private  UserRepository userRepository;


	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	public User findByUserName(String username) {
		return userRepository.findByUsername(username);
	}

	public List<User> getAllUsers(){return  userRepository.findAll();}

	@Override
	public String addConnexion(User user, User friend) {
		if (user!=null){
			user.addConnexion(friend);
			userRepository.save(user);
		}
		return user.getEmail();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("Invalid email or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
	public List<User> friends(){
		return 	userRepository.findAll();
	}

}
