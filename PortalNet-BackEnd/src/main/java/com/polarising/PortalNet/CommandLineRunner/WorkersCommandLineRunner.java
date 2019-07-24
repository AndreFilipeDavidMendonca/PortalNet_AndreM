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
		
		workersRepository.save(new Workers("Rui", "rui@worker.com", "EMPLOYEE", "yesno"));
		workersRepository.save(new Workers("Maria", "maria@worker.com", "EMPLOYEE", "yesno"));
		workersRepository.save(new Workers("Solange", "solange@admin.com", "ADMIN", "yesno"));
		workersRepository.save(new Workers("Gonçalo", "rui@admin.com", "ADMIN", "noyes"));
		
		for (Workers worker : workersRepository.findAll())
		{
			worker.setPassword(passwordEncoder.encode(worker.getPassword()));
			
			workersRepository.save(worker);
		}
	}
}
