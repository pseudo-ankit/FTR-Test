package com.infosys.ftr.workitems.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ftr_workitem_terminal")
public class WorkItemTerminalEntity {
	
	@Id
	@Column(name="workitem_id")
	private String workItemId;
	
	@Column(name="terminal_id")
	private String terminalId;

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
	 * @return the terminalId
	 */
	public String getTerminalId() {
		return terminalId;
	}

	/**
	 * @param terminalId the terminalId to set
	 */
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	@Override
	public String toString() {
		return "WorkItemTerminalEntity [workItemId=" + workItemId + ", terminalId=" + terminalId + "]";
	}
}
