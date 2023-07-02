package com.hms.userService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.hms.userService.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException e){
		String msg = e.getMessage();
		ApiResponse apiResponse = new ApiResponse(msg, true, HttpStatus.NOT_FOUND);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
		//return null;
	}
}
