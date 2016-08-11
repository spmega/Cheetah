package com.vpath.cloudcontacts.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vpath.cloudcontacts.model.Contact;
import com.vpath.cloudcontacts.model.ContactHolder;
import com.vpath.cloudcontacts.repositories.ContactHolderRepository;

@Component
public class ContactService implements com.vpath.cloudcontacts.service.ContactService {

	@Autowired
	private ContactHolderRepository repository;
	
	@Override
	public void saveContact(int contactHolderId, Contact contact) {
		// TODO Auto-generated method stub
		ContactHolder holder = this.repository.getOne(contactHolderId);
		Set<Contact> contacts = holder.getContacts();
		
		contacts.add(contact);
		
		this.repository.save(holder);
	}

	@Override
	public void deleteContact(int contactHolderId, Contact contact) {
		// TODO Auto-generated method stub
		ContactHolder holder = this.repository.getOne(contactHolderId);
		Set<Contact> contacts = holder.getContacts();
		
		contacts.remove(contact);
		
		this.repository.save(holder);
	}

	@Override
	public void deleteContactById(int contactHolderId, int contactId) {
		// TODO Auto-generated method stub
		ContactHolder holder = this.repository.getOne(contactHolderId);
		Set<Contact> contacts = holder.getContacts();
				
		for(Contact contact: contacts){
			if(contact.getId() == contactId)
				contacts.remove(contact);
		}
		
		this.repository.save(holder);
	}

	@Override
	public void updateContact(int contactHolderId, Contact contact) {
		// TODO Auto-generated method stub
		ContactHolder holder = this.repository.getOne(contactHolderId);
		Set<Contact> contacts = holder.getContacts();
		
		for(Contact contactInContacts: contacts){
			if(contactInContacts.getId() == contact.getId())
				contacts.remove(contactInContacts);
		}
		
		contacts.add(contact);
		
		this.repository.save(holder);
	}

	@Override
	public Contact getContact(int contactHolderId, int contactId) {
		// TODO Auto-generated method stub
		ContactHolder holder = this.repository.getOne(contactHolderId);
		Set<Contact> contacts = holder.getContacts();
		
		for(Contact contactInContacts: contacts){
			if(contactInContacts.getId() == contactId)
				return contactInContacts;
		}
		
		return null;

	}

	@Override
	public Set<Contact> getallContacts(int contactHolderId) {
		// TODO Auto-generated method stub
		ContactHolder holder = this.repository.getOne(contactHolderId);
		Set<Contact> contacts = holder.getContacts();
		
		return contacts;
	}

}
