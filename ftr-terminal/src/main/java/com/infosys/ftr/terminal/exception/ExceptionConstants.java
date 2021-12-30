package com.infosys.ftr.terminal.exception;

public enum ExceptionConstants {
	SERVER_ERROR("server.error"),
	ITEMTYPE_NOT_FOUND("terminal.itemtype.notFound"),
	TERMINAL_NOT_FOUND("terminal.notFound"),
	TERMINAL_HAS_WORKITEMS("terminal.hasWorkItems"),
	GENERAL_EXCEPTION("general.exception"),
	CAPACITY_EXCEEDED("terminal.capacity.failed"),
	TERMINAL_EMPTY("terminal.empty");
	
	private final String type;
	
	private ExceptionConstants(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return this.type;
	}
}