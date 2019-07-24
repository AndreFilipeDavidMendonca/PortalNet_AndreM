
package com.polarising.PortalNet.CommandLineRunner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.polarising.PortalNet.Repository.ClientRepository;
import com.polarising.PortalNet.model.Client;

@Component
public class ClientCommandLineRunner implements CommandLineRunner {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public void run(String... args) throws Exception {
		
		String today = Calendar.getInstance().getTime().toInstant().toString().substring(0, 10).replace("-", "");
		
		String random = String.format("%03d", (int) (Math.random() * 10000));
	
		String clientNumber = today + random;
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String entryDate = dateFormat.format(date).toString();
		
		@SuppressWarnings("unused")
		String endContract = "08/07/2020";
		
		clientRepository.save(new Client(clientNumber, 1111111, "Andre", "Rua da Alegria", "1000-245",
				"Lisboa", 911111111, 111111111, "andre@polarising.com", "male",
				"bernardoAdmin", entryDate, "27/06/2020", 2, "Blue Master 1",  50.0f, true, true, "18/01/1989", "CLIENT"));
		
		clientNumber = ("" + (Long.parseLong(clientNumber) + 1));
		
		clientRepository.save(new Client(clientNumber, 222222222, "Bernardo", "Rua da Alegria", "1000-245",
				"Lisboa", 911111111, 222222222, "bernardo@polarising.com", "male",
				"bernardoAdmin", entryDate, "27/06/2020", 2, "Pacote 4 Serviços",  60.0f, true, true, "25/11/1995", "CLIENT"));
		
		clientNumber = ("" + (Long.parseLong(clientNumber) + 1));
		
		clientRepository.save(new Client(clientNumber, 333333333, "Rui", "Rua da Alegria", "1000-245",
				"Lisboa", 911111111, 333333333, "rui@polarising.com", "male",
				"bernardoAdmin", entryDate, "27/06/2020", 2, "Blue Master 2",  45.0f, false, false, "18/01/1989", "CLIENT"));
		
		clientNumber = ("" + (Long.parseLong(clientNumber) + 1));
		
		clientRepository.save(new Client(clientNumber, 444444444, "Daniel", "Rua da Alegria", "1000-245",
				"Lisboa", 911111111, 444444444, "bernardo@polarising.com", "male",
				"bernardoAdmin", entryDate, "27/06/2020", 2, "Pacote 4 Serviços",  100.0f, false, true, "18/01/1989", "CLIENT"));
		
		clientNumber = ("" + (Long.parseLong(clientNumber) + 1));
		
		clientRepository.save(new Client(clientNumber, 555555555, "Eliana", "Rua da Alegria", "1000-245",
				"Lisboa", 911111111, 555555555, "bernardo@polarising.com", "male",
				"bernardoAdmin", entryDate, "27/06/2020", 2, "Blue Master 3", 50.0f, true, false, "18/01/1989", "CLIENT"));
		
		clientNumber = ("" + (Long.parseLong(clientNumber) + 1));
		
		clientRepository.save(new Client(clientNumber,666666666, "Perdiz", "Rua da Alegria", "1000-245",
				"Lisboa", 911111111, 666666666, "bernardo@polarising.com", "male",
				"bernardoAdmin", entryDate, "27/06/2020", 2, "Pacote 4 Serviços",  100.0f, true, true, "18/01/1989", "CLIENT"));
		
		clientNumber = ("" + (Long.parseLong(clientNumber) + 1));
		
		clientRepository.save(new Client(clientNumber, 7111111, "Renato", "Rua da Alegria", "1000-245",
				"Lisboa", 911111111, 777777777, "bernardo@polarising.com", "male",
				"bernardoAdmin", entryDate, "27/06/2020", 2, "Pacote 4 Serviços",  100.0f, true, true, "18/01/1989", "CLIENT"));
		
		clientNumber = ("" + (Long.parseLong(clientNumber) + 1));
		
		clientRepository.save(new Client(clientNumber, 8111111, "Soalnge", "Rua da Alegria", "1000-245",
				"Lisboa", 911111111, 888888888, "bernardo@polarising.com", "male",
				"bernardoAdmin", entryDate, "27/06/2020", 2, "Blue Master 5", 100.0f, true, true, "18/01/1989", "CLIENT"));
		
		clientNumber = ("" + (Long.parseLong(clientNumber) + 1));
		
		clientRepository.save(new Client(clientNumber, 9111111, "Cecília", "Rua da Alegria", "1000-245",
				"Lisboa", 911111111, 999999999, "bernardo@polarising.com", "male",
				"bernardoAdmin", entryDate, "27/06/2020", 2, "Pacote 4 Serviços",  100.0f, false, true, "18/01/1989", "CLIENT"));
		
		clientNumber = ("" + (Long.parseLong(clientNumber) + 1));
		
		clientRepository.save(new Client(clientNumber, 0111111, "Nuno", "Rua da Alegria", "1000-245",
				"Lisboa", 911111111, 101010111, "bernardo@polarising.com", "male",
				"bernardoAdmin", entryDate, "27/06/2020", 2, "Pacote 4 Serviços",  100.0f, true, false, "18/01/1989", "CLIENT"));
		
		clientNumber = ("" + (Long.parseLong(clientNumber) + 1));
		
		clientRepository.save(new Client(clientNumber, 1211111, "César", "Rua da Alegria", "1000-245",
				"Lisboa", 911111111, 121212121, "bernardo@polarising.com", "male",
				"bernardoAdmin", entryDate, "27/06/2020", 2, "Blue Master 4", 20.0f, true, true, "18/01/1989", "CLIENT"));
		
		clientNumber = ("" + (Long.parseLong(clientNumber) + 1));
		
		clientRepository.save(new Client(clientNumber, 1311111, "Felipe", "Rua da Alegria", "1000-245",
				"Lisboa", 911111111, 323232321, "bernardo@polarising.com", "male",
				"bernardoAdmin", entryDate, "27/06/2020", 2, "Pacote 4 Serviços",  100.0f, true, false, "18/01/1989", "CLIENT"));
	
		for (Client client : clientRepository.findAll())
		{
			client.setPassword(passwordEncoder.encode(client.getPassword()));
			clientRepository.save(client);
		}
	}
}
