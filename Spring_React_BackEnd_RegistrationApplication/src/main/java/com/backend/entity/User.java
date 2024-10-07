package com.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users" ,uniqueConstraints =
{@UniqueConstraint(columnNames = "email")})
@Getter
@Setter
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Name is mandatory")
	@Size(min = 2,max = 50)
	private String name;
	
	@NotBlank(message = "Email is mandatory")
	@Email(message = "Email should be valid")
	private String email;
	
	@NotBlank(message = "Password is mandatory")
	@Size(min =6,message = "Password must be at least 6 characters")
	private String password;
	
	public User() {
		
	}
	public User(String name,String email,String password) {
		this.name=name;
		this.email=email;
		this.password=password;
	}
}
