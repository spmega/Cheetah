package com.vpath.cloudcontacts.repositories;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.vpath.cloudcontacts.CloudContactsServerApplication;
import com.vpath.cloudcontacts.model.Contact;
import com.vpath.cloudcontacts.model.ContactHolder;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContactHolderCustomSelectorsRepositoryTest {

	@Autowired
	private ContactHolderRepository repository;
	
	private Logger log = LoggerFactory.getLogger(CloudContactsServerApplication.class);
	
	@Test
	public void contextLoads() {
	}
		
	@Test
	public void findByIdTest() {
		ContactHolder categoryA = new ContactHolder();
        Set contacts = new HashSet<Contact>();
        Contact contact = new Contact();
        String name = "Shashank Pachava";
        
        categoryA.setName(name);
        
        contact.setContactHolder(categoryA);
        contact.setName("Shashank Pachava");
        
        contacts.add(contact);
        
        categoryA.setContacts(contacts);
        log.debug("CategryA id is"+categoryA.getId());
        System.out.println("CategryA id is "+categoryA.getId());
        
        repository.save(categoryA);
        
        ContactHolder savedContactHolder = this.repository.getByName(name);
        
		Assert.assertNotNull(savedContactHolder);
		Assert.assertNotNull(savedContactHolder.getContacts());
		
		Assert.assertTrue(savedContactHolder.getId() == categoryA.getId());
		
	}
		
}
