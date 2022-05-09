package com.fdmgroup.springrest_demo.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.fdmgroup.springrest_demo.exception.ContactNotFoundException;
import com.fdmgroup.springrest_demo.model.Contact;
import com.fdmgroup.springrest_demo.service.ContactService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

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
    public Contact getContact(@PathVariable("id") Long id) throws ContactNotFoundException {
        return contactService.retrieveContact(id);
    }

    @PostMapping
    public ResponseEntity<Contact> createContact(@Valid @RequestBody Contact contact) {
        Contact createContact = contactService.generateContact(contact);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(createContact.getContactId()).toUri();
        return ResponseEntity.created(location).body(createContact);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable("id") Long id, @Valid @RequestBody Contact contact)
            throws ContactNotFoundException {
        Contact updatedContact = contactService.amendContact(id, contact);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(updatedContact.getContactId()).toUri();
        return ResponseEntity.created(location).body(updatedContact);
    }

    @Operation(summary = "Deletes Contact with the specified id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contact successfully deleted.", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE),
                    @Content(mediaType = MediaType.APPLICATION_XML_VALUE) }) })
    @DeleteMapping("/{id}")
    public void deleteContact(@PathVariable("id") Long id) throws ContactNotFoundException {
        contactService.removeContact(id);
    }
}
