package com.Alkemy.Challenge.auth.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

	@Email(message =  "El usuario debe ser un email")
	private String username;
	@Size(min = 8, message = "La contraseña debe contener al menos 8 caracteres")
	private String password;
}
