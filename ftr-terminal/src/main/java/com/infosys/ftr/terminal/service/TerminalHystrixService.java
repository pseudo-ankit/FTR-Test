package com.infosys.ftr.terminal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.ftr.terminal.controller.TerminalWorkItemFeign;
import com.infosys.ftr.terminal.dto.HarborDTO;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class TerminalHystrixService {
	
	@Autowired
	TerminalWorkItemFeign terminalWorkItemFeign;
	
	@HystrixCommand(fallbackMethod = "manageHarborFallback")
	public void manageHarbor(HarborDTO harborDTO) {
		System.out.println("in normal method");
		terminalWorkItemFeign.manageHarbor(harborDTO);
		}

	
	public void manageHarborFallback(HarborDTO harborDTO) {
		System.out.println("fallback method");	
		}
	
}