package com.infosys.ftr.terminal.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.infosys.ftr.terminal.controller.TerminalWorkItemFeign;
import com.infosys.ftr.terminal.dto.TerminalDTO;
import com.infosys.ftr.terminal.dto.WorkItemTerminalDTO;
import com.infosys.ftr.terminal.entity.Terminal;
import com.infosys.ftr.terminal.exception.ExceptionConstants;
import com.infosys.ftr.terminal.exception.FTRException;
import com.infosys.ftr.terminal.repository.TerminalRepository;
import com.infosys.ftr.terminal.utility.TerminalConstants;

@Service("terminalService")
@PropertySource("classpath:messages.properties")
public class TerminalServiceImpl implements TerminalService {

	@Autowired
	private TerminalRepository terminalRepository;

	@Autowired
	private Environment environment;
	
	@Autowired
	TerminalWorkItemFeign terminalWorkItemFeign;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public List<TerminalDTO> fetchFTRTerminals() throws FTRException {
		logger.info("fetchFTRTerminals");
		List<Terminal> terminals = terminalRepository.findAll();
		if (terminals!=null && !terminals.isEmpty()) {
			List<TerminalDTO> terminalDTOs = new ArrayList<>();
			for (Terminal terminal : terminals) {
				TerminalDTO terminalDTO = TerminalDTO.prepareDTO(terminal);
				terminalDTOs.add(terminalDTO);
			}
			return terminalDTOs;
		}
		throw new FTRException(environment.getProperty(ExceptionConstants.TERMINAL_EMPTY.toString()));
	}

	@Override
	public List<TerminalDTO> fetchTerminalsByItemType(String itemType) throws FTRException {
		logger.info("fetchTerminalsByItemType");
		List<Terminal> terminals = terminalRepository.findByItemType(itemType).orElse(null);
		if (terminals != null) {
			List<TerminalDTO> terminalDTOs = new ArrayList<>();
			for (Terminal terminal : terminals) {
				TerminalDTO terminalDTO = TerminalDTO.prepareDTO(terminal);
				terminalDTOs.add(terminalDTO);
			}
			return terminalDTOs;
		}
		throw new FTRException(environment.getProperty(ExceptionConstants.ITEMTYPE_NOT_FOUND.toString()));
	}

	@Override
	public TerminalDTO insertNewTerminal(TerminalDTO terminalDTO) {
		logger.info("insertNewTerminal");
		Terminal terminal = terminalRepository.save(TerminalDTO.prepareEntity(terminalDTO));
		return TerminalDTO.prepareDTO(terminal);
	}

	@Override
	public String updateTerminal(String terminalId, Integer newCapacity) throws FTRException {
		logger.info("updateTerminal");
		Terminal terminal = terminalRepository.findByTerminalId(terminalId).orElse(null);
		if (terminal != null) {
			TerminalDTO terminalDTO = TerminalDTO.prepareDTO(terminal);
			Integer reducedCapacity = terminalDTO.getAvailableCapacity() - newCapacity;
			if (reducedCapacity < 0) {
				throw new FTRException(environment.getProperty(ExceptionConstants.CAPACITY_EXCEEDED.toString()));
			}
			terminalDTO.setAvailableCapacity(reducedCapacity);
			if (reducedCapacity == 0) {
				terminalDTO.setStatus(TerminalConstants.STATUS_UNAVAILABLE.toString());
			} else {
				terminalDTO.setStatus(TerminalConstants.STATUS_AVAILABLE.toString());
			}
			terminalRepository.saveAndFlush(TerminalDTO.prepareEntity(terminalDTO));
			return (environment.getProperty("terminal.update.success") + " " + terminalId);
		}
		throw new FTRException(environment.getProperty(ExceptionConstants.TERMINAL_NOT_FOUND.toString()) + terminalId);
	}

	@Override
	public TerminalDTO fetchFTRTerminalByTerminalId(String terminalId) throws FTRException {
		logger.info("fetchFTRTerminalByTerminalId");
		Terminal terminal = terminalRepository.findByTerminalId(terminalId).orElse(null);
		if (terminal != null) {
			TerminalDTO terminalDTO = TerminalDTO.prepareDTO(terminal);
			return terminalDTO;
		}
		throw new FTRException(environment.getProperty(ExceptionConstants.TERMINAL_NOT_FOUND.toString()) + terminalId);
	}

	@Override
	public String removeTerminal(String terminalId) throws FTRException, Exception {
		logger.info("deleteTerminal");
		WorkItemTerminalDTO workItemTerminal = terminalWorkItemFeign.fetchWorkItemDetailsByTerminalId(terminalId);
		if(workItemTerminal.getTerminal()==null) {
			Terminal terminal = terminalRepository.findByTerminalId(terminalId).orElse(null);
			if (terminal != null) {
				terminalRepository.delete(terminal);
				return environment.getProperty("terminal.delete.success");
			}
			throw new FTRException(environment.getProperty(ExceptionConstants.TERMINAL_NOT_FOUND.toString()) + terminalId);
		}
		throw new FTRException(environment.getProperty(ExceptionConstants.TERMINAL_HAS_WORKITEMS.toString()));
	}
}