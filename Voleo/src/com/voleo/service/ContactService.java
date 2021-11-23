package com.voleo.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.voleo.dao.user.IContactDAO;
import com.voleo.dao.user.IUserDAO;
import com.voleo.entity.user.Contact;
import com.voleo.entity.user.ContactGroup;
import com.voleo.entity.user.User;
import com.voleo.entity.user.UserType;

@Component
@Transactional
public class ContactService implements IContactService {
	
	@Autowired
	private IUserDAO userDAO;

	@Autowired
	private IContactDAO contactDAO;
	
	@Override
	public Collection<ContactGroup> getContactGroupsByUserId(Long userId) {
		return userDAO.getContactGroupsByUserId(userId);
	}

	@Override
	public Collection<Contact> getContactsWithoutGroup(Long userId) {
		User user = userDAO.getById(userId);
		return userDAO.getContactsWithoutGroup(user);
	}
	
	public Collection<Contact> getContacts(Long userId) {
		return contactDAO.getContactsByUserId(userId);
	}

	@Override
	public void addContact(Contact newContact, Long userId) {
		//récupération de l'id de l'utilisateur.
		User user = userDAO.getById(userId);
		newContact.setUser(user);
		contactDAO.add(newContact);
	}
	
	
	public void addnewContact(Contact newContact){
		 contactDAO.add(newContact);
	}
	
	public void removeContact(Long contactId) {
		contactDAO.removeById(contactId);
	}
	
	public Collection<Contact> getContactbyLastname(Contact contact){
		return contactDAO.getContactbyLastname(contact);
	}
	
	public void updateContact(Contact contact,Long userId) {
		User user = userDAO.getById(userId);
		contact.setUser(user);
		contactDAO.update(contact);
	}
	@Override
	public int getCountContactByUser(Long userId){
		return contactDAO.getCountContactByUser(userId);
	}
	
	@Override
	public Contact getContactById(Long id) {
		return contactDAO.getById(id);
	}
}
