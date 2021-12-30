package com.infosys.ftr.vehicles.service;

import java.util.List;

import com.infosys.ftr.vehicles.dto.VehicleDTO;
import com.infosys.ftr.vehicles.exception.VehichleStatusExistException;
import com.infosys.ftr.vehicles.exception.VehicleFtrException;
import com.infosys.ftr.vehicles.exception.VehicleNotFoundException;

public interface VehicleService {
	
	public String insertNewVehicle(VehicleDTO vehicleDto) throws VehicleFtrException;
	
	public List<VehicleDTO> fetchAvailableVehicles() throws VehicleFtrException, VehicleNotFoundException;
	
	public List<VehicleDTO> fetchVehicleDetailsByVehicleName(String vehicleName) throws VehicleFtrException, VehicleNotFoundException;
	
	public VehicleDTO  fetchVehicleDetailsByVehicleNumber(String vehicleNumber) throws VehicleFtrException, VehicleNotFoundException;
	
	public String updateVehicleStatus(String vehicleNum, VehicleDTO dto) throws VehicleNotFoundException, VehichleStatusExistException;
	
	public String removeVehicle(String vechileNum) throws VehicleNotFoundException, Exception;
	
	public List<VehicleDTO> fetchVehicleByHarbor(String harborLocation) throws VehicleNotFoundException;
}
