package com.polarising.PortalNet.Utilities;

import java.util.Calendar;

import org.springframework.stereotype.Component;

@Component
public class ClientNumberGenerator {

	public String generateClientNumber()
	{
		String today = Calendar.getInstance().getTime().toInstant().toString().substring(0, 10).replace("-", "");
		
		String random = String.format("%03d", (int) (Math.random() * 10000));
	
		return today + random;
	}
}
