package br.com.contact.service;

import java.util.List;

import br.com.contact.controller.request.ContactRequest;
import br.com.contact.controller.response.ContactResponse;

public interface ContactService {

    public void createContact(ContactRequest request);
    public void removeContact(Long id);
    public ContactResponse getContactByName(String name);
    public ContactResponse getByName(String name);
    public List<ContactResponse> getAllContact();
    public ContactResponse updateContactById(Long idContact,ContactRequest requestContact);
}
