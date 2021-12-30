package com.infosys.ftr.vehicles.controller;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.infosys.ftr.vehicles.dto.WorkItemDriverVehicleDTO;

@FeignClient("WORKITEMS")
public interface WorkItemsFeign {
	
	@GetMapping(value = "/ftr/workItems/{fromCountry}")
	List<String> fetchAvailableHarborLocations(@PathVariable("fromCountry") String fromCountry);
	
	@GetMapping(value ="/ftr/workItems/managed-vehicle/{vehicleNumber}")
	WorkItemDriverVehicleDTO fetchWorkItemDetailsByVehicleNumber(@PathVariable("vehicleNumber") String vehicleNumber) throws Exception;

}
