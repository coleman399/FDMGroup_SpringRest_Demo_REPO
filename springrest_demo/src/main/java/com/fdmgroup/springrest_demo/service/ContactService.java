package com.fdmgroup.springrest_demo.service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Contact> retrieveContact(Long id) {
        return contactRepository.findById(id);
    }

    public Contact generateContact(Contact contact) {
        return contactRepository.save(contact);
    }

}
