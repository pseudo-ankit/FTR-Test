package com.infosys.ftr.vehicles.exception;

public enum ExceptionConstants {
	
	VEHICLE_NOT_FOUND("vehicle.notFound"),
	VEHICLE_UPDATE_ALREADY_EXISTS("vehicle.alreadyexists"),
	SERVER_ERROR("general.exception");
	
	private final String type;
	
	private ExceptionConstants(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return this.type;
	}

}
