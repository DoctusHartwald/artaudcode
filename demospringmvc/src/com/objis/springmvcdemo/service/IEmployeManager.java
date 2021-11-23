package com.objis.springmvcdemo.service;

import java.util.List;

import com.objis.springmvcdemo.domaine.Employe;

public interface IEmployeManager {
	public Employe getEmploye(String login);
	public List<Employe> getAllEmployes();
	public void deleteEmployes();
	public void updateEmployes();
	public void createEmployes();
}
