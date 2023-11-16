package com.haryo.config;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.haryo.exception.UserAlreadyExistsException;
import com.haryo.model.response.HttpResponseModel;

@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({BadCredentialsException.class})
	@ResponseBody
	public ResponseEntity<HttpResponseModel> handlerBadCredentialException(Exception ex) {
		
		HttpResponseModel<Object> model = new HttpResponseModel<Object>();
		model.setStatus(103);
		model.setMessage(ex.getMessage());
	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(model);
	}
	
	@ExceptionHandler({ InsufficientAuthenticationException.class })
    @ResponseBody
	public ResponseEntity<HttpResponseModel> handleInsufficientAuthenticationException(Exception ex) {
		
		HttpResponseModel<Object> model = new HttpResponseModel<Object>();
		model.setStatus(-999);
		model.setMessage(ex.getMessage()); 

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(model);
	}
	
	@ExceptionHandler({ UserAlreadyExistsException.class })
	@ResponseBody
	public ResponseEntity<HttpResponseModel> userAlreadyExistsException(Exception ex) {
		
		HttpResponseModel<Object> model = new HttpResponseModel<Object>();
		model.setStatus(402);
		model.setMessage("email sudah ada");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(model);
	}
}
