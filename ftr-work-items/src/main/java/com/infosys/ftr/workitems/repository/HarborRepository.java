package com.infosys.ftr.workitems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infosys.ftr.workitems.entity.HarborEntity;

@Repository
public interface HarborRepository extends JpaRepository<HarborEntity, String> {

}
