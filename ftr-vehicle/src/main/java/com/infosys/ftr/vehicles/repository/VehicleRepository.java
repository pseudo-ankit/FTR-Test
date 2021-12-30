package com.infosys.ftr.vehicles.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infosys.ftr.vehicles.entity.VehicleEntity;

public interface VehicleRepository extends JpaRepository<VehicleEntity, String>{
		
	public List<VehicleEntity> findByVehicleName(String vehicleName);
	
	public List<VehicleEntity> findByHarborLocationAndVehicleStatus(String harborLocation, String status);
}
