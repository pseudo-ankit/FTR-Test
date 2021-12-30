package com.infosys.ftr.vehicles.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.infosys.ftr.vehicles.controller.WorkItemsFeign;
import com.infosys.ftr.vehicles.dto.VehicleDTO;
import com.infosys.ftr.vehicles.dto.WorkItemDriverVehicleDTO;
import com.infosys.ftr.vehicles.entity.VehicleEntity;
import com.infosys.ftr.vehicles.exception.ExceptionConstants;
import com.infosys.ftr.vehicles.exception.VehichleStatusExistException;
import com.infosys.ftr.vehicles.exception.VehicleFtrException;
import com.infosys.ftr.vehicles.exception.VehicleNotFoundException;
import com.infosys.ftr.vehicles.repository.VehicleRepository;

@Service
public class VehicleServiceImpl implements VehicleService {
	
	@Value("${vehicle.create.success}")
	private String creationMessage;
	
	@Value("${vehicle.update.success}")
	private String updateMessage;
	
	@Value("${vehicle.delete.success}")
	private String deleteMessage;
	
	@Autowired
	VehicleRepository vehicleRepository;
	
	@Autowired
	WorkItemsFeign workItemsFeign;
	
	@Autowired
	private Environment env;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	private final String VEHICLE_STATUS = "Active";
	
	@Override
	public String insertNewVehicle(VehicleDTO vehicleDto) throws VehicleFtrException {
		// TODO Auto-generated method stub
		logger.info("ServiceImpl -> insertNewVehicle");
		List<String> harborList = workItemsFeign.fetchAvailableHarborLocations(vehicleDto.getCountry());
		String str = vehicleDto.getHarborLocation().toLowerCase();
		if(harborList.isEmpty() || harborList.get(0).isBlank() || harborList.stream().noneMatch(s -> s.equalsIgnoreCase(str))) {
			throw new VehicleFtrException("invalid.harbor");
		}
		Boolean response = vehicleRepository.findById(vehicleDto.getVehicleNumber()).isPresent();
		if(response) {
			throw new VehicleFtrException("vehicle.alreadyexists");
		}
		VehicleEntity response1 = vehicleRepository.save(VehicleDTO.prepareEntity(vehicleDto));
		return creationMessage+" "+response1.getVehicleNumber();
	}

	@Override
	public List<VehicleDTO> fetchAvailableVehicles() throws VehicleFtrException, VehicleNotFoundException {
		// TODO Auto-generated method stub
		logger.info("ServiceImpl -> fetchAvailableVehicles");
		List<VehicleDTO> response = new ArrayList<>();
		List<VehicleEntity> entities = vehicleRepository.findAll();
		
		if(entities.size() > 0)
			response = entities.stream().map(data -> VehicleDTO.prepareDTO(data)).collect(Collectors.toList());
		else
			throw new VehicleNotFoundException();
		
		return response;
	}

	@Override
	public List<VehicleDTO> fetchVehicleDetailsByVehicleName(String vehicleName) throws VehicleFtrException, VehicleNotFoundException {
		// TODO Auto-generated method stub
		logger.info("ServiceImpl -> fetchVehicleDetailsByVehicleName");
		List<VehicleDTO> response = new ArrayList<>();
		List<VehicleEntity> entities = vehicleRepository.findByVehicleName(vehicleName);
		
		if(entities.size() == 0) {
			throw new VehicleNotFoundException();
		}
		
		response = entities.stream().map(data -> VehicleDTO.prepareDTO(data)).collect(Collectors.toList());
		return response;
	}

	@Override
	public VehicleDTO fetchVehicleDetailsByVehicleNumber(String vehicleNumber) throws VehicleFtrException, VehicleNotFoundException {
		// TODO Auto-generated method stub
		logger.info("ServiceImpl -> fetchVehicleDetailsByVehicleNumber");
		Optional<VehicleEntity> entity = vehicleRepository.findById(vehicleNumber);
		if(entity.isEmpty()) {
			throw new VehicleNotFoundException();
		}
		
		return VehicleDTO.prepareDTO(entity.get());
	}

	@Override
	public String updateVehicleStatus(String vehicleNum, VehicleDTO dto) throws VehicleNotFoundException, VehichleStatusExistException {
		// TODO Auto-generated method stub
		logger.info("ServiceImpl -> updateVehicleStatus");
		Optional<VehicleEntity> entity = vehicleRepository.findById(vehicleNum);
		if(entity.isEmpty()) {
			throw new VehicleNotFoundException();
		}
		
		if(entity.get().getVehicleStatus().equals(dto.getVehicleStatus())) {
			System.out.println(dto.getVehicleStatus());
			throw new VehichleStatusExistException(dto.getVehicleStatus());
		}
		
		entity.get().setVehicleStatus(dto.getVehicleStatus());
		vehicleRepository.saveAndFlush(entity.get());
		
		return updateMessage+" "+dto.getVehicleStatus();
	}

	@Override
	public String removeVehicle(String vechileNum) throws VehicleNotFoundException, Exception {
		// TODO Auto-generated method stub	
		logger.info("ServiceImpl -> removeVehicle");
		WorkItemDriverVehicleDTO workItemVehicle = workItemsFeign.fetchWorkItemDetailsByVehicleNumber(vechileNum);
		if(workItemVehicle.getVehicle()==null) {
			try{
				vehicleRepository.deleteById(vechileNum);
			} catch (EmptyResultDataAccessException ex) {
				throw new VehicleNotFoundException();
			}
		}
		else {
			System.out.println("222");
			throw new VehicleFtrException("vehicle.assigned.workitem");
		}
		return deleteMessage;
	}

	@Override
	public List<VehicleDTO> fetchVehicleByHarbor(String harborLocation) throws VehicleNotFoundException {
		// TODO Auto-generated method stub
		logger.info("ServiceImpl -> fetchVehicleByHarbor");
		List<VehicleDTO> response = new ArrayList<>();
		List<VehicleEntity> entities = vehicleRepository.findByHarborLocationAndVehicleStatus(harborLocation, VEHICLE_STATUS);
		
		if(entities.size() == 0) {
			throw new VehicleNotFoundException();
		}
		
		response = entities.stream().map(data -> VehicleDTO.prepareDTO(data)).collect(Collectors.toList());
		return response;
	}

}
