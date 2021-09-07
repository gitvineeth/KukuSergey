package com.appsdevelopersblog.app.ws.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.appsdevelopersblog.app.ws.ui.response.ErrorMessage;

@ControllerAdvice
public class AppExceptionHandler {
	
	@ExceptionHandler(value = {UserServiceExceptions.class})
	public ResponseEntity<Object> handleUserServiceExceptions(UserServiceExceptions ex,WebRequest req){
//		return new ResponseEntity<>(ex.getMessage(),new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
		ErrorMessage error = new ErrorMessage(new Date(),ex.getMessage());
		return new ResponseEntity<Object>(error,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

}
