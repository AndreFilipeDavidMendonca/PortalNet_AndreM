package com.PortalNet.PortalNet;

import java.util.Calendar;

import org.junit.Test;

public class ClientNumberAlgorithmTest {

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

}
