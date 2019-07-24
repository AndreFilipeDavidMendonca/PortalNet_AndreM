package com.polarising.PortalNet.Controller;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.polarising.PortalNet.Forms.ServiceForm;
import com.polarising.PortalNet.Repository.ServiceRepository;
import com.polarising.PortalNet.Response.ResponseMessage;
import com.polarising.PortalNet.Utilities.PortalNetHttpRequest;
import com.polarising.PortalNet.model.Services;

@RestController
@CrossOrigin(origins = "*")
public class ServicesController {
	
	@Autowired
	ServiceRepository serviceRepository;
	
	@Autowired
	PortalNetHttpRequest httpRequest;
	
	@RequestMapping(path = "/servicesTable", produces= {"application/json"})
	public List<Services> getServices()
	{
		return (List<Services>) serviceRepository.findAll();
	}
	
	@GetMapping(path = "/home", produces= {"application/json"})
	public List<Services> getServicesForHomePage()
	{
		return (List<Services>) serviceRepository.findAll();
	}
	
	@GetMapping(path = "/registration/{name}", produces= {"application/json"})
	public ResponseEntity<?> getByName(@PathVariable String name)
	{	
		return new ResponseEntity<List<Services>>((List<Services>) serviceRepository.findByName(name), HttpStatus.OK);
	} 
	
	@PostMapping(path = "/createService", consumes = {"application/json"})
	public ResponseEntity<?> registerService(@RequestBody ServiceForm serviceForm)
	{	
		String message;
		String name;

		
		name = serviceForm.getName();
		String imgUrl = "assets/img/" + serviceForm.getImgName();
		String creationDate = Calendar.getInstance().getTime().toString();
		boolean status = true;
		
		
	
		
		Services newService = new Services(serviceForm.getName(), serviceForm.getTv(), serviceForm.getInternet(), serviceForm.getPhone(),
											serviceForm.getMobilePhone(), serviceForm.getLoyalty(), serviceForm.getPrice(), creationDate, status, imgUrl, serviceForm.getImgName());
		
		List<Services> servicesList = (List<Services>) serviceRepository.findAll();
		
		
		for (Services service : servicesList)
		{
			if (service.getName().equals(newService.getName()))
			{
				message = "Já existe um serviço com este nome!";
				
				return new ResponseEntity<String> (message, HttpStatus.CONFLICT);
			} 
		}
		
		serviceRepository.save(newService);		
		message = "O serviço " + name + " foi registado com sucesso!";
		return new ResponseEntity<>(new ResponseMessage(message), HttpStatus.OK);
	}
	
//	@PutMapping(path = "/servicesTable/{serviceID}")
//	public ResponseEntity<?> updateService (@PathVariable Long serviceID)
//	{
//		String message;
//		String serviceName;
//		
//		if (serviceRepository.existsById(serviceID))
//		{	
//			if (serviceRepository.findByServiceID(serviceID).get(0).isStatus() == true)
//			{
//				serviceRepository.findByServiceID(serviceID).get(0).setStatus(false);
//				serviceRepository.save(serviceRepository.findByServiceID(serviceID).get(0));
//			}
//			else
//			{
//				serviceRepository.findByServiceID(serviceID).get(0).setStatus(true);
//				serviceRepository.save(serviceRepository.findByServiceID(serviceID).get(0));
//			}
//			
//			serviceName = serviceRepository.findById(serviceID).get().getName();
//			message = serviceName + " foi atualizado.";
//			
//			return new ResponseEntity<>(new ResponseMessage(message), HttpStatus.OK);
//		}
//		else
//		{
//			message = "O serviço não existe.";
//			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
//		}
//	}
	
	@PutMapping(path = "/servicesTable")
    public ResponseEntity<?> updateService (@RequestBody Services service)
    {
        String message;
        String serviceName;
       
        if (serviceRepository.existsById(service.getServiceID()))
        {   
           
            serviceName = serviceRepository.findById(service.getServiceID()).get().getName();
           
            message = serviceName + " foi atualizado.";
           
            serviceRepository.save(service);
           
            return new ResponseEntity<>(new ResponseMessage(message), HttpStatus.OK);
           
        }
        else
        {
            message = "O serviço não existe.";
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
}


