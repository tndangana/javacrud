package zw.co.test.covid.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.test.covid.model.Contact;
import zw.co.test.covid.service.ContactService;
import zw.co.test.covid.service.CountryService;
import zw.co.test.covid.service.PatientService;

import javax.validation.Valid;
import java.util.Optional;


@RestController
@RequestMapping("api/contact")
public class ContactResource {

    @Autowired
    private ContactService contactService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private CountryService countryService;

    @PostMapping("/")
    public ResponseEntity create(@Valid @RequestBody Contact contact) {

        String patientId = patientService.findOne(contact.getPatientId()).get().getId();
        String countryId = countryService.findOne(contact.getCountryId()).get().getId();
        contact.setCountryId(countryId);
        contact.setPatientId(patientId);
        return ResponseEntity.status(201).body(contactService.save(contact));

    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable String id) {
        Optional<Contact> contact = Optional.of(contactService.findOne(id).get());
        if (contact.isPresent()) {

            String patient = patientService.findOne(contact.get().getPatientId()).get().getId();
            String country = countryService.findOne(contact.get().getPatientId()).get().getId();
            contact.get().setPatientId(patient);
            contact.get().setCountryId(country);
            return ResponseEntity.status(200).body(contactService.save(contact.get()));
        }
        return ResponseEntity.status(404).body("Value is missing");

    }

    @GetMapping("/")
    public ResponseEntity findAll() {
        return ResponseEntity.ok(contactService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity findOne(@PathVariable String id) throws Exception {

        Optional<Contact> contact = Optional.of(contactService.findOne(id).get());
        contact.orElseThrow(() -> new Exception("Id not found"));

        return ResponseEntity.status(200).body(contact);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id) throws Exception {
        Contact contact = Optional.of(contactService.findOne(id)).get().orElseThrow(() -> new Exception("Cant find id "));
        contactService.deleteById(contact.getId());

        return ResponseEntity.status(200).body("Deleted succesfully");
    }
}
