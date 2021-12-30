package com.infosys.ftr.terminal.utility;

public enum TerminalConstants {
	STATUS_UNAVAILABLE("NotAvailable"),
	STATUS_AVAILABLE("Available");
	
	private final String type;
	
	private TerminalConstants(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return this.type;
	}

}
