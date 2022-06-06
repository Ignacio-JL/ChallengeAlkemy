package com.Alkemy.Challenge.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.Alkemy.Challenge.dto.ApiErrorDTO;
import com.Alkemy.Challenge.exception.EmailAlreadyExistException;
import com.Alkemy.Challenge.exception.JwtBadRequestException;
import com.Alkemy.Challenge.exception.ParamNotFound;

@ControllerAdvice
public class RestExceptionController extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(value = {ParamNotFound.class})
	protected ResponseEntity<Object> handleParamNotFound(RuntimeException ex, WebRequest request) {
		ApiErrorDTO errorDTO = new ApiErrorDTO(
				HttpStatus.BAD_REQUEST, 
				ex.getMessage(),
				Arrays.asList("Param Not Found")
				);
		
		return handleExceptionInternal(ex, errorDTO, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	@ExceptionHandler(value = {EmailAlreadyExistException.class})
	protected ResponseEntity<Object> handleEmailAlreadyExistException(RuntimeException ex, WebRequest request) {
		ApiErrorDTO errorDTO = new ApiErrorDTO(
				HttpStatus.BAD_REQUEST,
				ex.getMessage(), 
				Arrays.asList(ex.getMessage())
				);
		return handleExceptionInternal(ex, errorDTO, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
				
	}
	
	@ExceptionHandler(value = {JwtBadRequestException.class})
	protected ResponseEntity<Object> handleJwtBadRequestException(RuntimeException ex, WebRequest request){
		ApiErrorDTO errorDTO = new ApiErrorDTO(
				HttpStatus.BAD_REQUEST, 
				ex.getMessage(), 
				Arrays.asList(ex.getMessage())
				);
		return handleExceptionInternal(ex, errorDTO, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
//	Cuando los @Valid presentan error
	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex,
			HttpHeaders headers, 
			HttpStatus status, 
			WebRequest request
			) {

		List<String> errors = new ArrayList<>();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() + ": " + error.getDefaultMessage());
		}
		
		for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
		}
		
		ApiErrorDTO apiError = new ApiErrorDTO(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
		
		return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
	}
	
	
	
	

}
