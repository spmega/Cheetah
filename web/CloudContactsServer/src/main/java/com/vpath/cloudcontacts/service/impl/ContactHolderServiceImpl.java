package com.vpath.cloudcontacts.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vpath.cloudcontacts.model.ContactHolder;
import com.vpath.cloudcontacts.repositories.ContactHolderRepository;
import com.vpath.cloudcontacts.service.ContactHolderService;

@Component
public class ContactHolderServiceImpl implements ContactHolderService {

	@Autowired
	private ContactHolderRepository repository;
	
	@Override
	public void saveContactHolder(ContactHolder contactHolder) {
		// TODO Auto-generated method stub
		this.repository.save(contactHolder);
	}

	@Override
	public void deleteContactHolder(ContactHolder contactHolder) {
		// TODO Auto-generated method stub
		this.repository.delete(contactHolder);
	}

	@Override
	public void deleteContactHolderById(int contactHolderId) {
		// TODO Auto-generated method stub
		this.repository.delete(contactHolderId);
	}

	@Override
	@Transactional
	public void updateContactHolder(int id, String name){
		// TODO Auto-generated method stub
		ContactHolder holder = this.repository.getOne(id);
		holder.setName(name);
		this.repository.flush();
	}

	@Override
	public ContactHolder getContactHolderById(int contactHolderId) {
		// TODO Auto-generated method stub
		return this.repository.getOne(contactHolderId);
	}

	@Override
	public List<ContactHolder> getAllContactHolders() {
		// TODO Auto-generated method stub
		return this.repository.findAll();
	}

}
