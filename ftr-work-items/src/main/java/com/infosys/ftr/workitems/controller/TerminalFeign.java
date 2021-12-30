package com.infosys.ftr.workitems.controller;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.infosys.ftr.workitems.dto.Message;
import com.infosys.ftr.workitems.dto.TerminalDTO;
import com.infosys.ftr.workitems.exception.FTRException;

@FeignClient("TerminalMS")
public interface TerminalFeign {
	
	@GetMapping("/ftr/terminals/fetchTerminalByItemType/{itemType}")
	List<TerminalDTO> fetchTerminalsByItemType(@PathVariable("itemType") String itemType);
	
	@PutMapping(value = "/ftr/terminals/{terminalId}/{newCapacity}")
	Message updateTerminal(@PathVariable("terminalId") String terminalId, @PathVariable("newCapacity") Integer newCapacity) throws FTRException;
	
	@GetMapping("/ftr/terminals/fetchTerminalByTerminalId/{terminalId}")
	TerminalDTO fetchTerminalByTerminalId(@PathVariable("terminalId") String terminalId);

}
