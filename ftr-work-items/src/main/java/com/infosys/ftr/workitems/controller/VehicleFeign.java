package com.infosys.ftr.workitems.controller;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.infosys.ftr.workitems.dto.Message;
import com.infosys.ftr.workitems.dto.VehicleDTO;
import com.infosys.ftr.workitems.exception.FTRException;

@FeignClient("VehicleMS")
public interface VehicleFeign {
	
	@GetMapping(value = "/ftr/vehicles/managed-number/{vehicleNumber}")
	VehicleDTO fetchVehicleDetailsByVehicleNumber(@PathVariable("vehicleNumber") String vehicleNumber) throws FTRException;

	@PutMapping(value = "/ftr/vehicles/{vehicleNumber}")
	Message updateVehicleStatus(@PathVariable("vehicleNumber") String vehicleNum, @RequestBody VehicleDTO dto) throws FTRException;
	
	@GetMapping(value = "/ftr/vehicles/harbor/{harborLocation}")
	public List<VehicleDTO> fetchVehicleByHarbor(@PathVariable("harborLocation") String harborLocation) throws FTRException;
}
