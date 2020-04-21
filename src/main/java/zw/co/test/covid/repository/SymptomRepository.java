package zw.co.test.covid.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import zw.co.test.covid.model.Symptom;

public interface SymptomRepository extends MongoRepository<Symptom,String> {
    Symptom findByName(String name);
}
