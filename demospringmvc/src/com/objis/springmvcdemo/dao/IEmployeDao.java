package com.objis.springmvcdemo.dao;

import java.util.List;
import com.objis.springmvcdemo.domaine.Employe;

public interface IEmployeDao {
	
	public void saveEmploye(Employe employe);
	public Employe getEmployeById(int id);
	public Employe getEmployeByLogin(String login);
	public int getEmployesCount();
	public List<Employe> getAllEmployes();

}
