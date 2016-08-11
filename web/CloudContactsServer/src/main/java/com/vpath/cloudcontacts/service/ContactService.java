package com.vpath.cloudcontacts.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.vpath.cloudcontacts.model.Contact;

@Service
public interface ContactService {
	public void saveContact(int contactHolderId, Contact contact);
	public void deleteContact(int contactHolderId, Contact contact);
	public void deleteContactById(int contactHolderId, int contactId);
	public void updateContact(int contactHolderId, Contact contact);
	public Contact getContact(int contactHolderId, int contactId);
	public Set<Contact> getallContacts(int contactHolderId);
	
}
