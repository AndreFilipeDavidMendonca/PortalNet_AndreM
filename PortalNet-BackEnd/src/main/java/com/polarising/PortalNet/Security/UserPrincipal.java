package com.polarising.PortalNet.Security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javassist.NotFoundException;

public class UserPrincipal implements UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -278630287843293797L;
	
	private String id;
	private String role;
	private String password;
	private String email;
	
	public UserPrincipal(String id, String role, String password, String email) {
		super();
		this.id = id;
		this.role = role;
		this.password = password;
		this.email = email;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		try {
		if(role.equalsIgnoreCase("client"))
		{
			this.role = "client";
			return Collections.singleton(new SimpleGrantedAuthority("CLIENT"));
		}
		else if (role.equalsIgnoreCase("operator"))
		{
			this.role = "operator";
			return Collections.singleton(new SimpleGrantedAuthority("EMPLOYEE"));
		}
		else if (role.equalsIgnoreCase("administrator")) {
			this.role = "administrator";
			return Collections.singleton(new SimpleGrantedAuthority("ADMIN"));
		}
		else {
				throw new NotFoundException("Authority not found.");
		}
		} catch (NotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getPassword() {
		
		return password;
	}

	@Override
	public String getUsername() {
		
		return id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
