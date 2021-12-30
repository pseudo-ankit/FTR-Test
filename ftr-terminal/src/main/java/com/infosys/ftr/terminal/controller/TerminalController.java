package com.infosys.ftr.terminal.controller;

import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.ftr.terminal.dto.HarborDTO;
import com.infosys.ftr.terminal.dto.Message;
import com.infosys.ftr.terminal.dto.TerminalDTO;
import com.infosys.ftr.terminal.exception.FTRException;
import com.infosys.ftr.terminal.service.TerminalHystrixService;
import com.infosys.ftr.terminal.service.TerminalService;

@RestController
@CrossOrigin
@RequestMapping("/ftr/terminals")
public class TerminalController {

	@Autowired
	TerminalService terminalService;
	
	// TODO Uncomment below and test
	@Autowired
	TerminalHystrixService terminalHystrixService;

	Logger logger = LoggerFactory.getLogger(getClass());

	@GetMapping
	ResponseEntity<List<TerminalDTO>> fetchFTRTerminals() throws FTRException {
		logger.info("fetchFTRTerminals");
		List<TerminalDTO> response = terminalService.fetchFTRTerminals();
		return ResponseEntity.ok(response);
	}

	@GetMapping("/fetchTerminalByTerminalId/{terminalId}")
	ResponseEntity<TerminalDTO> fetchTerminalByTerminalId(@PathVariable("terminalId") String terminalId)
			throws FTRException {
		logger.info("fetchTerminalByTerminalId");
		TerminalDTO response = terminalService.fetchFTRTerminalByTerminalId(terminalId);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/fetchTerminalByItemType/{itemType}")
	ResponseEntity<List<TerminalDTO>> fetchTerminalsByItemType(@PathVariable("itemType") String itemType)
			throws FTRException {
		logger.info("fetchTerminalsByItemType");
		List<TerminalDTO> response = terminalService.fetchTerminalsByItemType(itemType);
		return ResponseEntity.ok(response);
	}

	@PostMapping(produces = "application/json", consumes = "application/json")
	ResponseEntity<TerminalDTO> insertNewTerminal(@Valid @RequestBody TerminalDTO tDTO, Errors error) throws Exception {
		logger.info("insertNewTerminal");
		if (error.hasErrors()) {
			String response = error.getAllErrors().stream().map(ObjectError::getDefaultMessage)
					.collect(Collectors.joining(","));
			throw new Exception(response);
		} else {
			TerminalDTO response = terminalService.insertNewTerminal(tDTO);
			// CALL TO WORKITEMS MS
			// TODO Uncomment below and test
			HarborDTO harborDTO = new HarborDTO();
			harborDTO.setAvailableHarborLocations(response.getHarborLocation());
			harborDTO.setCountry(response.getCountry());
			harborDTO.setOpCode("ADD");
			 terminalHystrixService.manageHarbor(harborDTO);
			return ResponseEntity.ok(response);
		}
	}

	@PutMapping(value = "/{terminalId}/{newCapacity}", produces = "application/json")
	ResponseEntity<Message> updateTerminal(@PathVariable("terminalId") String terminalId,
			@PathVariable("newCapacity") Integer newCapacity) throws FTRException {
		logger.info("updateTerminal");
		String response = terminalService.updateTerminal(terminalId, newCapacity);

		//CALL TO WORKITEMS MS
		// TODO Uncomment below and test
		TerminalDTO tDTO = terminalService.fetchFTRTerminalByTerminalId(terminalId);
		if (tDTO.getAvailableCapacity() == 0) {
			HarborDTO harborDTO = new HarborDTO();
			harborDTO.setAvailableHarborLocations(tDTO.getHarborLocation());
			harborDTO.setCountry(tDTO.getCountry());
			harborDTO.setOpCode("DEL");
			 terminalHystrixService.manageHarbor(harborDTO);
		}
		return ResponseEntity.ok(new Message(response));
	}

	@DeleteMapping("/{terminalId}")
	ResponseEntity<Message> removeTerminal(@PathVariable("terminalId") String terminalId) throws Exception {
		logger.info("removeTerminal");
		// CALL TO WORKITEMS
		// TODO Uncomment below and test		
		TerminalDTO tDTO = terminalService.fetchFTRTerminalByTerminalId(terminalId);
		HarborDTO harborDTO = new HarborDTO();
		harborDTO.setAvailableHarborLocations(tDTO.getHarborLocation());
		harborDTO.setCountry(tDTO.getCountry());
		harborDTO.setOpCode("DEL");
		terminalHystrixService.manageHarbor(harborDTO);
		String response = terminalService.removeTerminal(terminalId);
		return ResponseEntity.ok(new Message(response));
	}

}
