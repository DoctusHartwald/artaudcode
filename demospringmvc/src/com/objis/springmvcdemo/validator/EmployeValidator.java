package com.objis.springmvcdemo.validator;


import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.objis.springmvcdemo.domaine.Employe;

public class EmployeValidator implements Validator{

	@Override
	public boolean supports(Class clazz) {
		return clazz.equals(Employe.class);
	}

	@Override
	public void validate(Object command, Errors errors) {
		Employe employee= (Employe)command;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "required.login","Vous avez pas rempli le login");
	}
	

}
