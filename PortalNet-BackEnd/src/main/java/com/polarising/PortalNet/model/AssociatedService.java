package com.polarising.PortalNet.model;

import org.springframework.stereotype.Component;

@Component
public class AssociatedService {

    private int associatedServiceID;
    private String serviceName;
    private String serviceID;
    private String installationAddress;
    private String postalCode;
    private String locality;
    private String alterationDate;
    private String contractEndDate;
    private int workerNumber;
    private float monthlyPay;
    
    public AssociatedService() {}

    public AssociatedService(int associatedServiceID, String serviceName, String serviceID, String installationAddress, String postalCode,
			String locality, String alterationDate, String contractEndDate, int workerNumber, float monthlyPay) {
		super();
		this.associatedServiceID = associatedServiceID;
		this.serviceName = serviceName;
		this.serviceID = serviceID;
		this.installationAddress = installationAddress;
		this.postalCode = postalCode;
		this.locality = locality;
		this.alterationDate = alterationDate;
		this.contractEndDate = contractEndDate;
		this.workerNumber = workerNumber;
		this.monthlyPay = monthlyPay;
	}
    
	public AssociatedService(String serviceName, String serviceID, String installationAddress, String postalCode,
			String locality, String alterationDate, String contractEndDate, int workerNumber, float monthlyPay) {
		super();
		this.serviceName = serviceName;
		this.serviceID = serviceID;
		this.installationAddress = installationAddress;
		this.postalCode = postalCode;
		this.locality = locality;
		this.alterationDate = alterationDate;
		this.contractEndDate = contractEndDate;
		this.workerNumber = workerNumber;
		this.monthlyPay = monthlyPay;
	}
	
	

	public int getAssociatedServiceID() {
		return associatedServiceID;
	}

	public void setAssociatedServiceID(int associatedServiceID) {
		this.associatedServiceID = associatedServiceID;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceID() {
		return serviceID;
	}

	public void setServiceID(String serviceID) {
		this.serviceID = serviceID;
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

	public String getAlterationDate() {
		return alterationDate;
	}

	public void setAlterationDate(String alterationDate) {
		this.alterationDate = alterationDate;
	}

	public String getContractEndDate() {
		return contractEndDate;
	}

	public void setContractEndDate(String contractEndDate) {
		this.contractEndDate = contractEndDate;
	}

	public int getWorkerNumber() {
		return workerNumber;
	}

	public void setWorkerNumber(int workerNumber) {
		this.workerNumber = workerNumber;
	}

	public float getMonthlyPay() {
		return monthlyPay;
	}

	public void setMonthlyPay(float monthlyPay) {
		this.monthlyPay = monthlyPay;
	}

	@Override
	public String toString() {
		return "AssociatedService [id=" + associatedServiceID + ", serviceName=" + serviceName + ", serviceID=" + serviceID
				+ ", installationAddress=" + installationAddress + ", postalCode=" + postalCode + ", alterationDate="
				+ alterationDate + ", contractEndDate=" + contractEndDate + ", workerNumber=" + workerNumber
				+ ", monthlyPay=" + monthlyPay + "]";
	}
}

