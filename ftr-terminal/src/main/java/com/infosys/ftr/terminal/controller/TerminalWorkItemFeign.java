package com.infosys.ftr.terminal.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.infosys.ftr.terminal.dto.HarborDTO;
import com.infosys.ftr.terminal.dto.WorkItemTerminalDTO;

@FeignClient("WORKITEMS")
public interface TerminalWorkItemFeign {
	@RequestMapping(value="/ftr/workItems/harbor")
	ResponseEntity<Void> manageHarbor(@RequestBody HarborDTO harborDTO);
	
	@GetMapping(value="/ftr/workItems/managed-terminal/{terminalId}")
	WorkItemTerminalDTO fetchWorkItemDetailsByTerminalId(@PathVariable("terminalId") String terminalId) throws Exception;

}
