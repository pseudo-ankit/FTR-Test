package com.infosys.ftr.vehicles.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.ftr.vehicles.dto.Message;
import com.infosys.ftr.vehicles.dto.VehicleDTO;
import com.infosys.ftr.vehicles.exception.VehichleStatusExistException;
import com.infosys.ftr.vehicles.exception.VehicleFtrException;
import com.infosys.ftr.vehicles.exception.VehicleNotFoundException;
import com.infosys.ftr.vehicles.service.VehicleService;

@RestController
@CrossOrigin
@RequestMapping("ftr/vehicles")
public class VehicleRestController {
	
	@Autowired
	VehicleService vehicleService;
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@PostMapping
	public ResponseEntity<Message> insertNewVehicle(@Valid @RequestBody VehicleDTO dto) throws Exception{
		logger.info("Controller -> insertNewVehicle");
		String response = "";
		response = vehicleService.insertNewVehicle(dto);
		return ResponseEntity.ok(new Message(response));
	}
		
	@GetMapping
	public ResponseEntity<List<VehicleDTO>> fetchAvailableVehicles() throws VehicleFtrException, VehicleNotFoundException {
		logger.info("Controller -> fetchAvailableVehicles");
		return ResponseEntity.ok(vehicleService.fetchAvailableVehicles());
	}
	
	@GetMapping(value = "/managed-name/{vehicleName}")
	public 	ResponseEntity<List<VehicleDTO>> fetchVehicleDetailsByVehicleName(@PathVariable("vehicleName") String vehicleName) throws VehicleFtrException, VehicleNotFoundException {
		logger.info("Controller -> fetchVehicleDetailsByVehicleName");
		return ResponseEntity.ok(vehicleService.fetchVehicleDetailsByVehicleName(vehicleName));
	}
	
	@GetMapping(value = "/managed-number/{vehicleNumber}")
	public ResponseEntity<VehicleDTO> fetchVehicleDetailsByVehicleNumber(@PathVariable("vehicleNumber") String vehicleNumber) throws VehicleFtrException, VehicleNotFoundException {
		logger.info("Controller -> fetchVehicleDetailsByVehicleNumber");
		return ResponseEntity.ok(vehicleService.fetchVehicleDetailsByVehicleNumber(vehicleNumber));
	}
	
	@PutMapping(value = "/{vehicleNumber}")
	public ResponseEntity<Message> updateVehicleStatus(@PathVariable("vehicleNumber") String vehicleNum, @RequestBody VehicleDTO dto) throws VehicleNotFoundException, VehichleStatusExistException {
		logger.info("Controller -> updateVehicleStatus");
		return ResponseEntity.ok(new Message(vehicleService.updateVehicleStatus(vehicleNum, dto)));
	}
	
	@DeleteMapping(value = "/{vehicleNumber}")
	public ResponseEntity<Message> removeVehicle(@PathVariable("vehicleNumber") String vehicleNum) throws Exception {
		logger.info("Controller -> removeVehicle");
		return ResponseEntity.ok(new Message(vehicleService.removeVehicle(vehicleNum)));
	}
	
	
	@GetMapping(value = "/harbor/{harborLocation}")
	public ResponseEntity<List<VehicleDTO>> fetchVehicleByHarbor(@PathVariable("harborLocation") String harborLocation) throws VehicleNotFoundException{
		logger.info("Controller -> fetchVehicleByHarbor");
		return ResponseEntity.ok(vehicleService.fetchVehicleByHarbor(harborLocation));
	}
}
