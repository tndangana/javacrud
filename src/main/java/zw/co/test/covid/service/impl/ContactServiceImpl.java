package zw.co.test.covid.service.impl;

import org.springframework.stereotype.Service;
import zw.co.test.covid.model.Contact;
import zw.co.test.covid.repository.ContactRepository;
import zw.co.test.covid.service.ContactService;

import java.util.List;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {

    private ContactRepository contactRepository;
    @Override
    public Contact save(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public Optional<Contact> findOne(String id) {
        return Optional.ofNullable(contactRepository.findById(id).get());
    }

    @Override
    public Optional<List<Contact>> findAll() {
        return Optional.ofNullable(contactRepository.findAll());
    }

    @Override
    public void deleteById(String id) {
       contactRepository.deleteById(id);
    }

    @Override
    public void deleteByObject(Contact contact) {
       contactRepository.delete(contact);
    }
}
