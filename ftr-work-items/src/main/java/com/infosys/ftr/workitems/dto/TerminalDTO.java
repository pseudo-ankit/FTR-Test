package com.infosys.ftr.workitems.dto;

public class TerminalDTO {

	private String terminalId;
	private String terminalName;
	private String country;
	private String itemType;
	private String terminalDescription;
	private Integer capacity;
	private Integer availableCapacity;
	private String status;
	private String harborLocation;

	public TerminalDTO() {
	}

	public TerminalDTO(String terminalId, String terminalName, String country, String itemType,
			String terminalDescription, Integer capacity, Integer availableCapacity, String status,
			String harborLocation) {
		super();
		this.terminalId = terminalId;
		this.terminalName = terminalName;
		this.country = country;
		this.itemType = itemType;
		this.terminalDescription = terminalDescription;
		this.capacity = capacity;
		this.availableCapacity = availableCapacity;
		this.status = status;
		this.harborLocation = harborLocation;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getTerminalName() {
		return terminalName;
	}

	public void setTerminalName(String terminalName) {
		this.terminalName = terminalName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getTerminalDescription() {
		return terminalDescription;
	}

	public void setTerminalDescription(String terminalDescription) {
		this.terminalDescription = terminalDescription;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public Integer getAvailableCapacity() {
		return availableCapacity;
	}

	public void setAvailableCapacity(Integer availableCapacity) {
		this.availableCapacity = availableCapacity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getHarborLocation() {
		return harborLocation;
	}

	public void setHarborLocation(String harborLocation) {
		this.harborLocation = harborLocation;
	}

	@Override
	public String toString() {
		return "TerminalDTO [terminalId=" + terminalId + ", terminalName=" + terminalName + ", country=" + country
				+ ", itemType=" + itemType + ", terminalDescription=" + terminalDescription + ", capacity=" + capacity
				+ ", availableCapacity=" + availableCapacity + ", status=" + status + ", harborLocation="
				+ harborLocation + "]";
	}

}
