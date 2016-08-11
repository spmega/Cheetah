package com.vpath.cloudcontacts.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.vpath.cloudcontacts.model.Contact;
import com.vpath.cloudcontacts.model.ContactHolder;
import com.vpath.cloudcontacts.repositories.ContactHolderRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContactHandlerServiceTest {
	
	@Autowired
	private ContactHolderRepository repository;
	
	@Autowired
	private ContactHolderService service;
	
	@Test
	public void saveTest(){
		ContactHolder holder = new ContactHolder();
        Set contacts = new HashSet<Contact>();
        Contact contact = new Contact();
        
        holder.setName("Shashank Pachava");
        
        contact.setContactHolder(holder);
        contact.setName("Nag Pachava");
        
        contacts.add(contact);
        
        holder.setContacts(contacts);
        
        repository.save(holder);
    }
	
	@Test
	public void testGetOne(){
		ContactHolder holder = this.repository.getOne(1);
		
		ContactHolder savedHolder = service.getContactHolderById(holder.getId());
		
		Assert.assertNotNull(savedHolder);
		Assert.assertTrue(holder.getId() == savedHolder.getId());
	}
	
	@Test
	public void testGetAll(){
		int numOfContactHolders = 3;
		
		//A single ContactHolder added
		ContactHolder holderA = new ContactHolder();
        Set contactsA = new HashSet<Contact>();
        
        holderA.setName("Nag Pachava");
        
        contactsA.add(new Contact("Shashank Pachava", holderA));
        contactsA.add(new Contact("Jyothi Pachava", holderA));
        
        holderA.setContacts(contactsA);
        
        //another ContactHolder added
        ContactHolder holderB = new ContactHolder();
        Set contactsB = new HashSet<Contact>();
        
        holderB.setName("Jyothi Pachava");
        
        contactsB.add(new Contact("Shashank Pachava", holderB));
        contactsB.add(new Contact("Nag Pachava", holderB));
        
        holderB.setContacts(contactsB);
        
        //save both
        this.repository.save(holderA);
        this.repository.save(holderB);
		
		List<ContactHolder> holderList = this.service.getAllContactHolders();
		
		for(ContactHolder holder:holderList)
			System.out.println("ContactHolder Name: " + holder.getName()
					+ "\nContactHolder Id: " + holder.getId());
		
		Assert.assertNotNull(holderList);
		Assert.assertSame(holderList.size(), numOfContactHolders);
	}
	
	@Test
	public void deleteTest(){
		ContactHolder holderA = new ContactHolder();
        Set contactsA = new HashSet<Contact>();
        
        holderA.setName("Lovely Pachava");
        
        contactsA.add(new Contact("Shashank Pachava", holderA));
        contactsA.add(new Contact("Jyothi Pachava", holderA));
        
        holderA.setContacts(contactsA);
        
        this.repository.save(holderA);
        
        ContactHolder savedHolder = this.repository.getOne(holderA.getId());
        
        Assert.assertNotNull(savedHolder);
        
        this.service.deleteContactHolder(holderA);
        ContactHolder deleteHolder = this.repository.findOne(holderA.getId());
        
        Assert.assertNull(deleteHolder);
    }
	
	@Test
	public void updateTest(){
		String updatedName = "John Cena";
		int id = 1;
		
		ContactHolder savedHolder = this.repository.findOne(id);
		System.out.println("ContactHolder before update Name: " + savedHolder.getName());
		savedHolder.setName(updatedName);
		System.out.println("ContactHolder after update Name: " + savedHolder.getName());
		
		service.updateContactHolder(id, updatedName);
		
		ContactHolder updatedHolder = this.repository.findOne(id);
		System.out.println("Saved ContactHolder after update Name: " + updatedHolder.getName());
		
		Assert.assertNotNull(savedHolder);
		Assert.assertNotNull(updatedHolder);
		Assert.assertTrue(savedHolder.getId() == updatedHolder.getId());
		Assert.assertFalse(savedHolder.getName().equals(updatedHolder.getName()));
	}
}
