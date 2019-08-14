package com.polarising.PortalNet.Controller;


import java.util.ArrayList;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.polarising.PortalNet.Forms.LoginCredentials;
import com.polarising.PortalNet.Repository.ClientRepository;
import com.polarising.PortalNet.Repository.WorkersRepository;
import com.polarising.PortalNet.Security.UserDetailsService;
import com.polarising.PortalNet.Security.UserPrincipal;
import com.polarising.PortalNet.Utilities.PortalNetHttpRequest;
import com.polarising.PortalNet.Utilities.TibcoService;
import com.polarising.PortalNet.Utilities.XMLParser.ParseBodyXML;
import com.polarising.PortalNet.jwt.JwtCreator;
import com.polarising.PortalNet.jwt.JwtResponse;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	WorkersRepository workersRepository;
	
	@Autowired
	PortalNetHttpRequest portalNetHttpRequest;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	ParseBodyXML parseBodyXML;
	
	@Autowired
	TibcoService tibcoService;
	
	@Value("${portalnet.tibco.login.verification.soapAction}")
	private String loginSoapAction;
	
	@Value("${portalnet.tibco.login.verification.subPath}")
	private String loginSubPath;
	
	@Value("${portalnet.tibco.login.verification.specificVars}")
	private String[] getLoginVars;
	
	@Value("${portalnet.tibco.login.verification.soapRequestBody}")
	private String getLoginSoapRequestBody;

	@Value("${portalnet.tibco.client.getPersonalInfo.soapAction}")
	private String getClientSoapAction;
	
	@Value("${portalnet.tibco.client.getPersonalInfo.subPath}")
	private String getClientSubPath;
	
	@Value("${portalnet.tibco.client.getPersonalInfo.specificVars}")
	private String[] getClientInfoSpecificVars;
	
	@Value("${portalnet.tibco.client.getPersonalInfo.soapRequestBody}")
	private String getClientInfoSoapRequestBody;
	
	@Value("${portalnet.tibco.worker.getWorkerInfo.soapAction}")
	private String getWorkerSoapAction;
	
	@Value("${portalnet.tibco.worker.getWorkerInfo.subPath}")
	private String getWorkerSubPath;
	
	@Value("${portalnet.tibco.worker.getWorkerInfo.specificVars}")
	private String[] getWorkerInfoSpecificVars;
	
	@Value("${portalnet.tibco.login.port}")
	private int getLoginPort;
	
	//User login
	@PostMapping(path = "/home")
	public ResponseEntity<?> login(@RequestBody LoginCredentials user)
	{
		String jwt, name, message, response;
		int id;
		ArrayList<Map<String, String>> mapList;		
		
		try{	
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
			
			//Storing the details of the currently authenticated user (changing, if there was already one)
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
			
			//We make this request to obtain the user's name
			String requestUserSoapRequestBody = String.format(getClientInfoSoapRequestBody, userPrincipal.getUsername(), userPrincipal.getRole(), userPrincipal.getUsername());
			if (userPrincipal.getRole().equalsIgnoreCase("client"))
			{
				response = portalNetHttpRequest.postToTibco(getClientSubPath, requestUserSoapRequestBody, getClientSoapAction, 9010);
				mapList = parseBodyXML.parseResponseXML(response, getClientInfoSpecificVars);
			}
			else
			{
				response = portalNetHttpRequest.postToTibco(getWorkerSubPath, requestUserSoapRequestBody, getWorkerSoapAction, 9013);
				mapList = parseBodyXML.parseResponseXML(response, getWorkerInfoSpecificVars);
			}
			
			message = "Login bem sucedido.";
			
			if (userPrincipal.getAuthorities().toString().equals("[CLIENT]"))
				{
					name = mapList.get(1).get("clientName");
					id = Integer.parseInt(userPrincipal.getUsername());
					jwt = new JwtCreator().createJWT(authentication, id, name);
					return new ResponseEntity<>(new JwtResponse(jwt, message, userPrincipal.getAuthorities(), id, name), HttpStatus.OK);
				}
				else if (userPrincipal.getAuthorities().toString().equals("[EMPLOYEE]") || userPrincipal.getAuthorities().toString().equals("[ADMIN]"))
				{
					name = mapList.get(1).get("workerName");
					id = Integer.parseInt(userPrincipal.getUsername());
					jwt = new JwtCreator().createJWT(authentication, Integer.parseInt(userPrincipal.getUsername()), name);
					return new ResponseEntity<>(new JwtResponse(jwt, message, userPrincipal.getAuthorities(), id , name), HttpStatus.OK);
				}
				else {
					throw new AuthenticationCredentialsNotFoundException("Não foi possível encontrar o role do user.");
				}		
		}
		catch(AuthenticationException e)
		{
			logger.error(e.getMessage());
			message = "Password ou email errados.";
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
		catch (IndexOutOfBoundsException e) {
			logger.error(e.getMessage());
			message = "Erro a verificar email ou a criar token.";
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
	}
}
