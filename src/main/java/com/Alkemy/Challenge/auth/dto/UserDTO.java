package com.Alkemy.Challenge.auth.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.Alkemy.Challenge.auth.entity.RoleEntity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

	@NonNull
    @NotEmpty(message = "the name can't be null")
    @NotBlank(message = "the name can't  be blank")
    private String firstName;
    @NonNull
    @NotEmpty(message = "the lastName can't be null")
    @NotBlank(message = "the lastName can't  be blank")
    private String lastName;
    @NonNull
    @Email(message = "enter a correct email")
    private String email;
	@Size(min = 8, message = "La contraseña debe contener al menos 8 caracteres")
	private String password;
	private Set<RoleEntity> roles = new HashSet<>();
}
