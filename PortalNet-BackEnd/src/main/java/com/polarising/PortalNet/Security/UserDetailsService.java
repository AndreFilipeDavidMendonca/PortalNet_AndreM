package com.polarising.PortalNet.Security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.polarising.PortalNet.Repository.ClientRepository;
import com.polarising.PortalNet.Repository.WorkersRepository;
import com.polarising.PortalNet.model.Client;
import com.polarising.PortalNet.model.Workers;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private WorkersRepository workersRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		try{
			
			if (clientRepository.existsByEmail(email))
			{
				Client client = clientRepository.findByEmail(email).get(0);
				
				if (client == null)
				{
					throw new UsernameNotFoundException(email);
				}
				
				return new UserPrincipal(client);
			}
			else if (workersRepository.existsByEmail(email))
			{
				Workers worker = workersRepository.findByEmail(email).get(0);
				
				if (worker == null)
				{
					throw new UsernameNotFoundException(email);
				}
				
				return new UserPrincipal(worker);
			}
			else
			{
				throw new UsernameNotFoundException("Username not found.");
			}
		}
		catch(UsernameNotFoundException e)
		{
			throw new UsernameNotFoundException("Username not found.");
		}
	}
}
