package com.vpath.cloudcontacts.dto;

import java.util.Set;

public class ContactHolderDto {
	private int id;
    private String name;
    private Set<ContactDto> contacts;

    public ContactHolderDto(){

    }

    public ContactHolderDto(String name) {
        this.name = name;
    }
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ContactDto> getContacts() {
        return contacts;
    }

    public void setContacts(Set<ContactDto> contacts) {
        this.contacts = contacts;
    }

}
