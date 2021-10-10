package com.infy.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CustomerDTO {
	long id;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@NotNull(message="{customer.phone.must}")
	@Size(min = 10, max = 10, message ="{customer.phoneNo.invalid}")
	String phoneNo;
	@NotBlank(message="{customer.name.must}")
	String name;
	@Email(message= "{customer.email.invalid}")
	String email;
	String address;
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
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
	public CustomerDTO(long id,String phoneNo, String name, String email, String address) {
		this.id=id;
		this.phoneNo = phoneNo;
		this.name = name;
		this.email = email;
		this.address = address;
	}
	public CustomerDTO() {
	}
	@Override
	public String toString() {
		String obj="[id: "+this.id+", name: "+this.name+", phoneNo: "+this.phoneNo+", email: "+this.email+", address: "+this.address+"]";
		return obj;
	}
	
	
	
}
