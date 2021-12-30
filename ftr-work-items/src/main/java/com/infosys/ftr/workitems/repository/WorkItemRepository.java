package com.infosys.ftr.workitems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infosys.ftr.workitems.entity.WorkItemEntity;

@Repository
public interface WorkItemRepository extends JpaRepository<WorkItemEntity, String>{
	
	public List<WorkItemEntity> findByUserId(Long userId);

}
