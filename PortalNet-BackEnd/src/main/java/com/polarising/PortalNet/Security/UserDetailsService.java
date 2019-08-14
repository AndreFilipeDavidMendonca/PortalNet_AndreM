package com.polarising.PortalNet.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.polarising.PortalNet.Utilities.PortalNetHttpRequest;
import com.polarising.PortalNet.Utilities.TibcoService;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
	
	@Autowired
	PortalNetHttpRequest portalNetHttpRequest;
	
	@Autowired
	TibcoService tibcoService;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
			
		try{			
			
			String[] credentials = tibcoService.login(email);
			
			return new UserPrincipal(credentials[0], credentials[1], credentials[2], email);
			}
		catch(UsernameNotFoundException e)
		{
			throw new UsernameNotFoundException("Username not found.");
		}
	}
}
