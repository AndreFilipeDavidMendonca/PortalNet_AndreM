package com.polarising.PortalNet.model;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class AssociatedServices {

    private int id;
    private long clientID;
    private String serviceID;
    private String installationAddress;
    private int postalCode;
    private Date alterationDate;
    private Date contractEndDate;
    private int workerNumber;
    
    public AssociatedServices() {}

    public AssociatedServices(int id, long clientID, String serviceID, String installationAddress, int postalCode, Date alterationDate, Date contractEndDate, int workerNumber) {
        this.id = id;
        this.clientID = clientID;
        this.serviceID = serviceID;
        this.installationAddress = installationAddress;
        this.postalCode = postalCode;
        this.alterationDate = alterationDate;
        this.contractEndDate = contractEndDate;
        this.workerNumber = workerNumber;
    }

    public int getId() {
        return id;
    }

    public long getClientID() {
        return clientID;
    }

    public String getServiceID() {
        return serviceID;
    }

    public String getInstallationAddress() {
        return installationAddress;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public Date getAlterationDate() {
        return alterationDate;
    }

    public Date getContractEndDate() {
        return contractEndDate;
    }

    public int getWorkerNumber() {
        return workerNumber;
    }
}

