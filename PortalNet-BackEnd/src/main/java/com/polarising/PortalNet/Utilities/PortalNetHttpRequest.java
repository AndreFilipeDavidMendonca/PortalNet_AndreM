package com.polarising.PortalNet.Utilities;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PortalNetHttpRequest {
	
	private final String url = "http://localhost:8080";
	
	private final RestTemplate restTemplate = new RestTemplate();
	
	public String get(String subPath)
	{	
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<String> response = restTemplate.exchange(url + subPath,HttpMethod.GET,entity,String.class);
		
		return response.getBody();		
	}
	
	public String post(String subPath, Object body)
	{
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Object> entity = new HttpEntity<Object>(body, headers);
		ResponseEntity<String> response = restTemplate.exchange(url + subPath,HttpMethod.POST,entity,String.class);
		
		return response.getBody();		
	}
	
	public String postToTibco(String subPath, String body, String soapHeaderValue)
	{
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_XML);
		headers.add("SOAPAction", soapHeaderValue);
		HttpEntity<Object> entity = new HttpEntity<Object>(body, headers);
		ResponseEntity<String> response = restTemplate.exchange(url + subPath,HttpMethod.POST,entity,String.class);
		
		return response.getBody();
	}
	
	
}
