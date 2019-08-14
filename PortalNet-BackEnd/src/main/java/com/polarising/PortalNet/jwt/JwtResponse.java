package com.polarising.PortalNet.jwt;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class JwtResponse {

	private String jwt;
	private String message;
	private Collection<? extends GrantedAuthority> authorities;
	private int userId;
	private String userName;
	
	public JwtResponse() {};
	
	//Defining object to be sent to front-end once authenticated
	public JwtResponse(String jwt, String message, Collection<? extends GrantedAuthority> authorities, int userId, String userName) {
		super();
		this.jwt = jwt;
		this.message = message;
		this.authorities = authorities;
		this.userId = userId;
		this.userName = userName;
	}

	public String getJwt() {
		return jwt;
	}

	public String getMessage() {
		return message;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getUserId() {
		return userId;
	}

	public void setId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setName(String userName) {
		this.userName = userName;
	}
}
