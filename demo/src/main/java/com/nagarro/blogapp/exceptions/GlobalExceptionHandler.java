package com.nagarro.blogapp.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nagarro.blogapp.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFound.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFound ex){
		String message=ex.getMessage();
		ApiResponse apiresponse=new ApiResponse(message,false);
		return new ResponseEntity<ApiResponse>(apiresponse,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String ,String>> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex){
		Map<String,String> map= new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String fieldName=((FieldError)error).getField();
			String defaultmsg=error.getDefaultMessage();
			map.put(fieldName, defaultmsg);
		});;
		
		return new ResponseEntity<Map<String ,String>>(map,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<Map<String,String>> HttpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException ex){
		Map<String,String> map= new HashMap<>();
//		ex.getLocalizedMessage();
		
		map.put(ex.getLocalizedMessage()," ");
		return new ResponseEntity<Map<String,String>>(map,HttpStatus.BAD_REQUEST);
	}

}
