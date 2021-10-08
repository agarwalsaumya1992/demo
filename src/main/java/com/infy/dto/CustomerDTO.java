package com.infy.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CustomerDTO {
	@NotNull(message="{customer.phone.must}")
	long phoneNo;
	@NotBlank(message="{customer.name.must}")
	String name;
	@Email(message= "{customer.email.invalid}")
	String email;
	String address;
	public long getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public CustomerDTO(long phoneNo, String name, String email, String address) {
		this.phoneNo = phoneNo;
		this.name = name;
		this.email = email;
		this.address = address;
	}
	public CustomerDTO() {
	}
}
