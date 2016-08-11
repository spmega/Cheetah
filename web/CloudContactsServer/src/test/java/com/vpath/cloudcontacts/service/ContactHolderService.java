package com.vpath.cloudcontacts.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vpath.cloudcontacts.model.ContactHolder;

@Service
public interface ContactHolderService {
	public void saveContactHolder(ContactHolder contactHolder);
	public void deleteContactHolder(ContactHolder contactHolder);
	public void deleteContactHolderById(int contactHolderId);
	public void updateContactHolder(ContactHolder contactHolder);
	public ContactHolder getContactHolderById(int contactHolderId);
	public List<ContactHolder> getAllContactHolders();
}
