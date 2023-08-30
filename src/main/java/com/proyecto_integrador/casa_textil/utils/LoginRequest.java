package com.proyecto_integrador.casa_textil.utils;

import org.springframework.stereotype.Component;

@Component
public class LoginRequest {
	private String email;
	private String password;
	
	public LoginRequest() {
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	
}
