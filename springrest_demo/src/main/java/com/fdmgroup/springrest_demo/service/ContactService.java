package com.fdmgroup.springrest_demo.service;

import java.util.List;

import com.fdmgroup.springrest_demo.exception.ContactNotFoundException;
import com.fdmgroup.springrest_demo.model.Contact;
import com.fdmgroup.springrest_demo.repository.ContactRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {
    private ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<Contact> retrieveContacts() {
        return contactRepository.findAll();
    }

    public Contact retrieveContact(Long id) throws ContactNotFoundException {
        contactRepository.findById(id).orElseThrow(ContactNotFoundException::new);
        return contactRepository.findById(id).get();
    }

    public Contact generateContact(Contact contact) {
        return contactRepository.save(contact);
    }

    public Contact amendContact(Long id, Contact contact) {
        Contact oldContact = contactRepository.findById(id).get();
        oldContact.setFirstName(contact.getFirstName());
        oldContact.setMiddleName(contact.getMiddleName());
        oldContact.setLastName(contact.getLastName());
        oldContact.setPhoneNumber(contact.getPhoneNumber());
        contactRepository.save(oldContact);
        return oldContact;
    }

    public void removeContact(Long id) {
        Contact contact = contactRepository.findById(id).get();
        contactRepository.delete(contact);
    }

}
