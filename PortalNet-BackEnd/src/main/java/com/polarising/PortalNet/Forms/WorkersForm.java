package com.polarising.PortalNet.Forms;

import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Component;

@Component
public class WorkersForm {
	
	@NotBlank
    private String name;
	
	@NotBlank
    private String email;
	
	@NotBlank
    private String role;
	
	@NotBlank
    private String password;
    
    public WorkersForm() {}

    public WorkersForm(String name, String email, String role, String password) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.password = password;
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

}
