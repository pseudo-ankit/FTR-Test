package com.infosys.ftr.workitems.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ftr_harbor")
public class HarborEntity {

	@Id
	private String country;
	
	@Column(name="available_harbor_locations")
	private String availableHarborLocations;

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

	@Override
	public String toString() {
		return "HarborEntity [country=" + country + ", availableHarborLocations=" + availableHarborLocations + "]";
	}
}
