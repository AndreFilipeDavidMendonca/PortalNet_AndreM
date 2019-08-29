package com.polarising.PortalNet.Utilities;

import java.util.Map;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.polarising.PortalNet.Security.UserPrincipal;
import com.polarising.PortalNet.Utilities.XMLParser.ParseBodyXML;
import com.polarising.PortalNet.model.AssociatedService;
import com.polarising.PortalNet.model.Client;
import com.polarising.PortalNet.model.Services;
import com.polarising.PortalNet.model.Workers;

import javassist.NotFoundException;

@Service
public class TibcoService {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(TibcoService.class);
	
	//PORTS
	@Value("${portalnet.tibco.admin.port}")
	private int getAdminPort;
	
	@Value("${portalnet.tibco.client.port}")
	private int getClientPort;
	
	@Value("${portalnet.tibco.general.port}")
	private int getGeneralPort;
	
	@Value("${portalnet.tibco.login.port}")
	private int getLoginPort;
	
	@Value("${portalnet.tibco.worker.port}")
	private int getWorkerPort;

	//STANDARD VARS
	@Value("${portalnet.tibco.standardVars}")
	private String[] getStandardVars;
	
	//LOGIN
	@Value("${portalnet.tibco.login.verification.soapAction}")
	private String loginSoapAction;
	
	@Value("${portalnet.tibco.login.verification.subPath}")
	private String loginSubPath;
	
	@Value("${portalnet.tibco.login.verification.specificVars}")
	private String[] getLoginVars;
	
	@Value("${portalnet.tibco.login.verification.soapRequestBody}")
	private String getLoginSoapRequestBody;

	//GET ALL SERVICES
	@Value("${portalnet.tibco.general.getAllServices.soapAction}")
	private String getAllServicesSoapAction;
	
	@Value("${portalnet.tibco.general.getAllServices.subPath}")
	private String getAllServicesSubPath;
	
	@Value("${portalnet.tibco.general.getAllServices.specificVars}")
	private String[] getAllServicesSpecificVars;
	
	@Value("${portalnet.tibco.general.getAllServices.soapRequestBody}")
	private String getAllServicesSoapRequestBody;
	
	//GET ALL CLIENTS
	@Value("${portalnet.tibco.worker.getAllClients.subPath}")
	private String getAllClientsSubPath;
	
	@Value("${portalnet.tibco.worker.getAllClients.soapAction}")
	private String getAllClientsSoapAction;
	
	@Value("${portalnet.tibco.worker.getAllClients.specificVars}")
	private String[] getAllClientsSpecificVars;
	
	@Value("${portalnet.tibco.worker.getAllClients.soapRequestBody}")
	private String getAllClientsSoapRequestBody;
	
	//GET ALL WORKERS
	@Value("${portalnet.tibco.admin.getAllWorkers.subPath}")
	private String getAllWorkersSubPath;
	
	@Value("${portalnet.tibco.admin.getAllWorkers.soapAction}")
	private String getAllWorkersSoapAction;
	
	@Value("${portalnet.tibco.admin.getAllWorkers.specificVars}")
	private String[] getAllWorkersSpecificVars;
	
	@Value("${portalnet.tibco.admin.getAllWorkers.soapRequestBody}")
	private String getAllWorkersSoapRequestBody;
	
	//GET ASSOCIATED SERVICES
	@Value("${portalnet.tibco.general.getAssociatedServices.subPath}")
	private String getAssociatedServicesSubPath;
	
	@Value("${portalnet.tibco.general.getAssociatedServices.soapAction}")
	private String getAssociatedServicesSoapAction;
	
	@Value("${portalnet.tibco.general.getAssociatedServices.specificVars}")
	private String[] getAssociatedServicesSpecificVars;
	
	@Value("${portalnet.tibco.general.getAssociatedServices.soapRequestBody}")
	private String getAssociatedServicesSoapRequestBody;
	
	//MODIFY CLIENT
	@Value("${portalnet.tibco.worker.modifyClient.subPath}")
	private String getModifyClientSubPath;
	
	@Value("${portalnet.tibco.worker.modifyClient.soapAction}")
	private String getModifyClientSoapAction;
	
	@Value("${portalnet.tibco.worker.modifyClient.soapRequestBody}")
	private String getModifyClientSoapRequestBody;
	
	//CLIENT REGISTRY
	@Value("${portalnet.tibco.general.clientRegist.subPath}")
	private String getClientRegistSubPath;
	
	@Value("${portalnet.tibco.general.clientRegist.soapAction}")
	private String getClientRegistSoapAction;
	
	@Value("${portalnet.tibco.general.clientRegist.soapRequestBody}")
	private String getClientRegistSoapRequestBody;
	
	//CREATE SERVICE
	@Value("${portalnet.tibco.admin.createService.subPath}")
	private String getCreateServiceSubPath;
	
	@Value("${portalnet.tibco.admin.createService.soapAction}")
	private String getCreateServiceSoapAction;
	
	@Value("${portalnet.tibco.admin.createService.soapRequestBody}")
	private String getCreateServiceSoapRequestBody;
	
	//MODIFY SERVICE
	@Value("${portalnet.tibco.admin.modifyService.subPath}")
	private String getModifyServiceSubPath;
	
	@Value("${portalnet.tibco.admin.modifyService.soapAction}")
	private String getModifyServiceSoapAction;
	
	@Value("${portalnet.tibco.admin.modifyService.soapRequestBody}")
	private String getModifyServiceSoapRequestBody;
	
	//CREATE WORKER
	@Value("${portalnet.tibco.admin.createWorker.subPath}")
	private String getCreateWorkerSubPath;
	
	@Value("${portalnet.tibco.admin.createWorker.soapAction}")
	private String getCreateWorkerSoapAction;
	
	@Value("${portalnet.tibco.admin.createWorker.soapRequestBody}")
	private String getCreateWorkerSoapRequestBody;
	
	//REMOVE WORKER
	@Value("${portalnet.tibco.admin.removeWorker.subPath}")
	private String getRemoveWorkerSubPath;
	
	@Value("${portalnet.tibco.admin.removeWorker.soapAction}")
	private String getRemoveWorkerSoapAction;
	
	@Value("${portalnet.tibco.admin.removeWorker.soapRequestBody}")
	private String getRemoveWorkerSoapRequestBody;
	
	//ASSOCIATE NEW SERVICE
	@Value("${portalnet.tibco.worker.associateNewService.subPath}")
	private String getAssociateNewServiceSubPath;
	
	@Value("${portalnet.tibco.worker.associateNewService.soapAction}")
	private String getAssociateNewServiceSoapAction;
	
	@Value("${portalnet.tibco.worker.associateNewService.soapRequestBody}")
	private String getAssociateNewServiceSoapRequestBody;
	
	//MODIFY ASSOCIATED SERVICE
	@Value("${portalnet.tibco.worker.modifyAssociatedService.subPath}")
	private String getModifyAssociatedServiceSubPath;
	
	@Value("${portalnet.tibco.worker.modifyAssociatedService.soapAction}")
	private String getModifyAssociatedServiceSoapAction;
	
	@Value("${portalnet.tibco.worker.modifyAssociatedService.soapRequestBody}")
	private String getModifyAssociatedServiceSoapRequestBody;
	
	//REMOVE ASSOCIATED SERVICE
	@Value("${portalnet.tibco.worker.removeAssociatedService.subPath}")
	private String getRemoveAssociatedServiceSubPath;
	
	@Value("${portalnet.tibco.worker.removeAssociatedService.soapAction}")
	private String getRemoveAssociatedServiceSoapAction;
	
	@Value("${portalnet.tibco.worker.removeAssociatedService.soapRequestBody}")
	private String getRemoveAssociatedServiceSoapRequestBody;
	
	
	@Autowired
	ParseBodyXML parseBodyXML;
	
	@Autowired
	PortalNetHttpRequest portalNetHttpRequest;
	
	@Autowired
	DateFormatHelper dateFormatHelper;
	
	@Autowired
	NumberGenerator clientNumberGenerator;
	
	
	/**
	 * Introduce an available TIBCO SOAP action for the PortalNet application. If wanting to obtain a list, then <b>idUser</b> should be <b>null</b>.
	 * <ul>
	 * <li> <i>getAllServices</i> <p>
	 * <li> <i>getAllClients</i> <p>
	 * <li> <i>getAllWorkers</i> <p>
	 * </ul>
	 * To obtain information about a specific user provide an <b>idUser</b> and pick one of the following actions:
	 * <ul>
	 * <li> <i>getAssociatedServices</i> <p>
	 * </ul>
	 * An id and role is needed to verify if the user is authorized to use this element.
	 * @param tibcoActionName
	 * @param idAuth
	 * @param roleAuth
	 * @param idUser
	 * @return a list of objects or information about a specific user.
	 */
	public List<Object> performTibcoListAction(String tibcoActionName, String idAuth, String roleAuth, String idUser)
	{
		String filledSoapRequestBody = null;
		String subPath = null;
		String soapAction = null;
		int port = 0;
		String[] specificVars = null;
		String objectName = null;
		
		switch(tibcoActionName)
		{
			case "getAllServices":
				filledSoapRequestBody = String.format(getAllServicesSoapRequestBody, idAuth, roleAuth);
				subPath = getAllServicesSubPath;
				soapAction = getAllServicesSoapAction;
				port = getGeneralPort;
				specificVars = getAllServicesSpecificVars;
				objectName = "Service";
				break;
				
			case "getAllClients":
				filledSoapRequestBody = String.format(getAllClientsSoapRequestBody, idAuth, roleAuth);
				subPath = getAllClientsSubPath;
				soapAction = getAllClientsSoapAction;
				port = getWorkerPort;
				specificVars = getAllClientsSpecificVars;
				objectName = "Client";
				break;
				
			case "getAllWorkers":
				filledSoapRequestBody = String.format(getAllWorkersSoapRequestBody, idAuth, roleAuth);
				subPath = getAllWorkersSubPath;
				soapAction = getAllWorkersSoapAction;
				port = getAdminPort;
				specificVars = getAllWorkersSpecificVars;
				objectName = "Worker";
				break;
				
			case "getAssociatedServices":
				filledSoapRequestBody = String.format(getAssociatedServicesSoapRequestBody, idAuth, roleAuth, idUser);
				subPath = getAssociatedServicesSubPath;
				soapAction = getAssociatedServicesSoapAction;
				port = getGeneralPort;
				specificVars = getAssociatedServicesSpecificVars;
				objectName = "AssociatedService";
				break;
		}
		
		return getListGeneric(idAuth, roleAuth, filledSoapRequestBody, subPath, soapAction, port, specificVars, objectName);
	}
	
	public void modifyClient(String idAuth, String roleAuth, Client client, int clientId)
	{		
		
		String filledSoapRequestBody = String.format(getModifyClientSoapRequestBody, idAuth, roleAuth, client.isStatus(), client.isFraudulent(), "false",
													client.getEntryDate(), client.getAddress(), client.getBirthDate(), client.getName(),
													clientId, client.getEmail(), client.getGender(), client.getCity(), client.getMobilePhone(),
													client.getNif(), client.getPhone(), client.getPostalCode());
		
		String response = portalNetHttpRequest.postToTibco(getModifyClientSubPath, filledSoapRequestBody, getModifyClientSoapAction, getWorkerPort);
		
		ArrayList<Map<String, String>> mapList = parseBodyXML.parseResponseXML(response, null);
		
		//Checking if the operation was successful
		tibcoSuccessCheck(mapList);
	}
	
	public void registClient(Client client)
	{
		String filledSoapRequestBody = String.format(getClientRegistSoapRequestBody, client.getClientId(), client.getRole(),
				client.getEntryDate(), client.getPassword(), getServiceIDFromServiceList(client.getServiceName()), client.isStatus(), client.isFraudulent(),
				"false", client.getEntryDate(), client.getAddress(), client.getBirthDate(), client.getName(), client.getClientId(),
				client.getEmail(), client.getGender(), client.getCity(), client.getMobilePhone(), client.getNif(),
				client.getPhone(), client.getPostalCode());
		
		String response = portalNetHttpRequest.postToTibco(getClientRegistSubPath, filledSoapRequestBody, getClientRegistSoapAction, getGeneralPort);
		ArrayList<Map<String, String>> mapList = parseBodyXML.parseResponseXML(response, null);
		
		//Checking if the operation was successful
		tibcoSuccessCheck(mapList);
	}
	
	public void createService(String idAuth, String roleAuth, Services service)
	{
		
		String filledSoapRequestBody = String.format(getCreateServiceSoapRequestBody, idAuth, roleAuth, service.getImgName(),
													service.isStatus(), service.getCreationDate(), service.getInternet(),
													service.getLoyalty(),  service.getMobilePhone(), service.getPhone(),
													service.getPrice(), service.getName(), service.getTv());
		
		String response = portalNetHttpRequest.postToTibco(getCreateServiceSubPath, filledSoapRequestBody, getCreateServiceSoapAction, getAdminPort);
		ArrayList<Map<String, String>> mapList = parseBodyXML.parseResponseXML(response, null);
		
		//Checking if the operation was successful
		tibcoSuccessCheck(mapList);
	}
	
	public void modifyService(String idAuth, String roleAuth, Services service)
	{
		String filledSoapRequestBody = String.format(getModifyServiceSoapRequestBody, idAuth, roleAuth, service.isStatus(), service.getServiceID());
		
		
		String response = portalNetHttpRequest.postToTibco(getModifyServiceSubPath, filledSoapRequestBody, getModifyServiceSoapAction, getAdminPort);
		ArrayList<Map<String, String>> mapList = parseBodyXML.parseResponseXML(response, null);
		
		//Checking if the operation was successful
		tibcoSuccessCheck(mapList);
	}
	
	public void createWorker(String idAuth, String roleAuth, Workers worker)
	{
		String filledSoapRequestBody = String.format(getCreateWorkerSoapRequestBody, idAuth, roleAuth, worker.getPassword(), worker.getEmail(), worker.getRole(), worker.getName(), worker.getEmployeeId());
		
		String response = portalNetHttpRequest.postToTibco(getCreateWorkerSubPath, filledSoapRequestBody, getCreateWorkerSoapAction, getAdminPort);
		ArrayList<Map<String, String>> mapList = parseBodyXML.parseResponseXML(response, null);
		
		//Checking if the operation was successful
		tibcoSuccessCheck(mapList);
	}
	
	public void removeWorker(String idAuth, String roleAuth, Integer employeeId)
	{
		String filledSoapRequestBody = String.format(getRemoveWorkerSoapRequestBody, idAuth, roleAuth, employeeId);
		
		String response = portalNetHttpRequest.postToTibco(getRemoveWorkerSubPath, filledSoapRequestBody, getRemoveWorkerSoapAction, getAdminPort);
		ArrayList<Map<String, String>> mapList = parseBodyXML.parseResponseXML(response, null);
		
		//Checking if the operation was successful
		tibcoSuccessCheck(mapList);
	}
	
	public void associateNewService(String idAuth, String roleAuth, AssociatedService newAssociatedService, Integer clientNumber)
	{
		String filledSoapRequestBody = String.format(getAssociateNewServiceSoapRequestBody, idAuth, roleAuth,
													newAssociatedService.getInstallationAddress(), clientNumber,
													newAssociatedService.getLocality(), newAssociatedService.getAlterationDate(),
													newAssociatedService.getPostalCode(), newAssociatedService.getServiceID());
		
		String response = portalNetHttpRequest.postToTibco(getAssociateNewServiceSubPath, filledSoapRequestBody, getAssociateNewServiceSoapAction, getWorkerPort);
		ArrayList<Map<String, String>> mapList = parseBodyXML.parseResponseXML(response, null);
		
		//Checking if the operation was successful
		tibcoSuccessCheck(mapList);
	}
	
	public void updateAssociatedService(String idAuth, String roleAuth, AssociatedService updatedAssociatedService)
	{
		String filledSoapRequestBody = String.format(getModifyAssociatedServiceSoapRequestBody, idAuth, roleAuth,
													updatedAssociatedService.getInstallationAddress(),
													updatedAssociatedService.getAssociatedServiceID(),
													updatedAssociatedService.getLocality(),
													updatedAssociatedService.getAlterationDate(),
													updatedAssociatedService.getPostalCode(),
													updatedAssociatedService.getServiceID());
		
		String response = portalNetHttpRequest.postToTibco(getModifyAssociatedServiceSubPath, filledSoapRequestBody, getModifyAssociatedServiceSoapAction, getWorkerPort);
		ArrayList<Map<String, String>> mapList = parseBodyXML.parseResponseXML(response, null);
		
		//Checking if the operation was successful
		tibcoSuccessCheck(mapList);
	}
	
	public void removeAssociatedService(String idAuth, String roleAuth, String associatedServiceID)
	{
		String filledSoapRequestBody = String.format(getRemoveAssociatedServiceSoapRequestBody, idAuth, roleAuth, associatedServiceID);
		
		String response = portalNetHttpRequest.postToTibco(getRemoveAssociatedServiceSubPath, filledSoapRequestBody, getRemoveAssociatedServiceSoapAction, getWorkerPort);
		ArrayList<Map<String, String>> mapList = parseBodyXML.parseResponseXML(response, null);
		
		//Checking if the operation was successful
		tibcoSuccessCheck(mapList);
	}
	
	public String[] login(String userEmail)
	{
		String filledSoapRequestBody = String.format(getLoginSoapRequestBody, userEmail);
		
		String response = portalNetHttpRequest.postToTibco(loginSubPath, filledSoapRequestBody, loginSoapAction, getLoginPort);
		ArrayList<Map<String, String>> mapList = parseBodyXML.parseResponseXML(response, getLoginVars);
		
		//Checking if the operation was successful
		tibcoSuccessCheck(mapList);
		
		String[] credentials;
		
		credentials = new String[] {mapList.get(1).get("id"), mapList.get(1).get("role"), mapList.get(1).get("password")};
		
		return credentials;
	}

	/**
	 * Method created to check if the id and role authentication made by TIBCO is successful.
	 * TIBCO always sends a message and error strings. If the message is equal to SUCCESS, then this
	 * test passes. Else, it throws an authentication exception.
	 * @param mapList
	 */
	public void tibcoSuccessCheck (ArrayList<Map<String, String>> mapList)
	{
		if (!mapList.get(0).get(getStandardVars[0]).equals("SUCCESS"))
		{
			throw new AuthenticationCredentialsNotFoundException(mapList.get(0).get(getStandardVars[0]) + "--> TIBCO action: " + mapList.get(0).get(getStandardVars[1]));
		}
	}
	
	/**
	 * This method receives an ArrayList of maps of strings and an empty Object list which is to be filled with the
	 * objects that are present in the mapList under the form of strings. An <b>objectName</b> must be provided so 
	 * the method knows which is the constructor to use. An <b>idAuth</b> and <b>roleAuth</b> is needed to confirm 
	 * the user asking for this information has authorization to do it. An <b>idUser</b> may be provided if the TIBCO
	 * SOAP action requires information about a specific user.
	 * @param mapList
	 * @param list
	 * @param objectName
	 * @param idAuth
	 * @param roleAuth
	 * @param idUser
	 */
	public void getFillObject(ArrayList<Map<String, String>> mapList, List<Object> list, String objectName, String idAuth, String roleAuth)
	{
		switch(objectName)
		{
			case "Service":
				for (Map<String, String> map : mapList) {
					String imgName = map.get("ImageURL").split("/")[map.get("ImageURL").split("/").length - 1];
					Services service = new Services(Long.parseLong(map.get("serviceID")), map.get("serviceName"), map.get("internet"), map.get("tv"), map.get("phone"), map.get("mobilePhone"),
													Integer.parseInt(map.get("loyalty")), Float.parseFloat(map.get("price")), map.get("creationDate"), Boolean.parseBoolean(map.get("active")), map.get("ImageURL"), imgName);
				
					list.add(service);
				}
				break;
				
			case "AssociatedService":
				@SuppressWarnings("unchecked") ArrayList<Services> servicesList = (ArrayList<Services>) transformList("Service", idAuth, roleAuth, null);
				
				for (Map<String, String> map : mapList) {
					AssociatedService associatedService = new AssociatedService(Integer.parseInt(map.get("associatedServiceID")), "serviceName empty", map.get("serviceID"), map.get("address"), 
							map.get("postalCode"), map.get("locality"), map.get("modificationDate"), "endDate empty", Integer.parseInt(map.get("workerNumber")), 0);
					
					for (int i = 0; i < servicesList.size(); i++)
					{
						if (associatedService.getServiceID().equals(servicesList.get(i).getServiceID().toString()))
						{
							associatedService.setServiceName(servicesList.get(i).getName());
							associatedService.setContractEndDate(dateFormatHelper.addYearToDate(associatedService.getAlterationDate(), (servicesList.get(i).getLoyalty() / 12)));
							associatedService.setMonthlyPay(servicesList.get(i).getPrice());
							continue;
						}
					}
					
					list.add(associatedService);
				}
				break;
				
			case "Client":
				for (Map<String, String> map : mapList) {
					Client client = new Client(Integer.parseInt(map.get("clientNumber")), Integer.parseInt(map.get("nif")), map.get("clientName"), map.get("address"),
																map.get("postalCode"), map.get("locality"), Long.parseLong(map.get("mobileNumber")), Long.parseLong(map.get("phoneNumber")),
																map.get("email"), map.get("gender"), map.get("accessionDate"), Boolean.parseBoolean(map.get("dishonest")), Boolean.parseBoolean(map.get("active")), map.get("birthDate"), "client");
				
					@SuppressWarnings("unchecked")
					ArrayList<AssociatedService> associatedServicesList = (ArrayList<AssociatedService>) transformList("AssociatedService", idAuth, roleAuth, client.getClientId());
						
					client.setServicesList(associatedServicesList);
					client.setEndContract(dateFormatHelper.getLatestDate(associatedServicesList));
					client.setNumberOfServices(associatedServicesList.size());
					client.setServiceName(getFiscalAddressServiceName(associatedServicesList, client));
					client.setMonthlyPay(getTotalMonthlyPay(associatedServicesList));
					
					list.add(client);
				}
				break;
				
			case "Worker":
				for (Map<String, String> map : mapList) {
					Workers worker = new Workers(Integer.parseInt(map.get("workerNumber")), map.get("workerName"), map.get("email"), map.get("role"));
					
					list.add(worker);
				}
				break;
		}
	}
	
	/**
	 * Method used to send a SOAP request to TIBCO, get the information, and transform it into an object.
	 * @param idAuth
	 * @param roleAuth
	 * @param idUser
	 * @param filledSoapRequestBody
	 * @param subPath
	 * @param soapAction
	 * @param port
	 * @param specificVars
	 * @param objectName
	 * @return
	 */
	public List<Object> getListGeneric(String idAuth, String roleAuth, String filledSoapRequestBody, String subPath, String soapAction, int port, String[] specificVars, String objectName)
	{
		List<Object> list = new ArrayList<Object>();
		String response = portalNetHttpRequest.postToTibco(subPath, filledSoapRequestBody, soapAction, port);
		ArrayList<Map<String, String>> mapList = parseBodyXML.parseResponseXML(response, specificVars);

		//Checking if the operation was successful
		tibcoSuccessCheck(mapList);
		mapList.remove(0);
		
		getFillObject(mapList, list, objectName, idAuth, roleAuth);
		
		return list;
	}
	
	public String getFiscalAddressServiceName (ArrayList<AssociatedService> associatedServicesList, Client client)
	{
		for (AssociatedService associatedService : associatedServicesList) {
			if (associatedService.getInstallationAddress().equals(client.getAddress())) {
				return associatedService.getServiceName();
			}
		}
		
		return null;
	}
	
	public float getTotalMonthlyPay (ArrayList<AssociatedService> associatedServicesList)
	{
		float totalMonthlyPay = 0;
		for (AssociatedService associatedService : associatedServicesList) {
			totalMonthlyPay += associatedService.getMonthlyPay();
		}
		
		return totalMonthlyPay;
	}
	
	public Client getClient (List<Object> clientList, int clientId) throws NotFoundException
	{
		Client client = null;
		ArrayList<Client> clientsList = new ArrayList<Client>();
		for (Object object : clientList) {
			clientsList.add((Client) object);
		}

		for (Client client1 : clientsList) {
			if ((int)client1.getClientId() == clientId)
			{
				client = client1;
			}
		}
			
		if (client == null)
		{
			throw new NotFoundException("O cliente com o id: " + clientId + " não foi encontrado.");
		}
		
		return client;
	}
	
	public ArrayList<?> transformList (String objectName, String idAuth, String roleAuth, Integer clientNumber)
	{
		
		switch (objectName) {
		case "Service":
			List<Object> allServicesList = performTibcoListAction("getAllServices", idAuth, roleAuth, null);
			ArrayList<Services> servicesList = new ArrayList<Services>();
			
			for(Object service : allServicesList)
			{
				servicesList.add((Services) service);
			}
			
			return servicesList;

		case "AssociatedService":
			List<Object> associatedServicesObjectList = performTibcoListAction("getAssociatedServices", idAuth, roleAuth, clientNumber.toString());
			ArrayList<AssociatedService> associatedServicesList = new ArrayList<AssociatedService>();
			for (Object associatedService : associatedServicesObjectList)
			{
				associatedServicesList.add((AssociatedService) associatedService);
			}
			return associatedServicesList;
			
		case "Client":
			List<Object> clientObjectList = performTibcoListAction("getAllClients", idAuth, roleAuth, null);
			ArrayList<Client> clientList = new ArrayList<Client>();
			for (Object object : clientObjectList) {
				clientList.add((Client) object);
			}
			return clientList;
			
		case "Worker":
			List<Object> workerObjectList = performTibcoListAction("getAllWorkers", idAuth, roleAuth, null);
			ArrayList<Workers> workersList = new ArrayList<Workers>();
			for (Object object : workerObjectList) {
				workersList.add((Workers) object);
			}
			return workersList;
		default:
			return null;
		}
	}
	
	public Services getServiceWithName(String serviceName)
	{
		@SuppressWarnings("unchecked")
		ArrayList<Services> servicesList = (ArrayList<Services>) transformList("Service", "", "", null);
		
		for (Services service : servicesList) {
			if (serviceName.equals(service.getName()))
			{
				return service;
			}
		}
		
		return null;
	}
	
	public Services getServiceWithId(String serviceId)
	{
		@SuppressWarnings("unchecked")
		ArrayList<Services> servicesList = (ArrayList<Services>) transformList("Service", "", "", null);
		
		for (Services service : servicesList) {
			if (serviceId.equals(service.getServiceID().toString()))
			{
				return service;
			}
		}
		
		return null;
	}
	
	public String getServiceIDFromServiceList(String serviceName)
	{
		
		return getServiceWithName(serviceName).getServiceID().toString();
	}
	
	/**
	 * This method can either receive a service name or ID to obtain full details on that service,
	 * inputting true or false as the boolean parameter, respectively.
	 * @param serviceNameOrID
	 * @param nameOrID
	 * @return
	 */
	public float getServicePrice(String serviceNameOrID, boolean nameOrID)
	{
		
		if(nameOrID){
			return getServiceWithName(serviceNameOrID).getPrice();
		}
		else
		{
			return getServiceWithId(serviceNameOrID).getPrice();
		}
	}
	
	public boolean compareServiceName (String serviceName, String idAuth, String roleAuth)
	{
		@SuppressWarnings("unchecked")
		List<Services> servicesList = (List<Services>) transformList("Service", idAuth, roleAuth, null);
		
		//Checking for services with the same name
		for (Services service : servicesList)
		{
			if (service.getName().equals(serviceName))
			{				
				return true;
			} 
		}
		
		return false;
	}
	
	public boolean compareServiceId (String serviceId, String idAuth, String roleAuth)
	{
		@SuppressWarnings("unchecked")
		List<Services> servicesList = (List<Services>) transformList("Service", idAuth, roleAuth, null);
		
		//Checking for services with the same name
		for (Services service : servicesList)
		{
			if (service.getServiceID().toString().equals(serviceId))
			{				
				return true;
			} 
		}
		
		return false;
	}
	
	public boolean compareWorkerId (Integer employeeId, String idAuth, String roleAuth)
	{
		@SuppressWarnings("unchecked")
		List<Workers> workersList = (List<Workers>) transformList("Worker", idAuth, roleAuth, null);
		
		//Checking for workers with the same Id
		for (Workers worker : workersList) {
			if (worker.getEmployeeId().equals(employeeId))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public String[] getSecurityCredentials()
	{
		UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String id = userPrincipal.getUsername();
		String role = userPrincipal.getRole();
		
		String[] credentials = {id, role};
		return credentials;
	}
}
