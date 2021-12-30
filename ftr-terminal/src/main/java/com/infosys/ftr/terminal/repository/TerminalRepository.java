package com.infosys.ftr.terminal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infosys.ftr.terminal.entity.Terminal;

public interface TerminalRepository extends JpaRepository<Terminal, String> {
	
	Optional<Terminal> findByTerminalId(String terminalId);

	Optional<List<Terminal>> findByItemType(String itemType);

}
