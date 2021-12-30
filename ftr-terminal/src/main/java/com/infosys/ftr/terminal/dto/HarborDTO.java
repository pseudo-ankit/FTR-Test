package com.infosys.ftr.terminal.dto;

public class HarborDTO {

	private String country;
	private String availableHarborLocations;
	/*
	 * if new terminal is added, opCode="ADD" if existing terminal is removed,
	 * opCode="DEL" if existing terminal is updated where available_capacity becomes
	 * 0, opCode="DEL"
	 */
	private String opCode;

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the availableHarborLocations
	 */
	public String getAvailableHarborLocations() {
		return availableHarborLocations;
	}

	/**
	 * @param availableHarborLocations the availableHarborLocations to set
	 */
	public void setAvailableHarborLocations(String availableHarborLocations) {
		this.availableHarborLocations = availableHarborLocations;
	}

	/**
	 * @return the opCode
	 */
	public String getOpCode() {
		return opCode;
	}

	/**
	 * @param opCode the opCode to set
	 */
	public void setOpCode(String opCode) {
		this.opCode = opCode;
	}
}