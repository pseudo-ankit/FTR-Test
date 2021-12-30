package com.infosys.ftr.vehicles.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.infosys.ftr.vehicles.entity.VehicleEntity;
import com.infosys.ftr.vehicles.utility.DoubleIntegerValidation;
import com.infosys.ftr.vehicles.utility.EnumValidator;
import com.infosys.ftr.vehicles.utility.VehicleStatusEnum;

public class VehicleDTO {
	
	@NotNull(message = "{vehicle.vehicleNumber.must}")
	@Pattern(regexp = "(([A-Za-z]{2})[0-9]{4})", message = "{vehicle.vehicleNumber.invalid}")
	private String vehicleNumber;
	@NotEmpty(message = "{vehicle.vehicleName.must}")
    @Size(max = 25, message = "{vehicle.vehicleName.invalid}")
    @Pattern(regexp = "(?i)(Tower crane|FirePlace Crane|Double treadwheel Crane|Crawler Crane|Aerial Crane|Deck Crane)", message = "{vehicle.vehicleName.invalid}")
    private String vehicleName;
	@NotNull(message = "{vehicle.maxLiftingCapacity.must}")
	@DoubleIntegerValidation(message ="{vehicle.maxLiftingCapacity.invalid}")
	private Double maxLiftingCapacity;
	@NotNull(message = "{vehicle.retireDate.must}")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MMM-yyyy")
	private Date retireDate;
	@NotNull(message = "{vehicle.vehicleStatus.must}")
	@EnumValidator(enumClass = VehicleStatusEnum.class, message ="{vehicle.vehicleStatus.invalid}")
	private String vehicleStatus;
	private String harborLocation;
	private String country;
	
	public VehicleDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VehicleDTO(String vehicleNumber, String vehicleName, Double maxLiftingCapacity, Date retireDate,
			String vehicleStatus, String harborLocation, String country) {
		super();
		this.vehicleNumber = vehicleNumber;
		this.vehicleName = vehicleName;
		this.maxLiftingCapacity = maxLiftingCapacity;
		this.retireDate = retireDate;
		this.vehicleStatus = vehicleStatus;
		this.harborLocation = harborLocation;
		this.country = country;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}

	public Double getMaxLiftingCapacity() {
		return maxLiftingCapacity;
	}

	public void setMaxLiftingCapacity(Double maxLiftingCapacity) {
		this.maxLiftingCapacity = maxLiftingCapacity;
	}

	public Date getRetireDate() {
		return retireDate;
	}

	public void setRetireDate(Date retireDate) {
		this.retireDate = retireDate;
	}

	public String getVehicleStatus() {
		return vehicleStatus;
	}

	public void setVehicleStatus(String vehicleStatus) {
		this.vehicleStatus = vehicleStatus;
	}

	public String getHarborLocation() {
		return harborLocation;
	}

	public void setHarborLocation(String harborLocation) {
		this.harborLocation = harborLocation;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	public static VehicleEntity prepareEntity(VehicleDTO vehicleDTO) {
		VehicleEntity entity = new VehicleEntity();
		entity.setVehicleNumber(vehicleDTO.getVehicleNumber());
		entity.setCountry(vehicleDTO.getCountry());
		entity.setHarborLocation(vehicleDTO.getHarborLocation());
		entity.setMaxLiftingCapacity(vehicleDTO.getMaxLiftingCapacity().intValue());
		entity.setRetireDate(vehicleDTO.getRetireDate());
		entity.setVehicleName(vehicleDTO.getVehicleName());
		entity.setVehicleStatus(vehicleDTO.getVehicleStatus());
		
		return entity;
	}
	
	public static VehicleDTO prepareDTO(VehicleEntity entity) {
		VehicleDTO dto = new VehicleDTO();
		dto.setCountry(entity.getCountry());
		dto.setHarborLocation(entity.getHarborLocation());
		dto.setMaxLiftingCapacity(entity.getMaxLiftingCapacity().doubleValue());
		dto.setRetireDate(entity.getRetireDate());
		dto.setVehicleName(entity.getVehicleName());
		dto.setVehicleNumber(entity.getVehicleNumber());
		dto.setVehicleStatus(entity.getVehicleStatus());
		
		return dto;
	}
	
}
