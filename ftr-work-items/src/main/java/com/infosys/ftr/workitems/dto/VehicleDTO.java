package com.infosys.ftr.workitems.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class VehicleDTO {

	private String vehicleNumber;
	private String vehicleName;
	private Double maxLiftingCapacity;
	@NotNull(message = "{vehicle.retireDate.must}")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MMM-yyyy")
	private LocalDate retireDate;
	private String vehicleStatus;
	private String harborLocation;
	private String country;

	/**
	 * @return the vehicleNumber
	 */
	public String getVehicleNumber() {
		return vehicleNumber;
	}

	/**
	 * @param vehicleNumber the vehicleNumber to set
	 */
	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	/**
	 * @return the vehicleName
	 */
	public String getVehicleName() {
		return vehicleName;
	}

	/**
	 * @param vehicleName the vehicleName to set
	 */
	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}

	/**
	 * @return the maxLiftingCapacity
	 */
	public Double getMaxLiftingCapacity() {
		return maxLiftingCapacity;
	}

	/**
	 * @param maxLiftingCapacity the maxLiftingCapacity to set
	 */
	public void setMaxLiftingCapacity(Double maxLiftingCapacity) {
		this.maxLiftingCapacity = maxLiftingCapacity;
	}

	/**
	 * @return the retireDate
	 */
	public LocalDate getRetireDate() {
		return retireDate;
	}

	/**
	 * @param retireDate the retireDate to set
	 */
	public void setRetireDate(LocalDate retireDate) {
		this.retireDate = retireDate;
	}

	/**
	 * @return the vehicleStatus
	 */
	public String getVehicleStatus() {
		return vehicleStatus;
	}

	/**
	 * @param vehicleStatus the vehicleStatus to set
	 */
	public void setVehicleStatus(String vehicleStatus) {
		this.vehicleStatus = vehicleStatus;
	}

	/**
	 * @return the harborLocation
	 */
	public String getHarborLocation() {
		return harborLocation;
	}

	/**
	 * @param harborLocation the harborLocation to set
	 */
	public void setHarborLocation(String harborLocation) {
		this.harborLocation = harborLocation;
	}

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

	@Override
	public String toString() {
		return "VehicleDTO [vehicleNumber=" + vehicleNumber + ", vehicleName=" + vehicleName + ", maxLiftingCapacity="
				+ maxLiftingCapacity + ", retireDate=" + retireDate + ", vehicleStatus=" + vehicleStatus
				+ ", harborLocation=" + harborLocation + ", country=" + country + "]";
	}
}
