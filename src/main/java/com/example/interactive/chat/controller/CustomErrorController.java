package com.example.interactive.chat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.interactive.chat.api.json.ErrorObj;
import com.example.interactive.chat.exception.ApiServiceException;

/**
 *
 * @author Artur Korra
 */
@RestControllerAdvice
public class CustomErrorController {

	@ExceptionHandler(value = ApiServiceException.class)
	public ResponseEntity<ErrorObj> errorRequestRefused(ApiServiceException ex) {
		return new ResponseEntity<>(new ErrorObj(ex.getCode(), ex.getMessage()), HttpStatus.BAD_REQUEST);
	}
}