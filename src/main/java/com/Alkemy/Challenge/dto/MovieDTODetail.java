package com.Alkemy.Challenge.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieDTODetail {

	private Long id;
	@NotBlank(message = "{La imagen es obligatoria}")
	@Size(max = 60, message = "{Max 60 caracteres}")
	private String image;
	@NotBlank(message = "{El titulo es obligatorio}")
	@Size(max = 100, message = "{Max 100 caracteres}")
	private String title;

	private String creationDate;
	@Min(value = 1, message = "{valor minimo 1}")
	@Max(value = 5, message = "{valor maximo 5}")
	private int qualification;

	@NotNull(message = "{Id de genero obligatorio}")
	private Long genderId;
}
