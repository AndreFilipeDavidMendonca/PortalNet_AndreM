package com.polarising.PortalNet.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.polarising.PortalNet.Forms.ClientForm;
import com.polarising.PortalNet.Repository.ClientRepository;
import com.polarising.PortalNet.Repository.ServiceRepository;
import com.polarising.PortalNet.Response.ResponseMessage;
import com.polarising.PortalNet.Utilities.ClientNumberGenerator;
import com.polarising.PortalNet.Utilities.DateFormatHelper;
import com.polarising.PortalNet.Utilities.PortalNetHttpRequest;
import com.polarising.PortalNet.model.Client;

@RestController
@CrossOrigin(origins = "*")

public class ClientController {
	
	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	ServiceRepository serviceRepository;
	
	@Autowired
	PortalNetHttpRequest httpRequest;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	ClientNumberGenerator clientNumberGenerator;
	
	@Autowired
	DateFormatHelper dateFormatHelper;
	
	//Obtain client's list
	@GetMapping(path = "/clientsTable", produces= {"application/json"})
	public ResponseEntity<?> getClients()
	{	
		return new ResponseEntity<List<Client>>((List<Client>) clientRepository.findAll(), HttpStatus.OK);
	}
	
	//Obtain a specific client
	@GetMapping(path = "/client/{clientId}", produces= {"application/json"})
	public ResponseEntity<?> getClientById(@PathVariable int clientId)
	{	
		return new ResponseEntity<List<Client>>((List<Client>) clientRepository.findByClientId(clientId), HttpStatus.OK);
	}
	
	//Client registration
	@PostMapping(path = "/registration", consumes = {"application/json"})
	public ResponseEntity<?> registerClient(@RequestBody ClientForm clientForm)
	{	
		String message;
		String clientNumber = clientNumberGenerator.generateClientNumber();	
		String entryDate = dateFormatHelper.dateFormater();
		String endContract = dateFormatHelper.addYearToDate(entryDate, 1);
		int numberOfServices = 1;
		float monthlyPay = serviceRepository.findByName(clientForm.getServiceName()).get(0).getPrice();
		boolean fraudulent = false;
		boolean status = true;
		String role = "CLIENT";
		
		Client newClient = new Client(clientNumber, clientForm.getNif(), clientForm.getName(), clientForm.getAddress(),
										clientForm.getPostalCode(), clientForm.getCity(), clientForm.getMobilePhone(), clientForm.getPhone(),
										clientForm.getEmail(), clientForm.getGender(), clientForm.getPassword(), entryDate, 
										endContract, numberOfServices, clientForm.getServiceName(), 
										monthlyPay, fraudulent, status, clientForm.getBirthDate(), role);
		
		//Hashing the password
		newClient.setPassword(passwordEncoder.encode(newClient.getPassword()));
		
		List<Client> clientsList = (List<Client>) clientRepository.findAll();
		
		//Checking for already existing clients
		for (Client client : clientsList)
		{
			if (client.getNif() == (newClient.getNif()))
			{
				message = "Já existe um utilizador com este NIF!";
				
				return new ResponseEntity<String> (message, HttpStatus.CONFLICT);
			} 
		}
		
		clientRepository.save(newClient);
		message = clientForm.getName() + " foi registado com sucesso!";
		return new ResponseEntity<>(new ResponseMessage(message), HttpStatus.OK);
	}
	
	//Update Client details
	@PutMapping(path = "/client/{clientId}")
	public ResponseEntity<?> updateClient(@PathVariable int clientId, @RequestBody Client client)
	{
		try{
		
//		Client clientToUpdate = clientRepository.findByClientId(clientId).get(0);
			
//		clientToUpdate.setClientId(client.getClientId());
//		clientToUpdate.setAddress(client.getAddress());
//		clientToUpdate.setCity(client.getCity());
//		clientToUpdate.setEmail(client.getEmail());
//		clientToUpdate.setFraudulent(client.isFraudulent());
//		clientToUpdate.setMobilePhone(client.getMobilePhone());
//		clientToUpdate.setMonthlyPay(newMonthlyPay);
//		clientToUpdate.setName(client.getName());
//		clientToUpdate.setPassword(client.getPassword());
//		clientToUpdate.setPostalCode(client.getPostalCode());
//		clientToUpdate.setServiceName(client.getServiceName());
//		clientToUpdate.setStatus(client.isStatus());

		float newMonthlyPay = serviceRepository.findByName(client.getServiceName()).get(0).getPrice();
		client.setMonthlyPay(newMonthlyPay);
		clientRepository.save(client);
		String message = "Atualização bem sucedida.";
		
		return new ResponseEntity<>(new ResponseMessage(message), HttpStatus.OK);
		}
		catch(ClassCastException e)
		{
			return new ResponseEntity<Client>(client, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		catch(IndexOutOfBoundsException e)
		{	
			String message;
			
			if (!clientRepository.existsById(clientId))
			{
				message = "Id de cliente não existe.";
			}
			else if (!serviceRepository.existsByName(client.getServiceName()))
			{
				message = "O nome do novo serviço não se encontra na lista de serviços disponíveis.";
			}
			else {
				message = null;
			}
			return new ResponseEntity<String>(message,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}