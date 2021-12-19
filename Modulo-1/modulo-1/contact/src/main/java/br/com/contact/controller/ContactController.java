package br.com.contact.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import antlr.collections.List;
import br.com.contact.controller.request.ContactRequest;
import br.com.contact.controller.response.ContactResponse;
import br.com.contact.service.ContactService;

@RestController
@RequestMapping("/api/V1/contacts")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ContactResponse> get() {
        return this.contactService.getAllContact();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createContact(@RequestBody ContactRequest request) {
        this.contactService.createContact(request);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteContact(@RequestParam Long id) {
        this.contactService.removeContact(id);
    }

    @GetMapping("/find-by-name")
    @ResponseStatus(HttpStatus.OK)
    public ContactResponse getContactByName(@RequestParam String name) {
       return this.contactService.getByName(name);
    }
    
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateContact(@RequestBody ContactRequest request, @PathVariable long idContact) {
        this.contactService.updateContactById(idContact, request);
    }
}