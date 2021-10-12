package com.infy.util;
/**
 * The Enum ExceptionConstants.
 */
public enum AppConstants {
	
	
	//Exception message constants
	RECORD_NOT_FOUND("record.not.found"), 
	GENERAL_EXCEPTION("general.exception"),
	
	//Success message constants
	
	UPDATE_SUCCESS("record.update.success"),
	DELETE_SUCCESS("record.delete.success"),
	CREATE_SUCCESS("record.create.success");
	
	private final String type;
	private AppConstants(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return this.type;
	}
}
