package com.polarising.PortalNet.Forms;

import org.springframework.stereotype.Component;

@Component
public class AssociatedServiceForm {

	private String associatedServiceID;
	private String serviceID;
	private String clientNumber;
	private String installationAddress;
	private String postalCode;
	private String locality;
	
	public AssociatedServiceForm() {}
	
	public AssociatedServiceForm(String associatedServiceID, String serviceID, String clientNumber,
			String installationAddress, String postalCode, String locality) {
		super();
		this.associatedServiceID = associatedServiceID;
		this.serviceID = serviceID;
		this.clientNumber = clientNumber;
		this.installationAddress = installationAddress;
		this.postalCode = postalCode;
		this.locality = locality;
	}

	public AssociatedServiceForm(String serviceID, String clientNumber, String installationAddress, String postalCode,
			String locality) {
		super();
		this.serviceID = serviceID;
		this.clientNumber = clientNumber;
		this.installationAddress = installationAddress;
		this.postalCode = postalCode;
		this.locality = locality;
	}
	
	public String getAssociatedServiceID() {
		return associatedServiceID;
	}

	public void setAssociatedServiceID(String associatedServiceID) {
		this.associatedServiceID = associatedServiceID;
	}

	public String getServiceID() {
		return serviceID;
	}

	public void setServiceID(String serviceID) {
		this.serviceID = serviceID;
	}

	public String getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

	public String getInstallationAddress() {
		return installationAddress;
	}

	public void setInstallationAddress(String installationAddress) {
		this.installationAddress = installationAddress;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}
}
