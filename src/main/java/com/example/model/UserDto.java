package com.example.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
@Entity
@Table(name="user")
public class UserDto {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	
	private int id;
	@NotBlank(message = "UserName is required.")    
	private String username;
    @Size(min = 8, message 
    	      = "About Me must be  8  characters")
    private String password;
    @NotBlank(message = "Email is required.") 
    @Email(message = "Email should be valid")
    @Column(unique = true)
    private String email;
    @Pattern(regexp = "^\\d{10}$",message = "invalid mobile number entered ")
    @Column(unique = true)
    private String mobilenumber;
    
    private String role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobilenumber() {
		return mobilenumber;
	}

	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDto)) return false;
        UserDto user = (UserDto) o;
        return Objects.equals(email, user.email) &&
                Objects.equals(password, user.password);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, email,mobilenumber
                            );
    }


}
