package com.pendownabook.web.controllers.dto;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.lang.NonNull;

import com.pendownabook.constraint.FieldMatch;

@FieldMatch.List({
	@FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match")
})
public class UserRegistrationDto {

	@NotEmpty(message = "Required")
	private String firstName;

	@NotEmpty(message = "Required")
	private String lastName;

	@NotEmpty(message = "Required")
//	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8, 20}$")
	private String password;

	@NotEmpty(message = "Required")
	private String confirmPassword;

	@Email
	@NotEmpty(message = "Required")
	private String email;	
	
	@NonNull
	@DecimalMin(value = "1000000000", message = "The contact should have 10 digits")
	@DecimalMax(value = "9999999999", message = "The contact should have 10 digits")
	private Long contact;

	@AssertTrue(message = "Please accept the terms and conditions")
	private Boolean terms;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Boolean getTerms() {
		return terms;
	}

	public void setTerms(Boolean terms) {
		this.terms = terms;
	}

	public Long getContact() {
		return contact;
	}

	public void setContact(Long contact) {
		this.contact = contact;
	}

}