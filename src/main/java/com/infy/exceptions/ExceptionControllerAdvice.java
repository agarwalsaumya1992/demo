package com.infy.exceptions;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.infy.dto.ResponseBuilder;
import com.infy.service.ProductServiceImpl;

@RestControllerAdvice
public class ExceptionControllerAdvice {
    //this helps receiving the message/value related to the general exception from the ValidationMessages.properties
//	@Autowired
//	private Environment environment;
	
	private static Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

 	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseBuilder> handleGeneralExceptions(Exception ex) 
 	{
 		log.error(ex.getMessage());
 		ResponseBuilder error = new ResponseBuilder();
	     error.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
//	     error.setMessage(environment.getProperty(InfyConstants.GENERAL_EXCEPTION_MESSAGE.toString()));
	     error.setMessage(ex.getMessage());
	     return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//Handler for NoSuchCustomerException
	@ExceptionHandler(NoSuchRecordException.class)
	public ResponseEntity<ResponseBuilder> handleCustomerExceptions(NoSuchRecordException ex) 
	{
		log.error(ex.getMessage());
		ResponseBuilder error = new ResponseBuilder();
	     error.setResponseCode(HttpStatus.BAD_REQUEST.value());
	     error.setMessage(ex.getMessage());
	     return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	//Handler that handles the exception raised because of invalid data that is received as method argument (DTO)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseBuilder> handleValidationExceptions(MethodArgumentNotValidException ex) 
	{
		log.error(ex.getMessage());
		ResponseBuilder error = new ResponseBuilder();
	     error.setResponseCode(HttpStatus.BAD_REQUEST.value());
	     error.setMessage(ex.getBindingResult().getAllErrors()
	    		 		  	.stream().map(ObjectError::getDefaultMessage)//lambda equivalent -> x->x.getDefaultMessage()
	    		 		  	.collect(Collectors.joining(", ")));
	        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
		
	//Handler that handles the exception raised because of invalid data that is received as 
	//URI parameter (path variables, request parameters)
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ResponseBuilder> handleConstraintValidationExceptions(ConstraintViolationException ex) 
	{
		log.error(ex.getMessage());
		ResponseBuilder error = new ResponseBuilder();
	     error.setResponseCode(HttpStatus.BAD_REQUEST.value());
	     error.setMessage(ex.getConstraintViolations()
	    		 			.stream().map(ConstraintViolation::getMessage)//lambda equivalent -> x->x.getMessage()
	    		 			.collect(Collectors.joining(", ")));
	     return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
} 
