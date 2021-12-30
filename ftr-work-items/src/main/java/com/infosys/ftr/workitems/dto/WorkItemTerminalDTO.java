package com.infosys.ftr.workitems.dto;

public class WorkItemTerminalDTO {
	
	private WorkItemDTO workItem;
	private TerminalDTO terminal;
	
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
	
	@Override
	public String toString() {
		return "WorkItemTerminalDTO [workItem=" + workItem + ", terminal=" + terminal + "]";
	}
	
}
