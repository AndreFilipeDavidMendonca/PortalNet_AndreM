package com.polarising.PortalNet.Utilities;

import java.nio.charset.Charset;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PortalNetHttpRequest {
	
	private final String url = "http://192.168.0.136:";
	
	private final RestTemplate restTemplate = getUf8Template();
	
	public String postToTibco(String subPath, String body, String soapAction, int port)
	{
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_XML);
		headers.add("SOAPAction", soapAction);
		HttpEntity<String> entity = new HttpEntity<String>(body, headers);
		ResponseEntity<String> response = restTemplate.exchange(url + port + subPath,HttpMethod.POST,entity,String.class);
		
		return response.getBody();
	}
	
	private RestTemplate getUf8Template() {
		RestTemplate template = new RestTemplate();
		template.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
		
		return template;
	}
}
