package com.polarising.PortalNet.Controller;

import org.slf4j.LoggerFactory;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.polarising.PortalNet.Forms.AssociatedServiceForm;
import com.polarising.PortalNet.Response.ResponseMessage;
import com.polarising.PortalNet.Utilities.DateFormatHelper;
import com.polarising.PortalNet.Utilities.PortalNetHttpRequest;
import com.polarising.PortalNet.Utilities.TibcoService;
import com.polarising.PortalNet.model.AssociatedService;

@RestController
public class AssociatedServiceController {
	
	private static final Logger logger = LoggerFactory.getLogger(AssociatedServiceController.class);

	@Autowired
	PortalNetHttpRequest httpRequest;
	
	@Autowired
	DateFormatHelper dateFormatHelper;
	
	@Autowired
	TibcoService tibcoService;
	
	
	//Associates a new service to a client
	@PostMapping(path = "/client")
	public ResponseEntity<?> associateNewService(@RequestBody AssociatedServiceForm associatedService)
	{
		String message;
		
		try{
			String[] credentials = tibcoService.getSecurityCredentials();
			
			AssociatedService newAssociatedService = new AssociatedService(tibcoService.getServiceNameFromServiceList(associatedService.getServiceID(),
																			credentials[0], credentials[1]), associatedService.getServiceID(),
																			associatedService.getInstallationAddress(),
																			associatedService.getPostalCode(),
																			associatedService.getLocality(),
																			dateFormatHelper.dateFormater(),
																			dateFormatHelper.addYearToDate(dateFormatHelper.dateFormater(), 1),
																			Integer.parseInt(credentials[0]),
																			tibcoService.getServiceMonthlyPay(associatedService.getServiceID(),credentials[0], credentials[1]));
			
			tibcoService.associateNewService(credentials[0], credentials[1], newAssociatedService, Integer.parseInt(associatedService.getClientNumber()));
			message = "Serviço foi associado!";
			return new ResponseEntity<>(new ResponseMessage(message), HttpStatus.OK);
		}
		catch (AuthenticationCredentialsNotFoundException e) {
			logger.error(e.getMessage());
			message = "Falhou o acesso à base de dados.";
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
	}
	
	//Updates an Associated Service
	@PutMapping(path = "/client/{associatedServiceID}/andre")
	public ResponseEntity<?> updateAssociatedService(@PathVariable String associatedServiceID, @RequestBody AssociatedServiceForm associatedService)
	{
		
		//Estou a ir buscar o associatedServiceID ao form, e não ao Path. Ver isso com o André
		String message;
		
		try{
			String[] credentials = tibcoService.getSecurityCredentials();
			
			AssociatedService updatedAssociatedService = new AssociatedService(Integer.parseInt(associatedService.getAssociatedServiceID()), tibcoService.getServiceNameFromServiceList(associatedService.getServiceID(),
					credentials[0], credentials[1]), associatedService.getServiceID(),
					associatedService.getInstallationAddress(),
					associatedService.getPostalCode(),
					associatedService.getLocality(),
					dateFormatHelper.dateFormater(),
					dateFormatHelper.addYearToDate(dateFormatHelper.dateFormater(), 1),
					Integer.parseInt(credentials[0]),
					tibcoService.getServiceMonthlyPay(associatedService.getServiceID(),credentials[0], credentials[1]));

			tibcoService.updateAssociatedService(credentials[0], credentials[1], updatedAssociatedService);
			
			message = "Serviço foi atualizado!";
			return new ResponseEntity<>(new ResponseMessage(message), HttpStatus.OK);
		}
		catch (AuthenticationCredentialsNotFoundException e) {
			logger.error(e.getMessage());
			message = "Falhou o acesso à base de dados.";
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
	}
	
	//Remove an associated service from a client
	@DeleteMapping(path = "/deleteAssociatedService/{associatedServiceID}")
	public ResponseEntity<?> removeAssociatedService(@PathVariable String associatedServiceID)
	{
		String message;
		
		try{
			String[] credentials = tibcoService.getSecurityCredentials();
			
			tibcoService.removeAssociatedService(credentials[0], credentials[1], associatedServiceID);
			
			message = "Serviço foi removido!";
			return new ResponseEntity<>(new ResponseMessage(message), HttpStatus.OK);
		}
		catch (AuthenticationCredentialsNotFoundException e) {
			logger.error(e.getMessage());
			message = "Falhou o acesso à base de dados.";
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
	}
	
	//Get a client's associated services
	@GetMapping(path = "/asServices/{clientId}")
	public ResponseEntity<?> getClientAssociatedServices(@PathVariable Integer clientId)
	{
		String message;
		
		try{
			String[] credentials = tibcoService.getSecurityCredentials();
			
			@SuppressWarnings("unchecked")
			List<AssociatedService> associatedServicesList = (List<AssociatedService>) tibcoService.transformList("AssociatedService", credentials[0], credentials[1], clientId);
			
			return new ResponseEntity<List<AssociatedService>>(associatedServicesList, HttpStatus.OK);
		}
		catch (AuthenticationCredentialsNotFoundException e) {
			logger.error(e.getMessage());
			message = "Falhou o acesso à base de dados.";
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
	}
}
