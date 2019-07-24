package com.polarising.PortalNet.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.polarising.PortalNet.Forms.LoginCredentials;
import com.polarising.PortalNet.Repository.ClientRepository;
import com.polarising.PortalNet.Repository.WorkersRepository;
import com.polarising.PortalNet.Security.UserPrincipal;
import com.polarising.PortalNet.jwt.JwtCreator;
import com.polarising.PortalNet.jwt.JwtResponse;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	WorkersRepository workersRepository;
		
	@PostMapping(path = "/home")
	public ResponseEntity<?> login(@RequestBody LoginCredentials user)
	{
		try{
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
		
			SecurityContextHolder.getContext().setAuthentication(authentication);
		
//			String jwt = new JwtCreator().createJWT(authentication);
			String jwt;
			
			UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		
			String message = "Login bem sucedido.";
			
			if (userPrincipal.getAuthorities().toString().equals("[CLIENT]"))
				{
					String clientName = clientRepository.findByEmail(userPrincipal.getUsername()).get(0).getName();
					String clientId = clientRepository.findByEmail(userPrincipal.getUsername()).get(0).getClientId().toString();
					jwt = new JwtCreator().createJWT(authentication, clientId, clientName);
					return new ResponseEntity<>(new JwtResponse(jwt, message, userPrincipal.getAuthorities(), clientId, clientName), HttpStatus.OK);
				}
				else if (userPrincipal.getAuthorities().toString().equals("[EMPLOYEE]"))
				{
					String employeeName = workersRepository.findByEmail(userPrincipal.getUsername()).get(0).getName();
					String employeeId = workersRepository.findByEmail(userPrincipal.getUsername()).get(0).getEmployeeId().toString();
					jwt = new JwtCreator().createJWT(authentication, employeeId, employeeName);
					return new ResponseEntity<>(new JwtResponse(jwt, message, userPrincipal.getAuthorities(), employeeId, employeeName), HttpStatus.OK);
				}
				else if (userPrincipal.getAuthorities().toString().equals("[ADMIN]"))
				{
					String adminName = workersRepository.findByEmail(userPrincipal.getUsername()).get(0).getName();
					String adminId = workersRepository.findByEmail(userPrincipal.getUsername()).get(0).getEmployeeId().toString();
					jwt = new JwtCreator().createJWT(authentication, adminId, adminName);
					return new ResponseEntity<>(new JwtResponse(jwt, message, userPrincipal.getAuthorities(), adminId, adminName), HttpStatus.OK);
				}
				else {
					throw new AuthenticationCredentialsNotFoundException("Não foi possível encontrar o role do user.");
				}
		
//			return new ResponseEntity<>(new JwtResponse(jwt, message, userPrincipal.getAuthorities()), HttpStatus.OK);
		
		}
		catch(AuthenticationException e)
		{
			String message = "Password ou email errados.";
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
		catch (IndexOutOfBoundsException e) {
			String message = "Erro a verificar email ou a criar token.";
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/")
	public String homePage()
	{
		return "Hey!";
	}
}
