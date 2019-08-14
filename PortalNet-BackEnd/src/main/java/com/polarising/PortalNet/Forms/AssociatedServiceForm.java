package com.polarising.PortalNet.Forms;

import org.springframework.stereotype.Component;

@Component
public class AssociatedServiceForm {

	private String associatedServiceID;
	private String serviceID;
	private String clientId;
	private String installationAddress;
	private String postalCode;
	private String locality;
	
	public AssociatedServiceForm() {}
	
	public AssociatedServiceForm(String associatedServiceID, String serviceID, String clientId,
			String installationAddress, String postalCode, String locality) {
		super();
		this.associatedServiceID = associatedServiceID;
		this.serviceID = serviceID;
		this.clientId = clientId;
		this.installationAddress = installationAddress;
		this.postalCode = postalCode;
		this.locality = locality;
	}

	public AssociatedServiceForm(String serviceID, String clientId, String installationAddress, String postalCode,
			String locality) {
		super();
		this.serviceID = serviceID;
		this.clientId = clientId;
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

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
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

	@Override
	public String toString() {
		return "AssociatedServiceForm [associatedServiceID=" + associatedServiceID + ", serviceID=" + serviceID
				+ ", clientId=" + clientId + ", installationAddress=" + installationAddress + ", postalCode="
				+ postalCode + ", locality=" + locality + "]";
	}
	
}
