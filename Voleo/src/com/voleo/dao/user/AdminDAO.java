package com.voleo.dao.user;

import org.springframework.stereotype.Component;

import com.voleo.dao.AbstractDAO;
import com.voleo.entity.user.Admin;

@Component
public class AdminDAO extends AbstractDAO<Admin> implements IAdminDAO {

	public AdminDAO() {
		super(Admin.class);
	}
}
