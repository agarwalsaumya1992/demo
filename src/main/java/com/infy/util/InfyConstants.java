package com.infy.util;
/**
 * The Enum ExceptionConstants.
 */
public enum InfyConstants {
	
	
	//Exception message constants
	CUSTOMER_NOT_FOUND("customer.not.found"), 
	GENERAL_EXCEPTION_MESSAGE("general.exception"),
	
	//Success message constants
	
	CUSTOMER_UPDATE_SUCCESS("customer.update.success"),
	CUSTOMER_DELETE_SUCCESS("customer.delete.success"),
	CUSTOMER_CREATE_SUCCESS("customer.create.success");
	
	private final String type;
	private InfyConstants(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return this.type;
	}
}
