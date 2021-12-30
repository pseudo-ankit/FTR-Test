package com.infosys.ftr.workitems.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ftr_vehicle_workitem")
public class WorkItemDriverVehicleEntity {
	
	@Id
	@Column(name="workitem_id")
	private String workItemId;
	
	@Column(name="vehicle_number")
	private String vehicleNumber;
	
	@Column(name="driver_id")
	private String driverId;
	
	@Column(name="assigned_workitem_status")
	private String assignedWorkitemStatus;

	/**
	 * @return the workItemId
	 */
	public String getWorkItemId() {
		return workItemId;
	}

	/**
	 * @param workItemId the workItemId to set
	 */
	public void setWorkItemId(String workItemId) {
		this.workItemId = workItemId;
	}

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
		return "WorkItemVehicleEntity [workItemId=" + workItemId + ", vehicleNumber=" + vehicleNumber + ", driverId="
				+ driverId + ", assignedWorkitemStatus=" + assignedWorkitemStatus + "]";
	}
}
