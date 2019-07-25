package com.polarising.PortalNet.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.polarising.PortalNet.Repository.WorkersRepository;
import com.polarising.PortalNet.Response.ResponseMessage;
import com.polarising.PortalNet.Utilities.PortalNetHttpRequest;
import com.polarising.PortalNet.model.Workers;

@RestController
@CrossOrigin(origins = "*")
public class WorkersController {

	@Autowired
	WorkersRepository workersRepository;
	
	@Autowired
	PortalNetHttpRequest httpRequest;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	//Obtains the employees table
	@GetMapping(path = "/employeesTable", produces = {"application/json"})
	public ResponseEntity<?> getWorkers()
	{
		return new ResponseEntity<List<Workers>>((List<Workers>) workersRepository.findAll(), HttpStatus.OK);
	}
	
	//Creates an employee
	@PostMapping(path = "/createEmployee", consumes = {"application/json"})
	public ResponseEntity<?> registerWorker(@Valid @RequestBody WorkersForm workersForm)
	{
		String message;
		
		try {
			Workers worker = new Workers(workersForm.getName(), workersForm.getEmail()
										, workersForm.getRole(), workersForm.getPassword());
			
			//Hash password
			worker.setPassword(passwordEncoder.encode(worker.getPassword()));
			
			List<Workers> workersList = (List<Workers>) workersRepository.findAll();

			//Verify if the worker already exists with the given email
			for (Workers foundWorker : workersList)
			{
				if (foundWorker.getEmail().equals(worker.getEmail()))
				{
					return new ResponseEntity<String>("Colaborador com este e-mail já foi registado!", HttpStatus.FOUND);
				}
			}
			
			workersRepository.save(worker);
			message = "Colaborador registado!";
			return new ResponseEntity<>(new ResponseMessage(message), HttpStatus.OK);
			
		} catch (RestClientException e) {
			
			message = "Não foi possivel registar o colaborador!";
			return new ResponseEntity<>(new ResponseMessage(message), HttpStatus.OK);
		}
	} 
	
	//Deletes an employee
	@DeleteMapping(path = "/employeesTable/{employeeId}")
	public ResponseEntity<?> deleteWorker(@PathVariable Integer employeeId)
	{
		String message;
		
		if (workersRepository.existsById(employeeId))
		{
			String workerName = workersRepository.findById(employeeId).get().getName();
			workersRepository.deleteById(employeeId);
			message = workerName + " foi eliminado.";
			return new ResponseEntity<>(new ResponseMessage(message), HttpStatus.OK);
		}
		else{
			message = "O trabalhador com o Id: \"" + employeeId + "\" não existe.";
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}		
	}
}
