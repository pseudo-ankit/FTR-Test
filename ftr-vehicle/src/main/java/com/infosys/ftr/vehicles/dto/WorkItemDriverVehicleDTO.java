package com.infosys.ftr.vehicles.dto;

public class WorkItemDriverVehicleDTO {
	
	private WorkItemDTO workItem;
	private VehicleDTO vehicle;
	private String driverId;
	private String assignedWorkitemStatus;
	
	/**
	 * @return the workItem
	 */
	public WorkItemDTO getWorkItem() {
		return workItem;
	}

	/**
	 * @param workItem the workItem to set
	 */
	public void setWorkItem(WorkItemDTO workItem) {
		this.workItem = workItem;
	}

	/**
	 * @return the vehicle
	 */
	public VehicleDTO getVehicle() {
		return vehicle;
	}

	/**
	 * @param vehicle the vehicle to set
	 */
	public void setVehicle(VehicleDTO vehicle) {
		this.vehicle = vehicle;
	}

	/**
	 * @return the driverId
	 */
	public String getDriverId() {
		return driverId;
	}

	/**
	 * @param driverId the driverId to set
	 */
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}

	/**
	 * @return the assignedWorkitemStatus
	 */
	public String getAssignedWorkitemStatus() {
		return assignedWorkitemStatus;
	}

	/**
	 * @param assignedWorkitemStatus the assignedWorkitemStatus to set
	 */
		
	public void setAssignedWorkitemStatus(String assignedWorkitemStatus) {
		this.assignedWorkitemStatus = assignedWorkitemStatus;
	}
	
	@Override
	public String toString() {
		return "WorkItemDriverVehicleDTO [workItem=" + workItem + ", vehicle=" + vehicle + ", driverId=" + driverId
				+ ", assignedWorkitemStatus=" + assignedWorkitemStatus + "]";
	}

}
