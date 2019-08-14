
package com.polarising.PortalNet.CommandLineRunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.polarising.PortalNet.Repository.ClientRepository;
import com.polarising.PortalNet.Utilities.NumberGenerator;
import com.polarising.PortalNet.Utilities.DateFormatHelper;
import com.polarising.PortalNet.model.Client;

@Component
public class ClientCommandLineRunner implements CommandLineRunner {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private DateFormatHelper dateFormatHelper;
	
	@Autowired
	private NumberGenerator clientNumberGenerator;

	public void run(String... args) throws Exception {
	
//		Integer clientNumber = clientNumberGenerator.generateClientNumber();
//		
//		String entryDate = dateFormatHelper.dateFormater();
//		
//		String endContract = dateFormatHelper.addYearToDate(entryDate, 1);
		
		//Filling database
		
//		clientRepository.save(new Client(clientNumber, 1111111, "Andre", "Rua da Alegria", "1000-245",
//				"Lisboa", 911111111, 111111111, "andre@polarising.com", "male",
//				"bernardoAdmin", entryDate, endContract, 2, "Blue Master 1",  50.0f, true, true, "18/01/1989", "CLIENT"));
//		
//		clientNumber = ("" + (Long.parseLong(clientNumber) + 1));
//		
//		clientRepository.save(new Client(clientNumber, 222222222, "Bernardo", "Rua da Alegria", "1000-245",
//				"Lisboa", 911111111, 222222222, "bernardo@polarising.com", "male",
//				"bernardoAdmin", entryDate, endContract, 2, "Pacote 4 Serviços",  60.0f, true, true, "25/11/1995", "CLIENT"));
//		
//		clientNumber = ("" + (Long.parseLong(clientNumber) + 1));
//		
//		clientRepository.save(new Client(clientNumber, 333333333, "Rui", "Rua da Alegria", "1000-245",
//				"Lisboa", 911111111, 333333333, "rui@polarising.com", "male",
//				"bernardoAdmin", entryDate, endContract, 2, "Blue Master 2",  45.0f, false, false, "18/01/1989", "CLIENT"));
//		
//		clientNumber = ("" + (Long.parseLong(clientNumber) + 1));
//		
//		clientRepository.save(new Client(clientNumber, 444444444, "Daniel", "Rua da Alegria", "1000-245",
//				"Lisboa", 911111111, 444444444, "bernardo@polarising.com", "male",
//				"bernardoAdmin", entryDate, endContract, 2, "Pacote 4 Serviços",  100.0f, false, true, "18/01/1989", "CLIENT"));
//		
//		clientNumber = ("" + (Long.parseLong(clientNumber) + 1));
//		
//		clientRepository.save(new Client(clientNumber, 555555555, "Eliana", "Rua da Alegria", "1000-245",
//				"Lisboa", 911111111, 555555555, "bernardo@polarising.com", "male",
//				"bernardoAdmin", entryDate, endContract, 2, "Blue Master 3", 50.0f, true, false, "18/01/1989", "CLIENT"));
//		
//		clientNumber = ("" + (Long.parseLong(clientNumber) + 1));
//		
//		clientRepository.save(new Client(clientNumber,666666666, "Perdiz", "Rua da Alegria", "1000-245",
//				"Lisboa", 911111111, 666666666, "bernardo@polarising.com", "male",
//				"bernardoAdmin", entryDate, endContract, 2, "Pacote 4 Serviços",  100.0f, true, true, "18/01/1989", "CLIENT"));
//		
//		clientNumber = ("" + (Long.parseLong(clientNumber) + 1));
//		
//		clientRepository.save(new Client(clientNumber, 7111111, "Renato", "Rua da Alegria", "1000-245",
//				"Lisboa", 911111111, 777777777, "bernardo@polarising.com", "male",
//				"bernardoAdmin", entryDate, endContract, 2, "Pacote 4 Serviços",  100.0f, true, true, "18/01/1989", "CLIENT"));
//		
//		clientNumber = ("" + (Long.parseLong(clientNumber) + 1));
//		
//		clientRepository.save(new Client(clientNumber, 8111111, "Soalnge", "Rua da Alegria", "1000-245",
//				"Lisboa", 911111111, 888888888, "bernardo@polarising.com", "male",
//				"bernardoAdmin", entryDate, endContract, 2, "Blue Master 5", 100.0f, true, true, "18/01/1989", "CLIENT"));
//		
//		clientNumber = ("" + (Long.parseLong(clientNumber) + 1));
//		
//		clientRepository.save(new Client(clientNumber, 9111111, "Cecília", "Rua da Alegria", "1000-245",
//				"Lisboa", 911111111, 999999999, "bernardo@polarising.com", "male",
//				"bernardoAdmin", entryDate, endContract, 2, "Pacote 4 Serviços",  100.0f, false, true, "18/01/1989", "CLIENT"));
//		
//		clientNumber = ("" + (Long.parseLong(clientNumber) + 1));
//		
//		clientRepository.save(new Client(clientNumber, 0111111, "Nuno", "Rua da Alegria", "1000-245",
//				"Lisboa", 911111111, 101010111, "bernardo@polarising.com", "male",
//				"bernardoAdmin", entryDate, endContract, 2, "Pacote 4 Serviços",  100.0f, true, false, "18/01/1989", "CLIENT"));
//		
//		clientNumber = ("" + (Long.parseLong(clientNumber) + 1));
//		
//		clientRepository.save(new Client(clientNumber, 1211111, "César", "Rua da Alegria", "1000-245",
//				"Lisboa", 911111111, 121212121, "bernardo@polarising.com", "male",
//				"bernardoAdmin", entryDate, endContract, 2, "Blue Master 4", 20.0f, true, true, "18/01/1989", "CLIENT"));
//		
//		clientNumber = ("" + (Long.parseLong(clientNumber) + 1));
//		
//		clientRepository.save(new Client(clientNumber, 1311111, "Felipe", "Rua da Alegria", "1000-245",
//				"Lisboa", 911111111, 323232321, "bernardo@polarising.com", "male",
//				"bernardoAdmin", entryDate, endContract, 2, "Pacote 4 Serviços",  100.0f, true, false, "18/01/1989", "CLIENT"));
	
		//Encoding password
//		for (Client client : clientRepository.findAll())
//		{
//			client.setPassword(passwordEncoder.encode(client.getPassword()));
//			clientRepository.save(client);
//		}
	}
}
