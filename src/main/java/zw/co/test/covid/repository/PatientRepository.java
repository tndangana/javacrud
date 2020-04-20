package zw.co.test.covid.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import zw.co.test.covid.model.Patient;

public interface PatientRepository extends MongoRepository<Patient,String> {
}
