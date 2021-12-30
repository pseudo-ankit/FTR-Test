package com.infosys.ftr.workitems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infosys.ftr.workitems.entity.WorkItemDriverVehicleEntity;

@Repository
public interface WorkItemDriverVehicleRepository extends JpaRepository<WorkItemDriverVehicleEntity, String>{
	WorkItemDriverVehicleEntity findByVehicleNumber(String vehicleNumber);
}
