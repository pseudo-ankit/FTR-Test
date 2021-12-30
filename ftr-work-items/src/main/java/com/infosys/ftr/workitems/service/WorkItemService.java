package com.infosys.ftr.workitems.service;

import java.util.List;

import com.infosys.ftr.workitems.dto.HarborDTO;
import com.infosys.ftr.workitems.dto.VehicleDTO;
import com.infosys.ftr.workitems.dto.WorkItemAssociationDTO;
import com.infosys.ftr.workitems.dto.WorkItemDTO;
import com.infosys.ftr.workitems.dto.WorkItemDriverVehicleDTO;
import com.infosys.ftr.workitems.dto.WorkItemTerminalDTO;
import com.infosys.ftr.workitems.exception.FTRException;

public interface WorkItemService {
	WorkItemDTO createWorkitem(WorkItemDTO workItemDTO);
	String updateWorkitemId(String workitemId, WorkItemDTO workItemDTO) throws FTRException;
	List<WorkItemDTO> fetchWorkItemDetails();
	List<WorkItemDTO> trackWorkitemByUser(Long userId);
	WorkItemAssociationDTO fetchWorkItemStatus(String workitemId) throws FTRException;
	String updateWorkItemStatus(String workitemId, WorkItemDTO workItemDTO) throws FTRException;
	WorkItemDTO fetchWorkItemById(String workitemId);
	String assignTerminalForWorkitem(String workitemId) throws FTRException;
	WorkItemTerminalDTO fetchTerminalByWorkitem(String workitemId) throws FTRException;
	WorkItemDriverVehicleDTO fetchWorkItemDetailsByVehicleNumber(String vehicleNumber) throws FTRException;
	String allocateVehicle(String workitemId) throws FTRException;
	VehicleDTO findVehicleForWorkitem(String workitemId,List<VehicleDTO> vehicleDtoList);
	Void manageHarbor(HarborDTO harborDTO);
	List<String> fetchAvailableHarborLocations(String country);
	List<String> fetchAvailableCountries();
	WorkItemTerminalDTO fetchWorkItemDetailsByTerminalId(String terminalId) throws FTRException;
}
