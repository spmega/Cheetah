package com.vpath.cloudcontacts.model;

import javax.persistence.*;

@Entity
public class Contact{
    private int id;
    private String name;
    private ContactHolder contactHolder;

    public Contact() {

    }

    public Contact(String name) {
        this.name = name;
    }

    public Contact(String name, ContactHolder contactHolder) {
        this.name = name;
        this.contactHolder = contactHolder;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @ManyToOne
    @JoinColumn(name = "contact_holder_id")
    public ContactHolder getContactHolder() {
        return contactHolder;
    }

    public void setContactHolder(ContactHolder contactHolder) {
        this.contactHolder = contactHolder;
    }
}
