package com.polarising.PortalNet.Controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.polarising.PortalNet.Forms.WorkersForm;
import com.polarising.PortalNet.Response.ResponseMessage;
import com.polarising.PortalNet.Utilities.NumberGenerator;
import com.polarising.PortalNet.Utilities.PortalNetHttpRequest;
import com.polarising.PortalNet.Utilities.TibcoService;
import com.polarising.PortalNet.model.Workers;

@RestController
@CrossOrigin(origins = "*")
public class WorkersController {
	
	private static final Logger logger = LoggerFactory.getLogger(WorkersController.class);
	
	@Autowired
	PortalNetHttpRequest httpRequest;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	TibcoService tibcoService;
	
	@Autowired
	NumberGenerator numberGenerator;
	
	//Obtains the employees table
	@GetMapping(path = "/employeesTable", produces = {"application/json"})
	public ResponseEntity<?> getWorkers()
	{
		String message;
		String[] credentials = tibcoService.getSecurityCredentials();

		try{
			
			return new ResponseEntity<>(tibcoService.transformList("Worker", credentials[0], credentials[1], null), HttpStatus.OK);
		}
		catch (AuthenticationCredentialsNotFoundException e) {
			logger.error(e.getMessage());
			message = "Falhou o acesso à base de dados.";
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}	
	}
	
	//Creates an employee
	@PostMapping(path = "/createEmployee", consumes = {"application/json"})
	public ResponseEntity<?> registerWorker(@Valid @RequestBody WorkersForm workersForm)
	{
		String message;
		String[] credentials = tibcoService.getSecurityCredentials();
		
		try {

			Workers worker = new Workers(Integer.parseInt(numberGenerator.generateNumber()), workersForm.getName(), workersForm.getEmail()
										, workersForm.getRole(), workersForm.getPassword());
			
			//Hash password
			worker.setPassword(passwordEncoder.encode(worker.getPassword()));
			
			@SuppressWarnings("unchecked")
			List<Workers> workersList = (List<Workers>) tibcoService.transformList("Worker", credentials[0], credentials[1], null);

			//Verify if the worker already exists with the given email
			for (Workers foundWorker : workersList)
			{
				if (foundWorker.getEmail().equals(worker.getEmail()))
				{
					message = "Colaborador com este e-mail já foi registado!";
					return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
				}
				else if (!worker.getEmail().split("@")[1].equals("portalnet.com"))
				{
					message = "Colaborador tem de ter email a acabar em \"@portalnet\".";
					return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
				}
				
				if (foundWorker.getEmployeeId().equals(worker.getEmployeeId()))
				{
					worker.setEmployeeId(Integer.parseInt(numberGenerator.generateNumber()));
				}
			}
			
			tibcoService.createWorker(credentials[0], credentials[1], worker);
			message = "Colaborador registado!";
			return new ResponseEntity<>(new ResponseMessage(message), HttpStatus.OK);
			
		} catch (RestClientException e) {
			
			logger.error(e.getMessage());
			message = "Não foi possivel registar o colaborador!";
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
		catch (AuthenticationCredentialsNotFoundException e) {
			logger.error(e.getMessage());
			message = "Falhou o acesso à base de dados.";
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
	} 
	
	//Deletes an employee
	@DeleteMapping(path = "/employeesTable/{employeeId}")
	public ResponseEntity<?> deleteWorker(@PathVariable Integer employeeId)
	{
		String message;
		String[] credentials = tibcoService.getSecurityCredentials();

		try{
			
			if (tibcoService.compareWorkerId(employeeId, credentials[0], credentials[1]))
			{
				tibcoService.removeWorker(credentials[0], credentials[1], employeeId);
				message = employeeId + " foi eliminado.";
				return new ResponseEntity<>(new ResponseMessage(message), HttpStatus.OK);
			}
			else{
				message = "O trabalhador com o Id: \"" + employeeId + "\" não existe.";
				return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
			}		
		}
		catch (AuthenticationCredentialsNotFoundException e) {
			logger.error(e.getMessage());
			message = "Falhou o acesso à base de dados.";
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
	}
}
