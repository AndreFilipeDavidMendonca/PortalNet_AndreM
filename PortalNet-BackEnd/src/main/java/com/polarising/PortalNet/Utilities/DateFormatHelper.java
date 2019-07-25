package com.polarising.PortalNet.Utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

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
}
