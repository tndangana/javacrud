package zw.co.test.covid.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import zw.co.test.covid.model.Contact;

public interface ContactRepository extends MongoRepository<Contact,String> {


}
