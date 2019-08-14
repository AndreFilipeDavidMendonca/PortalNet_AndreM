package com.polarising.PortalNet.Utilities.XMLParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ParseBodyXML {
	
	@Value("${portalnet.tibco.standardVars}")
	private String[] standardVars;
	
	public ArrayList<Map<String, String>> parseResponseXML( String response, String[] specificVars) {
		
		ArrayList<Map<String, String>> mapList = new ArrayList<Map<String,String>>();

		String[] aux1 = response.split("<SOAP-ENV:Body>");
		String[] aux2 = aux1[1].split("</SOAP-ENV:Body>");
			
		String[] lines = aux2[0].split("<");
		
		mapList.add( getVarsByRow(response, standardVars) );
		
		for (String line : lines) {
			if( getVarsByRow(line, specificVars) != null) {
				mapList.add( getVarsByRow(line, specificVars ) );
			}
		}
					
		return mapList;
	}
	
	private static Map<String, String> getVarsByRow(String line, String[] Vars) {
		
		boolean isEmpty = true;
		Map<String, String> map = new HashMap<String, String>();
		
		if (Vars == null)
		{
			return null;
		}

		for (String var : Vars) {
			if(line.contains( var + "=" )) {
				isEmpty = false;
				String[] aux1 = line.split( var + "=\"");
				String[] aux2 = aux1[1].split("\"");
				
				map.put(var, aux2[0]);
			}
		}	
		if(isEmpty) {
			return null;
		}
		return map;
	}
	
	public void displayResponse(ArrayList<Map<String, String>> mapList)
	{
		for (Map<String, String> map : mapList) {
			System.out.println(map);
		}
	}
}
