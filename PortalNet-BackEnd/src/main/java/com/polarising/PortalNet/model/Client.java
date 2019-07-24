package com.polarising.PortalNet.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Entity
@Component
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer clientId;
	private String clientNumber;
    private int nif;
    private String name;
    private String address;
    private String postalCode;
    private String city;
    private long mobilePhone;
    private long phone;
    private String email;
    private String gender;
    private String password;
    private String entryDate;
    private String endContract;
    private int numberOfServices;
    private String serviceName;
    private float monthlyPay;
    private boolean fraudulent;
    private boolean status;
    private String birthDate;
    private String role;

    
    public Client() {}
    
    public Client(String clientNumber, int nif, String name, String address, String postalCode,
			String city, long mobilePhone, long phone, String email, String gender, String password, String entryDate,
			String endContract, int numberOfServices, String serviceName, float monthlyPay, boolean fraudulent,
			boolean status, String birthDate, String role) {
		super();
		this.clientNumber = clientNumber;
		this.nif = nif;
		this.name = name;
		this.address = address;
		this.postalCode = postalCode;
		this.city = city;
		this.mobilePhone = mobilePhone;
		this.phone = phone;
		this.email = email;
		this.gender = gender;
		this.password = password;
		this.entryDate = entryDate;
		this.endContract = endContract;
		this.numberOfServices = numberOfServices;
		this.serviceName = serviceName;
		this.monthlyPay = monthlyPay;
		this.fraudulent = fraudulent;
		this.status = status;
		this.birthDate = birthDate;
		this.role = role;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public String getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

	public int getNif() {
		return nif;
	}

	public void setNif(int nif) {
		this.nif = nif;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public long getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(long mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	public String getEndContract() {
		return endContract;
	}

	public void setEndContract(String endContract) {
		this.endContract = endContract;
	}

	public int getNumberOfServices() {
		return numberOfServices;
	}

	public void setNumberOfServices(int numberOfServices) {
		this.numberOfServices = numberOfServices;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public float getMonthlyPay() {
		return monthlyPay;
	}

	public void setMonthlyPay(float monthlyPay) {
		this.monthlyPay = monthlyPay;
	}

	public boolean isFraudulent() {
		return fraudulent;
	}

	public void setFraudulent(boolean fraudulent) {
		this.fraudulent = fraudulent;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Client [clientId=" + clientId + ", clientNumber=" + clientNumber + ", nif=" + nif + ", name=" + name
				+ ", address=" + address + ", postalCode=" + postalCode + ", city=" + city + ", mobilePhone="
				+ mobilePhone + ", phone=" + phone + ", email=" + email + ", gender=" + gender + ", password="
				+ password + ", entryDate=" + entryDate + ", endContract=" + endContract + ", numberOfServices="
				+ numberOfServices + ", serviceName=" + serviceName + ", monthlyPay=" + monthlyPay + ", fraudulent="
				+ fraudulent + ", status=" + status + ", birthDate=" + birthDate + ", role=" + role + "]";
	}	
}

