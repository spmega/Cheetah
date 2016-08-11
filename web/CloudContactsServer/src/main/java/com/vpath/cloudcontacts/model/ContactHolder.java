package com.vpath.cloudcontacts.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "contact_holder")
public class ContactHolder {
    private int id;
    private String name;
    private Set<Contact> contacts;

    public ContactHolder(){

    }

    public ContactHolder(String name) {
        this.name = name;
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

    @OneToMany(mappedBy = "contactHolder", cascade = CascadeType.ALL)
    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }

}
