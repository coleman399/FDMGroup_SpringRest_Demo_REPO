package com.fdmgroup.springrest_demo.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.fdmgroup.springrest_demo.model.Contact;
import com.fdmgroup.springrest_demo.service.ContactService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/contacts")
public class ContactController {
    private ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public List<Contact> getContacts() {
        return contactService.retrieveContacts();
    }

    @GetMapping("/{id}")
    public Optional<Contact> getContact(@PathVariable("id") Long id) {
        return contactService.retrieveContact(id);
    }

    @PostMapping
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact) {
        Contact createContact = contactService.generateContact(contact);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(createContact.getContactId()).toUri();

        return ResponseEntity.created(location).build();
    }

}
