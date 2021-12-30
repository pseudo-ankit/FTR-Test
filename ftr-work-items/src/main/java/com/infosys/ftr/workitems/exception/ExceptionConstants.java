package com.infosys.ftr.workitems.exception;

public enum ExceptionConstants {
	
	SERVER_ERROR("server.error.message");
	
	private final String type;
	
	private ExceptionConstants(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return this.type;
	}
}