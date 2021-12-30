package com.infosys.ftr.workitems.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.ftr.workitems.dto.HarborDTO;
import com.infosys.ftr.workitems.dto.Message;
import com.infosys.ftr.workitems.dto.WorkItemAssociationDTO;
import com.infosys.ftr.workitems.dto.WorkItemDTO;
import com.infosys.ftr.workitems.dto.WorkItemDriverVehicleDTO;
import com.infosys.ftr.workitems.dto.WorkItemTerminalDTO;
import com.infosys.ftr.workitems.exception.FTRException;
import com.infosys.ftr.workitems.service.WorkItemService;

@RestController
@RequestMapping("/ftr/workItems")
@CrossOrigin
public class WorkItemsController {
	
	private static Logger logger = LoggerFactory.getLogger(WorkItemsController.class);
	
	private WorkItemService workItemService;
	
	@Autowired
	public WorkItemsController(final WorkItemService workItemService) {
		this.workItemService = workItemService;
	}

	@PostMapping
	public ResponseEntity<WorkItemDTO> createWorkItem(@RequestBody WorkItemDTO workItemDTO){
		logger.info("createWorkItem rest controller ....");
		return ResponseEntity.ok(workItemService.createWorkitem(workItemDTO));
	}

	@PutMapping("/{workItemId}")
	public ResponseEntity<Message> updateWorkItem(@RequestBody WorkItemDTO workItemDTO, @PathVariable("workItemId") String workItemId) throws FTRException{
		logger.info("updateWorkItem rest controller ....");
		if(StringUtils.isBlank(workItemDTO.getStatus())) {
			return ResponseEntity.ok(new Message(workItemService.updateWorkitemId(workItemId, workItemDTO)));
		} else {
			return ResponseEntity.ok(new Message(workItemService.updateWorkItemStatus(workItemId, workItemDTO)));
		}
	}

	@GetMapping
	public ResponseEntity<List<WorkItemDTO>> fetchWorkItemDetails(){
		logger.info("fetchWorkItemDetails rest controller ....");
		return ResponseEntity.ok(workItemService.fetchWorkItemDetails());
	}

	@GetMapping("/managed-user/{userId}")
	public ResponseEntity<List<WorkItemDTO>> trackWorkItemByUser(@PathVariable("userId") String userId){
		logger.info("trackWorkItemByUser rest controller ....");
		return ResponseEntity.ok(workItemService.trackWorkitemByUser(Long.valueOf(userId)));
	}

	@GetMapping("/managed-status/{workItemId}")
	public ResponseEntity<WorkItemAssociationDTO> fetchWorkItemStatus(@PathVariable("workItemId") String workItemId) throws FTRException{
		logger.info("fetchWorkItemStatus rest controller ....");
		return ResponseEntity.ok(workItemService.fetchWorkItemStatus(workItemId));
	}

	@PutMapping("/managed-update/{workItemId}")
	public ResponseEntity<Message> updateWorkItemStatus(@RequestBody WorkItemDTO workItemDTO, @PathVariable("workItemId") String workItemId) throws FTRException{
		logger.info("updateWorkItemStatus rest controller ....");
		return ResponseEntity.ok(new Message(workItemService.updateWorkItemStatus(workItemId, workItemDTO)));
	}
	
	@PostMapping("/managed-terminal/{workItemId}")
	public ResponseEntity<Message> assignTerminalforWorKitem(@PathVariable("workItemId") String workItemId) throws FTRException{
		logger.info("assignTerminalforWorKitem rest controller ....");
		return ResponseEntity.ok(new Message(workItemService.assignTerminalForWorkitem(workItemId)));
	}
	
	@GetMapping("/managed-vehicle/{vehicleNumber}")
	public ResponseEntity<WorkItemDriverVehicleDTO> fetchWorkItemDetailsByVehicleNumber(@PathVariable("vehicleNumber") String vehicleNumber) throws FTRException{
		logger.info("fetchWorkItemDetailsByVehicleNumber rest controller ....");
		return ResponseEntity.ok(workItemService.fetchWorkItemDetailsByVehicleNumber(vehicleNumber));
	}
	
	@GetMapping("/managed-terminal/{terminalId}")
	public ResponseEntity<WorkItemTerminalDTO> fetchWorkItemDetailsByTerminalId(@PathVariable("terminalId") String terminalId) throws FTRException{
		return ResponseEntity.ok(workItemService.fetchWorkItemDetailsByTerminalId(terminalId));
	}

	@PostMapping("/managed-vehicle/{workItemId}")
	public ResponseEntity<Message> allocateVehicle(@PathVariable("workItemId") String workItemId) throws FTRException{
		logger.info("allocateVehicle rest controller ....");
		return ResponseEntity.ok(new Message(workItemService.allocateVehicle(workItemId)));
	}
	
	@PostMapping("/harbor")
	public ResponseEntity<Void> manageHarbor(@RequestBody HarborDTO harborDTO){
		logger.info("manageHarbor rest controller ....");
		return ResponseEntity.ok(workItemService.manageHarbor(harborDTO));
	}
	
	@GetMapping("/{fromCountry}")
	public ResponseEntity<List<String>>	fetchAvailableHarborLocations(@PathVariable("fromCountry") String fromCountry){
		logger.info("fetchAvailableHarborLocations rest controller ....");
		return ResponseEntity.ok(workItemService.fetchAvailableHarborLocations(fromCountry));
	}
	
	@GetMapping("/managed-country")
	public ResponseEntity<List<String>>	fetchAvailableCountries(){
		logger.info("fetchAvailableCountries rest controller ....");
		return ResponseEntity.ok(workItemService.fetchAvailableCountries());
	}
}
