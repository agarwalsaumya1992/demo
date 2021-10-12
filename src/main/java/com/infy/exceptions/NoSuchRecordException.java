package com.infy.exceptions;
public class NoSuchRecordException extends Exception {
	private static final long serialVersionUID = 1L;
	public NoSuchRecordException() {
		super();
	}
	public NoSuchRecordException(String errors) {
		super(errors);
	}
}
