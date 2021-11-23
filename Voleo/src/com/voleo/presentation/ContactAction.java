package com.voleo.presentation;

import java.util.Collection;

import org.apache.commons.validator.routines.LongValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.voleo.dto.document.TagCount;
import com.voleo.entity.document.Document;
import com.voleo.entity.document.DocumentType;
import com.voleo.entity.user.Contact;
import com.voleo.entity.user.User;
import com.voleo.service.IContactService;
import com.voleo.service.IDocumentService;
import com.voleo.service.ITagService;
import com.voleo.service.IUserService;

@Component
public class ContactAction extends AbstractAction {
	
	@Autowired
	private IContactService contactService;

	@Autowired
	private IUserService userService;
	
	@Autowired
	private ITagService tagService;
	
	@Autowired
	private IDocumentService documentService;
	
	private Contact contact;
	private User user;
	private String contactId;
	private Collection<Contact> contacts;
	private String  contactIdDelete;
	private Collection<TagCount> tagsCount;
	private Collection<Document> nlastText; 

	
	// preparation du formulaire d'ajout
	public String addContactForm() {
		contact = new Contact();
		return SUCCESS;
	}
	
	public String updateContactForm() {
		// Recuperation du parametre contactId passé dans la requete get.
		Long id = LongValidator.getInstance().validate(contactId);
		if (id != null) {
			contact = contactService.getContactById(id);
		}
		return SUCCESS;
	}
	
	public String updateUserForm(){
		Long userId = getUserIdInSession();
		user = userService.getUserById(userId);
		return refreshContacts();
	}
	
	public String updateUser(){
		userService.update(user);
		return SUCCESS;
	}
	
	public String updateContact(){
		Long userId = getUserIdInSession();
		contactService.updateContact(contact, userId);
		return refreshContacts();
	}
	
	public String getContactIdDelete() {
		return contactIdDelete;
	}

	public void setContactIdDelete(String contactIdDelete) {
		this.contactIdDelete = contactIdDelete;
	}

	public String deleteContact(){
		Long id = LongValidator.getInstance().validate(contactIdDelete);
		if (id != null) {
			 contactService.removeContact(id);
			 return SUCCESS;
		}
		else{
			return "failed";
		}
	}
	

	public String addContact() {
		//Récupération de l'Id de la session
		Long userId = getUserIdInSession();
		contactService.addContact(contact, userId);
		return refreshContacts();
	}
	
	
	public String addUserForm(){
	    contact = new Contact();
		user = new User();
		return SUCCESS;
	}
	
	public String addUser(){
		
		userService.addUser(user);

		contact.setUser(user);		
		contactService.addnewContact(contact);
		
		user = new User();
		contact = new Contact();
		return refreshContacts();
	}

	
	public String refreshContacts() {
		contacts = contactService.getContacts(getUserIdInSession());
		tagsCount = tagService.getTagNamesAndDocumentsCount();
		nlastText = documentService.getNLastDocument(DocumentType.TEXT, 5);

		return SUCCESS;
	}

	
	
	

	/*--------------Getters et Setters-------------------*/
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	public Collection<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(Collection<Contact> contacts) {
		this.contacts = contacts;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public Collection<TagCount> getTagsCount() {
		return tagsCount;
	}

	public void setTagsCount(Collection<TagCount> tagsCount) {
		this.tagsCount = tagsCount;
	}

	public Collection<Document> getNlastText() {
		return nlastText;
	}

	public void setNlastText(Collection<Document> nlastText) {
		this.nlastText = nlastText;
	}

}
