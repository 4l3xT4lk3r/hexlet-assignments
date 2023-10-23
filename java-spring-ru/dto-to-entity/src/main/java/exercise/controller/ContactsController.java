package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import exercise.model.Contact;
import exercise.repository.ContactRepository;
import exercise.dto.ContactDTO;
import exercise.dto.ContactCreateDTO;

@RestController
@RequestMapping("/contacts")
public class ContactsController {

    @Autowired
    private ContactRepository contactRepository;

    // BEGIN
    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public ContactDTO create(@RequestBody ContactCreateDTO contactCreateDTO){
        Contact contact = contactRepository.save(getContact(contactCreateDTO));
        return getContactDTO(contact);

    }

    private Contact getContact(ContactCreateDTO contactCreateDTO){
        Contact contact = new Contact();
        contact.setPhone(contactCreateDTO.getPhone());
        contact.setFirstName(contactCreateDTO.getFirstName());
        contact.setLastName(contactCreateDTO.getLastName());
        return contact;
    }

    private ContactDTO getContactDTO(Contact contact){
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setId(contact.getId());
        contactDTO.setPhone(contact.getPhone());
        contactDTO.setFirstName(contact.getFirstName());
        contactDTO.setLastName(contact.getLastName());
        contactDTO.setCreatedAt(contact.getCreatedAt());
        contactDTO.setUpdatedAt(contact.getUpdatedAt());
        return contactDTO;
    }
    // END
}
