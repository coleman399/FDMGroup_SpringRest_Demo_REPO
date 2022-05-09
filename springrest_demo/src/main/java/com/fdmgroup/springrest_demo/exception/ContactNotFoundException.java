package com.fdmgroup.springrest_demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ContactNotFoundException extends Exception {
    public ContactNotFoundException() {
        super("Contact Not Found!");
    }

}
