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
public class ContactHolderRepositoryTests {

	@Autowired
	private ContactHolderRepository repository;
	
	private Logger log = LoggerFactory.getLogger(CloudContactsServerApplication.class);
	
	@Test
	public void contextLoads() {
	}
	
	@Before
	public void before() {
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
	}
	
	@Test
	public void saveTest(){
		ContactHolder contactHolder = new ContactHolder();
		contactHolder.setName("John Cena");
		
		Contact contact = new Contact();
		contact.setContactHolder(contactHolder);
		contact.setName("Shashaniik Pachava");
		
		Set<Contact> contacts = new HashSet<Contact>();
		contacts.add(contact);
		
		contactHolder.setContacts(contacts);
		
		this.repository.save(contactHolder);
		
		ContactHolder savedContactHolder = repository.findOne(contactHolder.getId());
		Assert.assertNotNull(savedContactHolder);
		Assert.assertSame(savedContactHolder.getName(), contactHolder.getName());
		Assert.assertSame(savedContactHolder.getId(), contactHolder.getId());
	}
	
	@Test
	public void deleteTest() {
		ContactHolder savedContactHolder = repository.findOne(1);
        
		Assert.assertNotNull(savedContactHolder);
		Assert.assertNotNull(savedContactHolder.getContacts());
		
		this.repository.delete(savedContactHolder);
		
		savedContactHolder = this.repository.findOne(savedContactHolder.getId());
		
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
		Assert.assertTrue(list.size() == 3);
	}
	
}
