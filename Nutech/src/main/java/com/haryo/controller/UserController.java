package com.haryo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.haryo.dto.UserDto;
import com.haryo.exception.UserAlreadyExistsException;
import com.haryo.model.User;
import com.haryo.model.request.RegistrationRequest;
import com.haryo.model.response.HttpResponseModel;
import com.haryo.repository.UserRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;



@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired private UserRepository repo;
	@Autowired PasswordEncoder encoder;
	
	@Operation(summary = "Register new user", description = "Registering for new user")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Request Successfull"),
			@ApiResponse(responseCode = "400", description = "Bad Request")
	})
	@PostMapping("/register")
	public HttpResponseModel<UserDto>register(@RequestBody RegistrationRequest req) {
		HttpResponseModel<UserDto> result = new HttpResponseModel<UserDto>();
		

			
		User user = repo.findByEmail(req.getEmail());
		if (user==null) {
			repo.save(User.builder()
					.email(req.getEmail())
					.firstName(req.getFirstName())
					.lastName(req.getLastName())
					.password(encoder.encode(req.getPassword()))
					.build());
			
			UserDto resp = UserDto.builder()
					.email(req.getEmail())
					.firstName(req.getFirstName())
					.lastName(req.getLastName())
					.build();
			
			result.setStatus(0);
			result.setMessage("registrasi berhasil silahkan login");
			result.setData(resp);
		} else {
			throw new UserAlreadyExistsException(req.getEmail());
		}
		return result;
	}
	


}
