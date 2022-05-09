package com.fdmgroup.springrest_demo.exception;

public class ContactNotFoundException extends Exception {
    public ContactNotFoundException() {
        super("Contact Not Found!");
    }

}
