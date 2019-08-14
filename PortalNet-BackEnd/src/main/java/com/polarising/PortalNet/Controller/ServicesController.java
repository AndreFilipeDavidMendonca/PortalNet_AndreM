package com.polarising.PortalNet.Controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.polarising.PortalNet.Forms.ServiceForm;
import com.polarising.PortalNet.Response.ResponseMessage;
import com.polarising.PortalNet.Utilities.DateFormatHelper;
import com.polarising.PortalNet.Utilities.PortalNetHttpRequest;
import com.polarising.PortalNet.Utilities.TibcoService;
import com.polarising.PortalNet.model.Services;

@RestController
@CrossOrigin(origins = "*")
public class ServicesController {
	
	private static final Logger logger = LoggerFactory.getLogger(ServicesController.class);
	
	@Autowired
	PortalNetHttpRequest httpRequest;
	
	@Autowired
	DateFormatHelper dateFormatHelper;
	
	@Autowired
	TibcoService tibcoService;
	
	//Obtain list of services (accessible to ADMIN and EMPLOYEE)
	@SuppressWarnings("unchecked")
	@GetMapping(path = "/servicesTable", produces= {"application/json"})
	public List<Services> getServices()
	{
		String[] credentials = tibcoService.getSecurityCredentials();
		
		return (List<Services>) tibcoService.transformList("Service", credentials[0], credentials[1], null);
	}
	
	//Update service details
	@PutMapping(path = "/servicesTable")
	public ResponseEntity<?> updateService (@RequestBody Services service)
	{
		String message;
		try{
			String[] credentials = tibcoService.getSecurityCredentials();
			
			if (!tibcoService.compareServiceId(service.getServiceID().toString(), credentials[0], credentials[1]))
			{   	          
				message = "O serviço não existe.";
				return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
			}
			else
			{
				tibcoService.modifyService(credentials[0], credentials[1], service);
				message = service.getName() + " foi atualizado.";
				return new ResponseEntity<>(new ResponseMessage(message), HttpStatus.OK);
			}			
		}
		catch (AuthenticationCredentialsNotFoundException e) {
			logger.error(e.getMessage());
			message = "Falhou o acesso à base de dados.";
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
	}	
	
	//Obtain list of services for home page (accessible to all)
	@SuppressWarnings("unchecked")
	@GetMapping(path = "/home", produces= {"application/json"})
	public List<Services> getServicesForHomePage()
	{
		return (List<Services>) tibcoService.transformList("Service", null, null, null);
	}
	
	//Get service by name
	@GetMapping(path = "/registration/{name}", produces= {"application/json"})
	public ResponseEntity<?> getByName(@PathVariable String name)
	{	
		
		return new ResponseEntity<>(tibcoService.getServiceWithName(name), HttpStatus.OK);
	}
	
	//Get service by Id
	@GetMapping(path = "/servicesTable/{serviceId}", produces= {"application/json"})
	public ResponseEntity<?> getById(@PathVariable String serviceId)
	{	
			
		return new ResponseEntity<>(tibcoService.getServiceWithId(serviceId), HttpStatus.OK);
	}
	
	//Create a service
	@PostMapping(path = "/createService", consumes = {"application/json"})
	public ResponseEntity<?> registerService(@RequestBody ServiceForm serviceForm)
	{	
		String[] credentials = tibcoService.getSecurityCredentials();
		String message;

		String name = serviceForm.getName();
		String creationDate = dateFormatHelper.dateFormater();
		boolean status = true;
		
		Services newService = new Services(serviceForm.getName(), serviceForm.getTv(), serviceForm.getInternet(), serviceForm.getPhone(),
											serviceForm.getMobilePhone(), serviceForm.getLoyalty(), serviceForm.getPrice(), creationDate, status, serviceForm.getImgUrl(), serviceForm.getImgName());

		
		if (tibcoService.compareServiceName(newService.getName(), credentials[0], credentials[1]))
		{
			message = "Já existe um serviço com este nome!";
				
			return new ResponseEntity<String> (message, HttpStatus.CONFLICT);
		} 
		
		tibcoService.createService(credentials[0], credentials[1], newService);
		message = "O serviço " + name + " foi registado com sucesso!";
		return new ResponseEntity<>(new ResponseMessage(message), HttpStatus.OK);
	}
}


