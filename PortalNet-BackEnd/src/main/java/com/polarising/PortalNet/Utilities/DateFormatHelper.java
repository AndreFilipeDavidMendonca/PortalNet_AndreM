package com.polarising.PortalNet.Utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.polarising.PortalNet.model.AssociatedService;

@Component
public class DateFormatHelper {

	private static final Logger logger = LoggerFactory.getLogger(DateFormatHelper.class);
	
	public String dateFormater()
	{
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
		return dateFormat.format(date).toString();
	}
	
	@SuppressWarnings("deprecation")
	public String addYearToDate(String dateToBeAdded, int year)
	{
		try{
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date dateToBeReturned = dateFormat.parse(dateToBeAdded);	
			int dateYear  = dateToBeReturned.getYear();
			dateToBeReturned.setYear(dateYear + year);
		
			return dateFormat.format(dateToBeReturned).toString();
		}
		catch(ParseException e)
		{
			logger.error("Error parsing date --> ", e);
		}
		return null;
	}
	
	public Date stringToDateFormatter(String dateString)
	{
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			return dateFormat.parse(dateString);
		} catch (ParseException e) {
			System.out.println("Error parsing String to Date --> " + e.getMessage());
			return null;
		}
	}
	
	public String getLatestDate (ArrayList<AssociatedService> associatedServicesList)
	{
		ArrayList<String> datesList = new ArrayList<String>();
		
		for (AssociatedService associatedService : associatedServicesList) {
			datesList.add(associatedService.getContractEndDate());
		}
		
		String latestDate = datesList.get(0);
		
		for (int i = 0; i < datesList.size(); i++)
		{
			if (stringToDateFormatter(latestDate).compareTo(stringToDateFormatter(datesList.get(i))) < 0)
			{
				latestDate = datesList.get(i);
			}
		}
		
		return latestDate;
	}
}
