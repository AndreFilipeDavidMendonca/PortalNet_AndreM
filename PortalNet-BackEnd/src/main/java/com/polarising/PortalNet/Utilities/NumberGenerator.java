package com.polarising.PortalNet.Utilities;

import org.springframework.stereotype.Component;


@Component
public class NumberGenerator {

	public String generateNumber()
	{		
		Long currentMillis = (System.currentTimeMillis() /1000);
		
		return currentMillis.toString();
	}
}
