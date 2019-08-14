package com.polarising.PortalNet.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.polarising.PortalNet.Forms.ClientForm;
import com.polarising.PortalNet.Response.ResponseMessage;
import com.polarising.PortalNet.Utilities.NumberGenerator;
import com.polarising.PortalNet.Utilities.DateFormatHelper;
import com.polarising.PortalNet.Utilities.PortalNetHttpRequest;
import com.polarising.PortalNet.Utilities.TibcoService;
import com.polarising.PortalNet.Utilities.XMLParser.ParseBodyXML;
import com.polarising.PortalNet.model.Client;

import javassist.NotFoundException;

@RestController
@CrossOrigin(origins = "*")

public class ClientController {
	
	private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
	
	@Autowired
	PortalNetHttpRequest httpRequest;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	NumberGenerator clientNumberGenerator;
	
	@Autowired
	PortalNetHttpRequest portalNetHttpRequest;
	
	@Autowired
	DateFormatHelper dateFormatHelper;
	
	@Autowired
	ParseBodyXML parseBodyXML;
	
	@Autowired
	TibcoService tibcoService;
	
	@Value("${portalnet.tibco.standardVars}")
	private String[] getStandardVars;
	
	@Value("${portalnet.tibco.worker.getAllClients.subPath}")
	private String getAllClientsSubPath;
	
	@Value("${portalnet.tibco.worker.getAllClients.soapAction}")
	private String getAllClientsSoapAction;
	
	@Value("${portalnet.tibco.worker.getAllClients.specificVars}")
	private String[] getAllClientsSpecificVars;
	
	@Value("${portalnet.tibco.worker.getAllClients.soapRequestBody}")
	private String getAllClientsSoapRequestBody;
	
	@Value("${portalnet.tibco.worker.port}")
	private int getAllClientsPort;
	
	@Value("${portalnet.tibco.general.getAssociatedServices.subPath}")
	private String getAssociatedServicesSubPath;
	
	@Value("${portalnet.tibco.general.getAssociatedServices.soapAction}")
	private String getAssociatedServicesSoapAction;
	
	@Value("${portalnet.tibco.general.getAssociatedServices.specificVars}")
	private String[] getAssociatedServicesSpecificVars;
	
	@Value("${portalnet.tibco.general.getAssociatedServices.soapRequestBody}")
	private String getAssociatedServicesSoapRequestBody;
	
	@Value("${portalnet.tibco.general.port}")
	private int getAssociatedServicesPort;
	
	@Value("${portalnet.tibco.general.getAllServices.subPath}")
	private String getAllServicesSubPath;
	
	@Value("${portalnet.tibco.general.getAllServices.soapAction}")
	private String getAllServicesSoapAction;
	
	@Value("${portalnet.tibco.general.getAllServices.specificVars}")
	private String[] getAllServicesSpecificVars;
	
	@Value("${portalnet.tibco.general.getAllServices.soapRequestBody}")
	private String getAllServicesSoapRequestBody;
	
	//Obtain client's list
	@GetMapping(path = "/clientsTable", produces= {"application/json"})
	public ResponseEntity<?> getClients()
	{	
		String message;
		
		try{
			String[] credentials = tibcoService.getSecurityCredentials();

			List<Object> clientsList = tibcoService.performTibcoListAction("getAllClients", credentials[0], credentials[1], null);
			
			return new ResponseEntity<List<Object>>((List<Object>) clientsList, HttpStatus.OK);
		}
		catch (IndexOutOfBoundsException e)
		{
			logger.error(e.getMessage() + "--> Some list is empty. Maybe in database.");
			e.printStackTrace();
			message = "Alguma lista encontra-se vazia. Podem não existir clientes na base de dados.";
			return new ResponseEntity<>(new ResponseMessage(message), HttpStatus.NOT_FOUND);
		}
		catch(AuthenticationCredentialsNotFoundException e)
		{
			logger.error(e.getMessage());
			message = "Falhou o acesso à base de dados.";
			return new ResponseEntity<>(message, HttpStatus.UNAUTHORIZED);
		}
	}
	
	//Obtain a specific client
	@GetMapping(path = "/client/{clientId}", produces= {"application/json"})
	public ResponseEntity<?> getClientById(@PathVariable int clientId)
	{	
		String message;
		String[] credentials = tibcoService.getSecurityCredentials();
		
		try{
			List<Object> clientList = tibcoService.performTibcoListAction("getAllClients", credentials[0], credentials[1], null);
			Client client = tibcoService.getClient(clientList, clientId);
			
			return new ResponseEntity<Client> (client, HttpStatus.OK);
		}
		catch(NotFoundException e)
		{
			logger.error(e.getMessage());
			message = "O utilizador não foi encontrado.";
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}
		catch (AuthenticationCredentialsNotFoundException e) {
			logger.error(e.getMessage());
			message = "Falhou o acesso à base de dados.";
			return new ResponseEntity<>(message, HttpStatus.UNAUTHORIZED);
		}
	}
	
	//Update Client details
	@PutMapping(path = "/client/{clientId}")
	public ResponseEntity<?> updateClient(@PathVariable int clientId, @RequestBody Client client)
	{
		String message;
		String[] credentials = tibcoService.getSecurityCredentials();
		
		try{
			tibcoService.modifyClient(credentials[0], credentials[1], client, clientId);
			
			return new ResponseEntity<> (new ResponseMessage("Atualização bem sucedida."), HttpStatus.OK);
		}
		catch(AuthenticationCredentialsNotFoundException e)
		{
			logger.error(e.getMessage());
			message = "Falhou o acesso à base de dados.";
			return new ResponseEntity<>(message, HttpStatus.UNAUTHORIZED);
		}

		catch(ClassCastException e)
		{
			logger.error(e.getMessage());
			message = "Os dados do cliente foram mal introduzidos ou estão em falta.";
			return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//Client registration
	@PostMapping(path = "/registration", consumes = {"application/json"})
	public ResponseEntity<?> registerClient(@RequestBody ClientForm clientForm)
	{	
		String message;
		
		try{
			
			String clientNumber = clientNumberGenerator.generateNumber();
			String entryDate = dateFormatHelper.dateFormater();
			String endContract = dateFormatHelper.addYearToDate(entryDate, 1);
			int numberOfServices = 1;
			float monthlyPay = tibcoService.getServicePriceFromServiceList(clientForm.getServiceName(), "", "");
			boolean fraudulent = false;
			boolean status = true;
			String clientRole = "client";
			
			Client newClient = new Client(Integer.parseInt(clientNumber), clientForm.getNif(), clientForm.getName(), clientForm.getAddress(),
					clientForm.getPostalCode(), clientForm.getCity(), clientForm.getMobilePhone(), clientForm.getPhone(),
					clientForm.getEmail(), clientForm.getGender(), clientForm.getPassword(), entryDate, 
					endContract, numberOfServices, clientForm.getServiceName(), 
					monthlyPay, fraudulent, status, clientForm.getBirthDate(), clientRole);
			
			//Hashing the password
			newClient.setPassword(passwordEncoder.encode(newClient.getPassword()));
			
//			@SuppressWarnings("unchecked")
//			List<Client> clientsList = (List<Client>) tibcoService.transformList("Client", credentials[0], credentials[1], null);
//			
//			//Checking for already existing clients
//			for (Client client : clientsList)
//			{
//				if (client.getNif() == newClient.getNif())
//				{
//					message = "Já existe um utilizador com este NIF!";
//					
//					return new ResponseEntity<String> (message, HttpStatus.CONFLICT);
//				}
//				else if (client.getEmail().equals(newClient.getEmail()))
//				{
//					message = "Já existe um utilizador com este email!";
//					
//					return new ResponseEntity<String> (message, HttpStatus.CONFLICT);
//				}
//				else if ((client.getMobilePhone() == newClient.getMobilePhone()) && newClient.getMobilePhone() != -1)
//				{
//					message = "Já existe um utilizador com este número de telemóvel!";
//					
//					return new ResponseEntity<String> (message, HttpStatus.CONFLICT);
//				}
//				
//				if (client.getClientId() == newClient.getClientId())
//				{
//					clientNumber = clientNumberGenerator.generateNumber();
//				}
//			}
			
			tibcoService.registClient(newClient);
			
			message = clientForm.getName() + " foi registado com sucesso!";
			return new ResponseEntity<>(new ResponseMessage(message), HttpStatus.OK);
		}
		catch (AuthenticationCredentialsNotFoundException e) {
			
			if (e.getMessage().contains("Mobile Number"))
			{
				message = "Número de telemóvel já existe.";
			}
			else if(e.getMessage().contains("Nif"))
			{
				message = "Nif já existe.";
			}
			else if(e.getMessage().contains("email"))
			{
				message = "Email já existe.";
			}
			logger.error(e.getMessage());
			message = "Falhou o acesso à base de dados.";
			return new ResponseEntity<>(message, HttpStatus.UNAUTHORIZED);
		}
	}
}