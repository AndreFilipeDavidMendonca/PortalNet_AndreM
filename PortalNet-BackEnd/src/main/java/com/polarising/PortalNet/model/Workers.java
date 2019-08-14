package com.polarising.PortalNet.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Entity
@Component
public class Workers {

	@Id
    private Integer employeeId;
    private String name;
    private String email;
    private String role;
    private String password;
    
    public Workers() {}

    public Workers(Integer employeeId, String name, String email, String role, String password) {
    	this.employeeId = employeeId;
        this.name = name;
        this.email = email;
        this.role = role;
        this.password = password;
    }

	public Workers(Integer employeeId, String name, String email, String role) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.email = email;
		this.role = role;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}
	
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}


	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getRole() {
		return role;
	}

	public String getPassword() {
		return password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Workers [employeeId=" + employeeId + ", name=" + name + ", email=" + email + ", role=" + role
				+ ", password=" + password + "]";
	}
}

