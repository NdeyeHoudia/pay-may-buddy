package com.openclassrooms.paymaybuddy.model;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Entity
@Table(name =  "user",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = "username"),
				@UniqueConstraint(columnNames = "email")
		})
//@ToString(exclude = {"parent"})
@EqualsAndHashCode(of = {"id"})
public class User {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "username")
	private String name;
	@Column(name = "email")
	private String email;
	@Column(name = "password")
	private String password;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "parent", referencedColumnName = "id")
	private User parent;
	@OneToMany(mappedBy = "parent", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<User> listFriends;
	public List<User> getListFriends() {
		return listFriends;
	}
	public void setListFriends(List<User> listFriends) {
		this.listFriends = listFriends;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(  name = "users_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Collection<Role> roles = new HashSet<>();
	public User() {}
	public User(String username, String email, String password) {
		super();
		this.name = username;
		this.email = email;
		this.password = password;
		//this.roles = roles;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Collection<Role> getRoles() {
		return roles;
	}
	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void addConnexion(User user){
		if (user !=null){
			listFriends.add(user);
		}
	}
}
