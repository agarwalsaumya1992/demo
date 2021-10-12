package com.infy.dto;


import javax.validation.constraints.NotBlank;


public class ProductDTO {
	

//	@Email(message= "{customer.email.invalid}")
//	@Size(min = 10, max = 10, message ="{customer.phoneNo.invalid}")
	
	private long id;
	private String category;
	@NotBlank(message="{name.must}")
	private String name;
	private double price;
	private int quantity;
    private String photo;
	
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public ProductDTO() {
	}
	@Override
	public String toString() {
		String obj="[id: "+this.id+", name: "+this.name+", category: "+this.category+", price: "+this.price+", quantity: "+this.quantity+", photo: "+this.photo+"]";
		return obj;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	
	
}
