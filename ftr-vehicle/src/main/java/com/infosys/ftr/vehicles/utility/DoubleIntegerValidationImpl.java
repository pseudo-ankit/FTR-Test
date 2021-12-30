package com.infosys.ftr.vehicles.utility;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DoubleIntegerValidationImpl implements ConstraintValidator<DoubleIntegerValidation, Double> {
	
	private Double number;
	
	@Override
	public void initialize(DoubleIntegerValidation constraintAnnotation) {
		// TODO Auto-generated method stub
	}
	@Override
	public boolean isValid(Double value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		if(value!=null) {
			number = ((Integer)value.intValue()).doubleValue();
			System.out.println(value);
			System.out.println(number);
			return number >=0 && number.equals(value);
		}
		return false;
	}

}
