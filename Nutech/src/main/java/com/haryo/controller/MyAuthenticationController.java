package com.haryo.controller;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.haryo.config.JwtTokenUtil;
import com.haryo.config.MyUserDetailsService;
import com.haryo.dto.UserDto;
import com.haryo.model.User;
import com.haryo.model.request.JwtRequest;
import com.haryo.model.request.UpdateProfileRequest;
import com.haryo.model.response.HttpResponseModel;
import com.haryo.model.response.JwtResponse;
import com.haryo.repository.UserRepository;

import io.jsonwebtoken.JwtException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@CrossOrigin
public class MyAuthenticationController {
	
	@Autowired 
	private UserRepository repo;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Operation(summary = "", description = "Login using registered email and password")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Berhasil Login"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "103", description = "Unauthorized")
	})
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public HttpResponseModel<JwtResponse> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		HttpResponseModel<JwtResponse> result = new HttpResponseModel<JwtResponse>();
		
		if (!isValidEmail(authenticationRequest.getEmail())) {
			 result.setStatus(102);
			 result.setMessage("Parameter email tidak sesuai format"); 
			 return result;
			 } 
			
		if (!isValidPasswordLength(authenticationRequest.getPassword())) {
			result.setStatus(102);
			result.setMessage("Parameter password harus memiliki panjang minimal 8 karakter");
			return result;
			}
		
		try {
            authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
            final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
            final String token = jwtTokenUtil.generateToken(userDetails);
             
            result.setStatus(0);
            result.setMessage("login success");
            result.setData(new JwtResponse(token));

            return result;
		 	} catch (DisabledException e) {
	            throw new Exception("User account is disabled", e);
		 		} catch (BadCredentialsException e) {
		 			throw new Exception("Username atau password salah", e);
		 		}
	    }

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("Username atau password salah", e);
		}
	}
	
	 private boolean isValidEmail(String email) {
	        return email != null && email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
	    }

	    private boolean isValidPasswordLength(String password) {
	        return password != null && password.length() >= 8;
	    }
	    
	    
	    @Operation(summary = " ", description = " ")
		@ApiResponses(value = {
				@ApiResponse(responseCode = "200", description = "Request Successfully"),
				@ApiResponse(responseCode = "400", description = "Bad Request"),
				@ApiResponse(responseCode = "401", description = "Unauthorized")
		})
	    @GetMapping("/profile")
	    public HttpResponseModel<UserDto> getUserProfile(Authentication authentication) {
	        HttpResponseModel<UserDto> result = new HttpResponseModel<UserDto>();

	        if (authentication == null) {
	            result.setStatus(401);
	            result.setMessage("Unauthorized");
	            return result;
	        }

	       try {
	    	   String userEmail = authentication.getName();
	        User user = repo.findByEmail(userEmail);

	        if (user != null) {
	            UserDto resp = UserDto.builder()
	                    .email(user.getEmail())
	                    .firstName(user.getFirstName())
	                    .lastName(user.getLastName())
	                    .profileImage(user.getProfileImage())
	                    .build();

	            result.setStatus(0);
	            result.setMessage("Sukses");
	            result.setData(resp);
	        } else {
	            result.setStatus(404);
	            result.setMessage("User not found");
	        }
	       }
	       catch (JwtException e) {
	           result.setStatus(108);
	           result.setMessage("Token tidak valid atau kadaluwarsa");
	           result.setData(null);
	       }

	        return result;
	    }
	    
	    @PutMapping("/profile/update")
	    public HttpResponseModel<UserDto> updateProfile(
	            @RequestBody UpdateProfileRequest updateRequest,
	            Authentication authentication
	    ) {
	        HttpResponseModel<UserDto> result = new HttpResponseModel<>();

	        if (authentication == null) {
	            result.setStatus(401);
	            result.setMessage("Unauthorized");
	            return result;
	        }

	      try {  
	    	  String userEmail = authentication.getName();
	        User user = repo.findByEmail(userEmail);

	        if (user != null) {
	            user.setFirstName(updateRequest.getFirstName());
	            user.setLastName(updateRequest.getLastName());
	            user.setProfileImage(updateRequest.getProfileImage());

	            repo.save(user);

	            UserDto resp = UserDto.builder()
	                    .email(user.getEmail())
	                    .firstName(user.getFirstName())
	                    .lastName(user.getLastName())
	                    .profileImage(user.getProfileImage())
	                    .build();

	            result.setStatus(0);
	            result.setMessage("Update Profile berhasil");
	            result.setData(resp);
	        } else {
	            result.setStatus(404);
	            result.setMessage("User not found");
	        } 
	      }
	      catch (JwtException e) {
	            result.setStatus(108);
	            result.setMessage("Token tidak valid atau kadaluwarsa");
	            result.setData(null);
	        }

	        return result;
	    }
	    
	    @PutMapping("/profile/image")
	    public ResponseEntity<HttpResponseModel<UserDto>> updateProfileImage(
	            @RequestParam("file") MultipartFile file,
	            Authentication authentication
	    ) {
	        HttpResponseModel<UserDto> result = new HttpResponseModel<>();

	        if (authentication == null) {
	            result.setStatus(108); 
	            result.setMessage("Token tidak valid atau kadaluwarsa");
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
	        }

	        String userEmail = authentication.getName();
	        User user = repo.findByEmail(userEmail);

	        if (user != null) {
	            if (!file.getContentType().equals("image/jpeg") && !file.getContentType().equals("image/png")) {
	                result.setStatus(102);
	                result.setMessage("Format Image tidak sesuai");
	                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
	            }

	            try {
	                String imageUrl = saveImageAndGetUrl(file);
	                
	                user.setProfileImage(imageUrl);
	                
	                repo.save(user);
	            } catch (IOException e) {
	                result.setStatus(500); 
	                result.setMessage("Internal Server Error");
	                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
	            }

	            UserDto resp = UserDto.builder()
	                    .email(user.getEmail())
	                    .firstName(user.getFirstName())
	                    .lastName(user.getLastName())
	                    .profileImage(user.getProfileImage()) 
	                    .build();

	            result.setStatus(0);
	            result.setMessage("Update Profile Image berhasil");
	            result.setData(resp);
	            return ResponseEntity.ok(result);
	        } else {
	            result.setStatus(404);
	            result.setMessage("User not found");
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
	        }
	    }
	    private String saveImageAndGetUrl(MultipartFile file) throws IOException {
	        byte[] imageBytes = file.getBytes();

	        String imageUrl = "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(imageBytes);
	        
	        return imageUrl;
	    }
	    
}
