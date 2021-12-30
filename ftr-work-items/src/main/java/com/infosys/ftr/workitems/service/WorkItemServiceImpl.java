package com.infosys.ftr.workitems.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.infosys.ftr.workitems.controller.TerminalFeign;
import com.infosys.ftr.workitems.controller.VehicleFeign;
import com.infosys.ftr.workitems.dto.HarborDTO;
import com.infosys.ftr.workitems.dto.TerminalDTO;
import com.infosys.ftr.workitems.dto.VehicleDTO;
import com.infosys.ftr.workitems.dto.WorkItemAssociationDTO;
import com.infosys.ftr.workitems.dto.WorkItemDTO;
import com.infosys.ftr.workitems.dto.WorkItemDriverVehicleDTO;
import com.infosys.ftr.workitems.dto.WorkItemTerminalDTO;
import com.infosys.ftr.workitems.entity.HarborEntity;
import com.infosys.ftr.workitems.entity.WorkItemDriverVehicleEntity;
import com.infosys.ftr.workitems.entity.WorkItemEntity;
import com.infosys.ftr.workitems.entity.WorkItemTerminalEntity;
import com.infosys.ftr.workitems.exception.FTRException;
import com.infosys.ftr.workitems.repository.HarborRepository;
import com.infosys.ftr.workitems.repository.WorkItemDriverVehicleRepository;
import com.infosys.ftr.workitems.repository.WorkItemRepository;
import com.infosys.ftr.workitems.repository.WorkItemTerminalRepository;
import com.infosys.ftr.workitems.utility.WorkItemsUtility;

@Service
public class WorkItemServiceImpl implements WorkItemService {
	
	private WorkItemRepository workItemRepository;
	private WorkItemDriverVehicleRepository workItemDriverVehicleRepository;
	private WorkItemTerminalRepository workItemTerminalRepository;
	private HarborRepository harborRepository;
	private TerminalFeign terminalFeign;
	private VehicleFeign vehicleFeign;
	
	@Autowired
	public WorkItemServiceImpl(final WorkItemRepository workItemRepository, 
			final WorkItemDriverVehicleRepository workItemDriverVehicleRepository,
			final WorkItemTerminalRepository workItemTerminalRepository,
			final HarborRepository harborRepository,
			final TerminalFeign terminalFeign,
			final VehicleFeign vehicleFeign) {
		this.workItemRepository = workItemRepository;
		this.workItemDriverVehicleRepository = workItemDriverVehicleRepository;
		this.workItemTerminalRepository = workItemTerminalRepository;
		this.harborRepository = harborRepository;
		this.terminalFeign = terminalFeign;
		this.vehicleFeign = vehicleFeign;
	}

	@Override
	public WorkItemDTO createWorkitem(WorkItemDTO workItemDTO) {
		return WorkItemDTO.valueOf(workItemRepository.saveAndFlush(WorkItemDTO.createEntity(workItemDTO)));
	}

	@Override
	public String updateWorkitemId(String workitemId, WorkItemDTO workItemDTO) throws FTRException {
		try {
			WorkItemEntity workItemEntity =  workItemRepository.findById(workitemId).orElse(null);
			if(Objects.nonNull(workItemEntity)) {
				if(Objects.nonNull(workItemDTO.getAvailableHarborLocations())) {
					workItemEntity.setAvailableHarborLocations(workItemDTO.getAvailableHarborLocations());
				}
				if(Objects.nonNull(workItemDTO.getShippingDate())) {
					workItemEntity.setShippingDate(workItemDTO.getShippingDate());
				}
				workItemRepository.saveAndFlush(workItemEntity);
				return "Workitem details are successfully updated";
			} else {
				return "Workitem details not found for given workitem id";
			}
		} catch (Exception e) {
			throw new FTRException("Invalid Data");
		}
	}

	@Override
	public List<WorkItemDTO> fetchWorkItemDetails() {
		List<WorkItemEntity> workItemEntityList = workItemRepository.findAll();
		if(CollectionUtils.isEmpty(workItemEntityList)) {
			return List.of(new WorkItemDTO());
		} else {
			return workItemEntityList.stream().map(WorkItemDTO::valueOf).collect(Collectors.toList());
		}
	}

	@Override
	public List<WorkItemDTO> trackWorkitemByUser(Long userId) {
		List<WorkItemEntity> workItemEntityList = workItemRepository.findByUserId(userId);
		if(CollectionUtils.isEmpty(workItemEntityList)) {
			return List.of(new WorkItemDTO());
		} else {
			return workItemEntityList.stream().map(WorkItemDTO::valueOf).collect(Collectors.toList());
		}
	}

	@Override
	public WorkItemAssociationDTO fetchWorkItemStatus(String workitemId) throws FTRException {
		WorkItemAssociationDTO workItemAssociationDTO = new WorkItemAssociationDTO();
		WorkItemDriverVehicleEntity workItemDriverVehicleEntity = workItemDriverVehicleRepository.findById(workitemId).orElse(null);
		VehicleDTO vehicle = null;
		if(Objects.isNull(workItemDriverVehicleEntity)) {
			vehicle = new VehicleDTO();
		} else {
			workItemAssociationDTO.setAssignedWorkitemStatus(workItemDriverVehicleEntity.getAssignedWorkitemStatus());
			vehicle = vehicleFeign.fetchVehicleDetailsByVehicleNumber(workItemDriverVehicleEntity.getVehicleNumber());
		}
		workItemAssociationDTO.setVehicle(vehicle);
		
		WorkItemTerminalEntity workItemTerminalEntity = workItemTerminalRepository.findById(workitemId).orElse(null);
		TerminalDTO terminal = null;
		if(Objects.isNull(workItemTerminalEntity)) {
			terminal = new TerminalDTO();
		} else {
			terminal = terminalFeign.fetchTerminalByTerminalId(workItemTerminalEntity.getTerminalId());
		}
		workItemAssociationDTO.setTerminal(terminal);
		
		return workItemAssociationDTO;
	}

	@Override
	public String updateWorkItemStatus(String workitemId, WorkItemDTO workItemDTO) throws FTRException {
		WorkItemEntity workItemEntity =  workItemRepository.findById(workitemId).orElse(null);
		if(Objects.isNull(workItemEntity)) {
			throw new FTRException("Workitem details not found for given workitem id");
		} else {
			WorkItemDriverVehicleEntity workItemDriverVehicleEntity = workItemDriverVehicleRepository.findById(workitemId).orElse(null);
			if(Objects.nonNull(workItemDriverVehicleEntity)) {
				if(!workItemEntity.getShippingDate().isAfter(LocalDate.now())) {
					workItemDriverVehicleEntity.setAssignedWorkitemStatus("Completed");
					workItemDriverVehicleRepository.saveAndFlush(workItemDriverVehicleEntity);
					
					VehicleDTO vehicle = new VehicleDTO();
					vehicle.setVehicleStatus("Active");
					vehicleFeign.updateVehicleStatus(workItemDriverVehicleEntity.getVehicleNumber(), vehicle);
					
					WorkItemTerminalEntity workItemTerminalEntity = workItemTerminalRepository.findById(workitemId).orElse(null);
					if(Objects.nonNull(workItemTerminalEntity)) {
						int quantity = 0;
						String quantityStr = WorkItemsUtility.getNumericValueFromAlphanumeric(workItemEntity.getQuantity()); 
						if(StringUtils.isNotBlank(quantityStr)) {
							quantity = Integer.parseInt(("-" + quantityStr));
						}
						terminalFeign.updateTerminal(workItemTerminalEntity.getTerminalId(), quantity);
					}
					return "Work item status has updated successfully";
				} else {
					if("Completed".equals(workItemDTO.getStatus())) {
						throw new FTRException("Workitem status can not be completed as the shipping date is in future date.");
					} else {
						workItemDriverVehicleEntity.setAssignedWorkitemStatus(workItemDTO.getStatus());
						workItemDriverVehicleRepository.saveAndFlush(workItemDriverVehicleEntity);
						return "Work item status has updated successfully";
					}
				}
			} else {
				throw new FTRException("The workitem is not associated with any vehicle.");
			}
		}
	}

	@Override
	public WorkItemDTO fetchWorkItemById(String workitemId) {
		return WorkItemDTO.valueOf(workItemRepository.findById(workitemId).get());
	}

	@Override
	public String assignTerminalForWorkitem(String workitemId) throws FTRException {
		WorkItemTerminalEntity workItemTerminalEntity = workItemTerminalRepository.findById(workitemId).orElse(null);
		if(Objects.isNull(workItemTerminalEntity)) {
			WorkItemEntity workItemEntity =  workItemRepository.findById(workitemId).orElse(null);
			List<TerminalDTO> terminalDTOList = terminalFeign.fetchTerminalsByItemType(workItemEntity.getItemType());
			if(CollectionUtils.isEmpty(terminalDTOList)) {
				throw new FTRException("This workitem is already allocated to a terminal");
			} else {
				int quantity = 0;
				String workItemQuantityStr = WorkItemsUtility.getNumericValueFromAlphanumeric(workItemEntity.getQuantity()); 
				if(StringUtils.isNotBlank(workItemQuantityStr)) {
					quantity = Integer.parseInt(workItemQuantityStr);
				}
				final int workItemsQuantity = quantity;
				List<TerminalDTO> finalTerminals = terminalDTOList
						.stream()
						.filter(t -> StringUtils.equals(t.getItemType(), workItemEntity.getItemType()) && 
								t.getAvailableCapacity() > workItemsQuantity &&
								"Available".equals(t.getStatus()))
						.collect(Collectors.toList());
				if(CollectionUtils.isEmpty(finalTerminals)) {
					throw new FTRException("There are no terminals available for this workitem capacity");
				} else {
					workItemTerminalEntity = new WorkItemTerminalEntity();
					workItemTerminalEntity.setWorkItemId(workitemId);
					workItemTerminalEntity.setTerminalId(finalTerminals.get(0).getTerminalId());
					workItemTerminalRepository.saveAndFlush(workItemTerminalEntity);
					
					terminalFeign.updateTerminal(finalTerminals.get(0).getTerminalId(), quantity);
					
					return "Workitem successfully allocated to terminal id";
				}
			}
		} else {
			throw new FTRException("This workitem is already allocated to a terminal");
		}
	}

	@Override
	public WorkItemTerminalDTO fetchTerminalByWorkitem(String workitemId) throws FTRException {
		WorkItemTerminalDTO workItemTerminalDTO = new WorkItemTerminalDTO();
		WorkItemEntity workItemEntity =  workItemRepository.findById(workitemId).orElse(null);
		if(Objects.isNull(workItemEntity)) {
			throw new FTRException("Workitem details not found for given workitem id");
		} else {
			WorkItemTerminalEntity workItemTerminalEntity = workItemTerminalRepository.findById(workitemId).orElse(null);
			if(Objects.nonNull(workItemTerminalEntity)) {
				workItemTerminalDTO.setWorkItem(WorkItemDTO.valueOf(workItemEntity));
				TerminalDTO terminal = terminalFeign.fetchTerminalByTerminalId(workItemTerminalEntity.getTerminalId());
				workItemTerminalDTO.setTerminal(terminal);
			}
		}
		return workItemTerminalDTO;
	}

	@Override
	public WorkItemDriverVehicleDTO fetchWorkItemDetailsByVehicleNumber(String vehicleNumber) throws FTRException {
		WorkItemDriverVehicleDTO workItemDriverVehicleDTO = new WorkItemDriverVehicleDTO();
		WorkItemDriverVehicleEntity workItemDriverVehicleEntity = workItemDriverVehicleRepository.findByVehicleNumber(vehicleNumber);
		if(Objects.nonNull(workItemDriverVehicleEntity)) {
			WorkItemEntity workItemEntity =  workItemRepository.findById(workItemDriverVehicleEntity.getWorkItemId()).orElse(null);
			workItemDriverVehicleDTO.setWorkItem(WorkItemDTO.valueOf(workItemEntity));
			
			VehicleDTO vehicle = vehicleFeign.fetchVehicleDetailsByVehicleNumber(workItemDriverVehicleEntity.getVehicleNumber());
			workItemDriverVehicleDTO.setVehicle(vehicle);
		}
		
		return workItemDriverVehicleDTO;
	}
	
	@Override
	public WorkItemTerminalDTO fetchWorkItemDetailsByTerminalId(String terminalId) throws FTRException {
		WorkItemTerminalDTO workItemTerminalDTO = new WorkItemTerminalDTO();
		WorkItemTerminalEntity workItemTerminalEntity = workItemTerminalRepository.findByTerminalId(terminalId);
		if(Objects.nonNull(workItemTerminalEntity)) {
			WorkItemEntity workItemEntity =  workItemRepository.findById(workItemTerminalEntity.getWorkItemId()).orElse(null);
			workItemTerminalDTO.setWorkItem(WorkItemDTO.valueOf(workItemEntity));
			TerminalDTO terminal = terminalFeign.fetchTerminalByTerminalId(workItemTerminalEntity.getTerminalId());
			workItemTerminalDTO.setTerminal(terminal);
		}
		return workItemTerminalDTO;
	}

	@Override
	public String allocateVehicle(String workitemId) throws FTRException {
		WorkItemDriverVehicleEntity workItemDriverVehicleEntity = workItemDriverVehicleRepository.findById(workitemId).orElse(null);
		if(Objects.isNull(workItemDriverVehicleEntity)) {
			WorkItemEntity workItemEntity =  workItemRepository.findById(workitemId).orElse(null);
			List<VehicleDTO> vehicleList = null;
			try {
				vehicleList = vehicleFeign.fetchVehicleByHarbor(workItemEntity.getAvailableHarborLocations());
			} catch(Exception e) {
				
			}
			if(CollectionUtils.isEmpty(vehicleList)) {
				throw new FTRException("Vehicle not available");
			} else {
				workItemDriverVehicleEntity = new WorkItemDriverVehicleEntity();
				workItemDriverVehicleEntity.setWorkItemId(workitemId);
				workItemDriverVehicleEntity.setVehicleNumber(vehicleList.get(0).getVehicleNumber());
				workItemDriverVehicleEntity.setDriverId("AWQ12312YT76");
				workItemDriverVehicleEntity.setAssignedWorkitemStatus("InProgress");
				workItemDriverVehicleRepository.saveAndFlush(workItemDriverVehicleEntity);
				
				VehicleDTO vehicle = new VehicleDTO();
				vehicle.setVehicleStatus("InProgress");
				vehicleFeign.updateVehicleStatus(vehicleList.get(0).getVehicleNumber(), vehicle);
			}
		} else {
			throw new FTRException("Workitem is already allocated with Vehicle");
		}
		return "Workitem is allocated with Vehicle successfully.";
	}

	@Override
	public VehicleDTO findVehicleForWorkitem(String workitemId, List<VehicleDTO> vehicleDtoList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void manageHarbor(HarborDTO harborDTO) {
		HarborEntity harborEntity = harborRepository.findById(harborDTO.getCountry()).orElse(null);
		if("ADD".equals(harborDTO.getOpCode())) {
			if(Objects.isNull(harborEntity)) {
				harborRepository.saveAndFlush(HarborDTO.createEntity(harborDTO));
			} else {
				if(!harborEntity.getAvailableHarborLocations().contains(harborDTO.getAvailableHarborLocations())) {
					harborEntity.setAvailableHarborLocations(
							new StringBuilder()
							.append(harborEntity.getAvailableHarborLocations())
							.append(", ")
							.append(harborDTO.getAvailableHarborLocations()).toString());
					harborRepository.saveAndFlush(harborEntity);
				}
			}
		} else {
			if(Objects.nonNull(harborEntity)) {
				if(harborEntity.getAvailableHarborLocations().contains(harborDTO.getAvailableHarborLocations())) {
					String harborLocation = harborEntity.getAvailableHarborLocations();
					harborLocation = harborLocation.replaceAll(", " + harborDTO.getAvailableHarborLocations(), "");
					harborLocation = harborLocation.replaceAll(harborDTO.getAvailableHarborLocations(), "");
					harborEntity.setAvailableHarborLocations(harborLocation);
					harborRepository.saveAndFlush(harborEntity);
				}
			}
		}
		return null;
	}
	
	@Override
	public List<String> fetchAvailableHarborLocations(String country) {
		HarborEntity harborEntity = harborRepository.findById(country).orElse(null);
		if(Objects.isNull(harborEntity)) {
			return List.of(""); 
		} else {
			return Arrays.asList(harborEntity.getAvailableHarborLocations().split(", "));
		}
	}

	@Override
	public List<String> fetchAvailableCountries() {
		List<HarborEntity> harborEntityList = harborRepository.findAll();
		if(CollectionUtils.isEmpty(harborEntityList)) {
			return List.of("");
		} else {
			return harborEntityList.stream().map(HarborEntity::getCountry).collect(Collectors.toList());
		}
	}
	
}
