package com.infosys.ftr.terminal.exception;

public class FTRException extends Exception {

	private static final long serialVersionUID = 1L;

	private String ftrExceptionMessage;

	public String getFtrExceptionMessage() {
		return ftrExceptionMessage;
	}

	public FTRException() {
		super();
	}

	public FTRException(String errors) {
		super(errors);

	}
}
