package com.example.ecommerce.dto;

import lombok.Data;

@Data
public class AuthenticationResponse {

	private String jwtToken;
	
	
	

	public AuthenticationResponse(String jwtToken) {
		super();
		this.jwtToken = jwtToken;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
	
	
	
}
