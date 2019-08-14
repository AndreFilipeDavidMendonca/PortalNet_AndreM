package com.PortalNet.PortalNet;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.polarising.PortalNet.PortalNetApplication;
import com.polarising.PortalNet.Utilities.NumberGenerator;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PortalNetApplication.class, webEnvironment = WebEnvironment.DEFINED_PORT)
public class ClientNumberAlgorithmTest {

	@Autowired
	NumberGenerator clientNumberGenerator;
	
	@Test
	public void test() {
		
		String today = Calendar.getInstance().getTime().toInstant().toString().substring(0, 10).replace("-", "");
		
		String random = String.format("%03d", (int) (Math.random() * 10000));
	
		String clientNumber = today + random;
		
		System.out.println(clientNumber);
		
		clientNumber = ("" + (Double.parseDouble(clientNumber) + 1)).substring(0, 13).replace(".", "");
		
		clientNumber = ("" + (Long.parseLong(clientNumber) + 1));
		
		System.out.println(clientNumber);
	}
	
	@Test
	public void clientNumberGeneratorTest() throws InterruptedException {
		
		System.out.println(clientNumberGenerator.generateNumber());
		Thread.sleep(1000);
		System.out.println(clientNumberGenerator.generateNumber());
		Thread.sleep(1000);
		System.out.println(clientNumberGenerator.generateNumber());
		Thread.sleep(1000);
		System.out.println(clientNumberGenerator.generateNumber());
	}

}
