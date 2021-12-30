package com.infosys.ftr.workitems.dto;

public class WorkItemAssociationDTO {
	private VehicleDTO vehicle;
	private TerminalDTO terminal;
	private String assignedWorkitemStatus;
	
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
	 * @return the terminal
	 */
	public TerminalDTO getTerminal() {
		return terminal;
	}
	/**
	 * @param terminal the terminal to set
	 */
	public void setTerminal(TerminalDTO terminal) {
		this.terminal = terminal;
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
}
