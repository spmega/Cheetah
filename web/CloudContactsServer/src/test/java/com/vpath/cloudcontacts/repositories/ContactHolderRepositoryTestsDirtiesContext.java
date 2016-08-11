package com.vpath.cloudcontacts.repositories;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.vpath.cloudcontacts.CloudContactsServerApplication;
import com.vpath.cloudcontacts.model.Contact;
import com.vpath.cloudcontacts.model.ContactHolder;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
public class ContactHolderRepositoryTestsDirtiesContext {

	@Autowired
	private ContactHolderRepository repository;
	
	private Logger log = LoggerFactory.getLogger(CloudContactsServerApplication.class);
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void saveTest() {
		final ContactHolder categoryA = new ContactHolder("Category A");
        Set bookAs = new HashSet<Contact>();
        bookAs.add(new Contact("Book A1", categoryA));
        bookAs.add(new Contact("Book A2", categoryA));
        bookAs.add(new Contact("Book A3", categoryA));
        
        categoryA.setContacts(bookAs);
        log.debug("CategryA id is"+categoryA.getId());
        System.out.println("CategryA id is "+categoryA.getId());
        
        final ContactHolder categoryB = new ContactHolder("Category B");
        Set bookBs = new HashSet<Contact>();
    	bookBs.add(new Contact("Book B1", categoryB));
    	bookBs.add(new Contact("Book B2", categoryB));
    	bookBs.add(new Contact("Book B3", categoryB));
        
    	categoryB.setContacts(bookBs);
        log.debug("CategryB id is"+categoryB.getId());
        System.out.println("CategryB id is "+categoryB.getId());
        
        repository.save(categoryA);
        repository.save(categoryB);
	}
	
	@Test
	public void deleteTest() {
		ContactHolder categoryA = new ContactHolder();
        Set contacts = new HashSet<Contact>();
        Contact contact = new Contact();
        
        categoryA.setName("JH hhh");
        
        contact.setContactHolder(categoryA);
        contact.setName("Shashank Pachava");
        
        contacts.add(contact);
        
        categoryA.setContacts(contacts);
        log.debug("CategryA id is"+categoryA.getId());
        System.out.println("CategryA id is "+categoryA.getId());
        
        repository.save(categoryA);
        
        ContactHolder savedContactHolder = this.repository.findOne(categoryA.getId());
        
		Assert.assertNotNull(savedContactHolder);
		Assert.assertNotNull(savedContactHolder.getContacts());
		
		this.repository.delete(savedContactHolder);
		
		savedContactHolder = this.repository.findOne(categoryA.getId());
		
		Assert.assertNull(savedContactHolder);
		
	}
	
	@Test
	public void findByIdTest() {
		ContactHolder categoryA = new ContactHolder();
        Set contacts = new HashSet<Contact>();
        Contact contact = new Contact();
        
        categoryA.setName("JH hhh");
        
        contact.setContactHolder(categoryA);
        contact.setName("Shashank Pachava");
        
        contacts.add(contact);
        
        categoryA.setContacts(contacts);
        log.debug("CategryA id is"+categoryA.getId());
        System.out.println("CategryA id is "+categoryA.getId());
        
        repository.save(categoryA);
        
        ContactHolder savedContactHolder = this.repository.findOne(categoryA.getId());
        
		Assert.assertNotNull(savedContactHolder);
		Assert.assertNotNull(savedContactHolder.getContacts());
		
		Assert.assertTrue(savedContactHolder.getId() == categoryA.getId());
		
	}
	
	@Test
	public void findAll() {
		ContactHolder categoryA = new ContactHolder();
        Set contacts = new HashSet<Contact>();
        Contact contactA = new Contact();
        Contact contactB = new Contact();
        
        categoryA.setName("JH hhh");
        
        contactA.setContactHolder(categoryA);
        contactA.setName("Shashank Pachava");
        
        contactB.setContactHolder(categoryA);
        contactB.setName("Bag Pachava");
        
        contacts.add(contactA);
        contacts.add(contactB);
        
        categoryA.setContacts(contacts);
        
		List list = this.repository.findAll();
		Assert.assertNotNull(list);
		Assert.assertTrue(list.size() == 2);
	}
	
}
