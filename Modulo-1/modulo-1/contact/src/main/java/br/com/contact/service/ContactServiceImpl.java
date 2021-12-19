package br.com.contact.service;

import br.com.contact.controller.request.ContactRequest;
import br.com.contact.controller.response.ContactResponse;
import br.com.contact.model.Contact;
import br.com.contact.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public void createContact(ContactRequest request) {
        this.contactRepository.save(new Contact().convertToEntity(request));
    }

    @Override
    public void removeContact(Long id) {
        this.contactRepository.deleteById(id);
    }

    @Override
    public ContactResponse getContactByName(String name) {
	    List<Contact> contatcs = this.contactRepository.findAll();
	    Contact c = contatcs.stream().filter(contact -> contact.getName().equals(name)).findAny().get();
	    return c.convertToContactResponse(c);
    }

    @Override
    public ContactResponse getByName(String name) {
        return new Contact().convertToContactResponse(this.contactRepository.findByName(name));
    }

    @Override
    public List<ContactResponse> getAllContact() {
        return this.contactRepository.findAll()
                .stream().map(contact -> contact.convertToContactResponse(contact))
                .collect(Collectors.toList());
    }

	@Override
	public ContactResponse updateContactById(Long idContact, ContactRequest requestContact) {
		Optional<Contact> contato = this.contactRepository.findById(idContact); 
		if(contato.isPresent()) {
			Contact c = new Contact().convertToEntity(requestContact);
			c.setId(idContact);
			this.contactRepository.save(c);
			return c.convertToContactResponse(c);
		};
		
		return null;
	}

}
