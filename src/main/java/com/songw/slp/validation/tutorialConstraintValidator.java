package com.songw.slp.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class tutorialConstraintValidator implements ConstraintValidator<tutorial, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		System.out.println("validation :" + value);

		return value != null;
	}
	
}
