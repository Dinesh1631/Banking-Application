package com.AccountService.Exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.AccountService.DTO.ErrorDTO;

@ControllerAdvice
public class GobelCustomExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		Map<String,String> ValidationError = new HashMap<>();
		List<ObjectError> errorList = ex.getBindingResult().getAllErrors();
		errorList.forEach((error)->{
			String filedName = ((FieldError)error).getField();
			String message = error.getDefaultMessage();
			ValidationError.put(filedName, message)	;	
		});
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ValidationError);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDTO> ExceptionHandler(Exception exception ,WebRequest webrequest ){
		ErrorDTO errodto = new ErrorDTO(
				webrequest.getDescription(false),
				HttpStatus.INTERNAL_SERVER_ERROR,
				exception.getMessage(),
				LocalDateTime.now()
				);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errodto);
		
	}
	@ExceptionHandler(customerAlreayExistException.class)
	public ResponseEntity<ErrorDTO> customerAlreayExistExceptionHandler(customerAlreayExistException exception ,WebRequest webrequest ){
		ErrorDTO errodto = new ErrorDTO(
				webrequest.getDescription(false),
				HttpStatus.BAD_REQUEST,
				exception.getMessage(),
				LocalDateTime.now()
				);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errodto);
		
	}
	@ExceptionHandler(ResourceNotFountException.class)
	public ResponseEntity<ErrorDTO> ResourceNotFountExceptionHandler(ResourceNotFountException exception ,WebRequest webrequest ){
		ErrorDTO errodto = new ErrorDTO(
				webrequest.getDescription(false),
				HttpStatus.NOT_FOUND,
				exception.getMessage(),
				LocalDateTime.now()
				);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errodto);
		
	}
	

}
