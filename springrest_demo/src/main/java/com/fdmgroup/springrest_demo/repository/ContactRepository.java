package com.fdmgroup.springrest_demo.repository;

import com.fdmgroup.springrest_demo.model.Contact;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {

}
