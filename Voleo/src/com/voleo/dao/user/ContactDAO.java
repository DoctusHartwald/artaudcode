package com.voleo.dao.user;

import java.util.Collection;

import org.springframework.stereotype.Component;

import com.voleo.dao.AbstractDAO;
import com.voleo.entity.user.Contact;

@Component
public class ContactDAO extends AbstractDAO<Contact> implements IContactDAO {

	public ContactDAO() {
		super(Contact.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Contact> getContactsByUserId(Long userId) {
		return jpaTemplate.find("select c from User u left outer join u.contacts c " +
				"where u.id = ?1 and c.id!= ?1", userId);
	}

	@SuppressWarnings("unchecked")
	public Collection<Contact> getContactbyLastname(Contact contact){
		return jpaTemplate.find("from Contact c where c.lastname = ?1",contact.getLastname());
	}
	
	@Override
	public int getCountContactByUser(Long userId) {
		return ((Long)jpaTemplate.find("select count(*) from Contact c where " +
				"c.user.id = ?1 and c.id!= ?1", userId).get(0)).intValue();
	}
}
