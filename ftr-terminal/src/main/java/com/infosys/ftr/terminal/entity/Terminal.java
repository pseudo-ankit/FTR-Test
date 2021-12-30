package com.infosys.ftr.terminal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "ftr_terminals")
public class Terminal {

	@Id
	@GenericGenerator(strategy = "com.infosys.ftr.terminal.utility.TerminalIDGenerator", name = "terminalidgenerator")
	@GeneratedValue(generator = "terminalidgenerator")
	@Column(name = "terminal_id", columnDefinition = "varchar(20)")
	private String terminalId;

	@Column(name = "terminal_name", columnDefinition = "varchar(100)")
	private String terminalName;

	@Column(name = "country", columnDefinition = "varchar(30)")
	private String country;

	@Column(name = "item_type", columnDefinition = "varchar(100)")
	private String itemType;

	@Column(name = "terminal_description", columnDefinition = "varchar(25)")
	private String terminalDescription;

	private Integer capacity;

	@Column(name = "available_capacity")
	private Integer availableCapacity;

	@Column(name = "status", columnDefinition = "varchar(200)")
	private String status;

	@Column(name = "harborLocation", columnDefinition = "varchar(30)")
	private String harborLocation;

	public Terminal() {
	}

	public Terminal(String terminalId, String terminalName, String country, String itemType, String terminalDescription,
			Integer capacity, Integer availableCapacity, String status, String harborLocation) {
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
		return "Terminal [terminalId=" + terminalId + ", terminalName=" + terminalName + ", country=" + country
				+ ", itemType=" + itemType + ", terminalDescription=" + terminalDescription + ", capacity=" + capacity
				+ ", availableCapacity=" + availableCapacity + ", status=" + status + ", harborLocation="
				+ harborLocation + "]";
	}

}
