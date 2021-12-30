package com.infosys.ftr.workitems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infosys.ftr.workitems.entity.WorkItemTerminalEntity;

@Repository
public interface WorkItemTerminalRepository extends JpaRepository<WorkItemTerminalEntity, String>{
	
	WorkItemTerminalEntity findByTerminalId(String terminalId);

}
