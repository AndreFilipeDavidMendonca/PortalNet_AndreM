package com.PortalNet.TestTibcoService;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.polarising.PortalNet.PortalNetApplication;
import com.polarising.PortalNet.Utilities.TibcoService;
import com.polarising.PortalNet.model.Services;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PortalNetApplication.class, webEnvironment = WebEnvironment.DEFINED_PORT)
public class GeneralSoapActionsTest {
	
	
	@Autowired
	TibcoService tibcoService;
	
	@Test
	public void testListAllClients() {
		
		List<Object> allServicesList = tibcoService.performTibcoListAction("getAllServices", "20190001", "administrator", null);
		
		for (Object services : allServicesList) {
			System.out.println(services.toString());
		}
	}
	
	@Test
	public void testListAssociatedServices()
	{
		List<Object> associatedServicesList = tibcoService.performTibcoListAction("getAssociatedServices", "2019000001", "administrator", "2019000001");
		
		for (Object associatedServices : associatedServicesList) {
			System.out.println(associatedServices.toString());
		}
	}
	
	@Test
	public void testListAllWorkers()
	{
		List<Object> allWorkersList = tibcoService.performTibcoListAction("getAllWorkers", "2019000001", "administrator", null);
		
		for (Object object : allWorkersList) {
			System.out.println(object.toString());
		}
	}
}
