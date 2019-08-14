package com.polarising.PortalNet.CommandLineRunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.polarising.PortalNet.Repository.WorkersRepository;
import com.polarising.PortalNet.model.Workers;

@Component
public class WorkersCommandLineRunner implements CommandLineRunner{

	@Autowired
	WorkersRepository workersRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public void run (String... args) throws Exception {
		
//		workersRepository.save(new Workers(null, "Rui", "rui@worker.com", "EMPLOYEE", "yesno"));
//		workersRepository.save(new Workers(null, "Maria", "maria@worker.com", "EMPLOYEE", "yesno"));
//		workersRepository.save(new Workers(nu"Solange", "solange@admin.com", "ADMIN", "yesno"));
//		workersRepository.save(new Workers("Gonçalo", "rui@admin.com", "ADMIN", "noyes"));
		
		//Password encryption
		for (Workers worker : workersRepository.findAll())
		{
			worker.setPassword(passwordEncoder.encode(worker.getPassword()));
			
			workersRepository.save(worker);
		}
	}
}
