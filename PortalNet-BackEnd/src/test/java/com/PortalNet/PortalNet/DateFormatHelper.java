package com.PortalNet.PortalNet;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.h2.api.DatabaseEventListener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import com.polarising.PortalNet.PortalNetApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PortalNetApplication.class, webEnvironment = WebEnvironment.DEFINED_PORT)
public class DateFormatHelper {

	@Autowired
	com.polarising.PortalNet.Utilities.DateFormatHelper dateFormatHelper;
	
	public ArrayList<String> dateList = new ArrayList<String>(Arrays.asList("03/08/2040", "02/08/2040", "01/01/2040"));
	
	@Test
	public void test() {
//		System.out.println(dateFormatHelper.getLatestDate(dateList));
	}

}
