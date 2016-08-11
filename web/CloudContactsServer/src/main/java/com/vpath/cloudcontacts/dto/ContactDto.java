package com.vpath.cloudcontacts.dto;

public class ContactDto{
	private int id;
    private String name;

    public ContactDto() {

    }
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ContactDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
