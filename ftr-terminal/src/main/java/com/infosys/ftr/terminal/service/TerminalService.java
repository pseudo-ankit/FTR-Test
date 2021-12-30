package com.infosys.ftr.terminal.service;

import java.util.List;

import com.infosys.ftr.terminal.dto.TerminalDTO;
import com.infosys.ftr.terminal.exception.FTRException;

public interface TerminalService {

	List<TerminalDTO> fetchFTRTerminals() throws FTRException;

	List<TerminalDTO> fetchTerminalsByItemType(String itemType) throws FTRException;

	TerminalDTO insertNewTerminal(TerminalDTO terminalDTO);

	String updateTerminal(String terminalId, Integer newCapacity) throws FTRException;

	TerminalDTO fetchFTRTerminalByTerminalId(String terminalId) throws FTRException;

	String removeTerminal(String terminalId) throws FTRException, Exception;

}
