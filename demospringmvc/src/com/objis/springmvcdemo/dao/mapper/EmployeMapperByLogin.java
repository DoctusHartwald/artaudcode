package com.objis.springmvcdemo.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.objis.springmvcdemo.domaine.Employe;


public class EmployeMapperByLogin implements RowMapper{

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Employe vEmploye = new Employe();
		vEmploye.setNom(rs.getString("nom"));
		vEmploye.setPrenom(rs.getString("prenom"));
		
		return vEmploye;
	}

}
