package com.example.ecommerce.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.dto.AuthenticationRequest;
import com.example.ecommerce.dto.AuthenticationResponse;
import com.example.ecommerce.dto.SignupDto;
import com.example.ecommerce.dto.UserDto;
import com.example.ecommerce.entities.User;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.security.JwtUtil;
import com.example.ecommerce.security.UserDetailsServiceImpl;
import com.example.ecommerce.service.UserService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(maxAge = 3600)
public class AuthanticationController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	JwtUtil jwtUtil;
	
	public static final String TOKEN_PREFIX ="Authorization Bearer ";
	
	

	
	@PostMapping("/sign-up")
	public ResponseEntity<?> signupUser(@RequestBody SignupDto signupDto){
		if(userService.hasUserWithEmail(signupDto.getEmail())) {
			return new ResponseEntity<>("User already exist", HttpStatus.NOT_ACCEPTABLE);
		}
		
		UserDto createdUser = userService.createUser(signupDto);
		
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}
	
	@PostMapping("/authenticate")
	public void createAuthenticationToken(@RequestBody AuthenticationRequest authenticaionRequest, HttpServletResponse response) throws IOException {
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticaionRequest.getUsername(),authenticaionRequest.getPassword()));
			
		}catch(BadCredentialsException e) {
			throw new BadCredentialsException("Incorrect username pr password");
			
		}catch(DisabledException disabledException) {
			response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE,"User is not found");
			return;
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticaionRequest.getUsername());
		User user = userRepository.findFirstByEmail(authenticaionRequest.getUsername());
		final String jwt = jwtUtil.generateToken(authenticaionRequest.getUsername());
		 //return new AuthenticationResponse(jwt);

		
		String expirationDate= jwtUtil.extractExpiration(jwt).toString();
		 String input = expirationDate;
	        String outputPattern = "yyyy-MM-dd'T'HH:mm:ss";
	        String output = "";

	        try {
	            SimpleDateFormat inputFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
	            Date date = inputFormat.parse(input);

	            SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
	             output = outputFormat.format(date);

	            System.out.println(output);
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	    
		  response.getWriter().write(new JSONObject()
		  .put("userId", user.getId())
		  .put("role", user.getUserRole())
		  .put("token", jwt)
		  .put("expirationDate", output)
		  .toString()
		  );
		
		response.addHeader("Access-Control-Expose-Headers", "Authorization");
		response.addHeader("Access-Control-Allow-Headers", "Authorization, X-PINGGOTHER, Origin, X-Requested-With,Content-Type,Accept,X-Custom-header");
		response.addHeader(TOKEN_PREFIX,jwt);
		  
	}
	
	
	
	
}
