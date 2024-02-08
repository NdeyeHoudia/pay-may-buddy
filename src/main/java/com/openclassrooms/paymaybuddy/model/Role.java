package com.openclassrooms.paymaybuddy.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "role")
public class Role {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private ERole name;
	public Role() {
		
	}
	
	public Role(ERole name) {
		super();
		this.name = name;
	}

}
