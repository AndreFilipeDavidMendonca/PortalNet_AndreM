package com.polarising.PortalNet.Forms;

import org.springframework.stereotype.Component;

@Component
public class ClientForm {

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
	
	private String birthDate;
	
	private String serviceName;
	
	public ClientForm () {}

	public ClientForm(int nif, String name, String address, String postalCode, String city, long mobilePhone, long phone,
			String email, String gender, String password, String birthDate, String serviceName) {
		super();
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
		this.serviceName = serviceName;
		this.birthDate = birthDate;
	}

	public int getNif() {
		return nif;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public String getCity() {
		return city;
	}

	public long getMobilePhone() {
		return mobilePhone;
	}

	public long getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public String getGender() {
		return gender;
	}

	public String getPassword() {
		return password;
	}
	
	public String getServiceName() {
		return serviceName;
	}
	
	public String getBirthDate() {
		return birthDate;
	}
}
