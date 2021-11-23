package com.voleo.dao.user;

import java.util.Collection;

import com.voleo.dao.IDAO;
import com.voleo.entity.user.Contact;

public interface IContactDAO extends IDAO<Contact> {

	public Collection<Contact> getContactsByUserId(Long userId);

	public Collection<Contact> getContactbyLastname(Contact contact);

	public int getCountContactByUser(Long userId);
}
