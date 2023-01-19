package com.easytrip.app.Exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;


@ControllerAdvice
public class GlobalExceptionHandler {

	
	
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErroDetails> myAnyExpHandler(Exception ie, WebRequest req)
	{
MyErroDetails err = new MyErroDetails();
		
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(ie.getMessage());
		err.setDetails(req.getDescription(false));
		
		return new ResponseEntity<MyErroDetails>(err,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyErroDetails> mynotfoundHandler(NoHandlerFoundException nfe, WebRequest req)
	{
		MyErroDetails err = new MyErroDetails(LocalDateTime.now(), nfe.getMessage(), req.getDescription(false));
   
		
		return new ResponseEntity<MyErroDetails>(err,HttpStatus.BAD_REQUEST);
	
	}
	
	
	
}
