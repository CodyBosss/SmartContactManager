package com.smartsaver.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.smartsaver.dao.ContactRepository;
import com.smartsaver.dao.UserRepository;
import com.smartsaver.entities.Contact;
import com.smartsaver.entities.User;

@RestController
public class SearchController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ContactRepository contactRepository;

    @GetMapping("/search/{query}")
    public ResponseEntity<?> searchResult(@PathVariable("query") String query, Principal principal) {
        User user = this.userRepository.getUserByUserName(principal.getName());
        List<Contact> contacts = this.contactRepository.findContactByNameContainingAndUser(query,user);
        return ResponseEntity.ok(contacts);
    }
}
