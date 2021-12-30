package com.infosys.ftr.workitems.utility;
import java.io.Serializable;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class WorkItemIdGenerator implements IdentifierGenerator {
	
	private static int counter = 2100;
	
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) {
		int id = counter++;
		return "J" + id;
	}
}