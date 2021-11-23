package com.voleo.service;

import java.util.Collection;

import com.voleo.entity.user.Contact;
import com.voleo.entity.user.ContactGroup;
import com.voleo.entity.user.User;

public interface IContactService {
	public Collection<ContactGroup> getContactGroupsByUserId(Long userId);
	
	public Collection<Contact> getContactsWithoutGroup(Long userId);

	public void addContact(Contact newContact, Long userId);
	
	public Collection<Contact> getContacts(Long userId);

	public void updateContact(Contact contact, Long userId);

	public Collection<Contact> getContactbyLastname(Contact contact);

	public void addnewContact(Contact contact);

	public Contact getContactById(Long id);

	public void removeContact(Long id);
	
	public int getCountContactByUser(Long userId);
}
