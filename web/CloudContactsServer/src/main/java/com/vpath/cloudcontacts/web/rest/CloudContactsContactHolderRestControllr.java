package com.vpath.cloudcontacts.web.rest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vpath.cloudcontacts.model.Contact;
import com.vpath.cloudcontacts.model.ContactHolder;
import com.vpath.cloudcontacts.service.ContactHolderService;

@RestController
@RequestMapping("/contact/holder")
public class CloudContactsContactHolderRestControllr {

	@Autowired
	ContactHolderService service;
	
	@RequestMapping(value = "/create", method = RequestMethod.PUT)
	public void createContactHolder(@RequestParam(value = "name", required = false) String name){
		ContactHolder holder = new ContactHolder();
		holder.setName(name);
		holder.setContacts(new HashSet<Contact>());
		
		service.saveContactHolder(holder);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PATCH)
	public void updateContactHolder(@RequestParam(value = "id", required = true) int id,
			@RequestParam(value = "name", required = true) String name){
		ContactHolder holder = service.getContactHolderById(id);
		holder.setName(name);
		
		service.updateContactHolder(holder);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public void deleteContactHolder(@RequestParam(value = "id", required = true) int id){
		service.deleteContactHolderById(id);
	}

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public ResponseEntity<ContactHolder> getContactHolder(@PathVariable int id){
		ContactHolder holder = service.getContactHolderById(id);
		
		return new ResponseEntity<ContactHolder>(holder, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/get/all", method = RequestMethod.GET)
	public List<ResponseEntity<ContactHolder>> getAllContactHolders(){
		List<ResponseEntity<ContactHolder>> responseList = new ArrayList<ResponseEntity<ContactHolder>>();
		List<ContactHolder> holdersList = service.getAllContactHolders();
		
		for(ContactHolder holder: holdersList){
			responseList.add(new ResponseEntity<ContactHolder>(holder, HttpStatus.ACCEPTED));
		}
		
		return responseList;
	}

}
