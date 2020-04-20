package zw.co.test.covid.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import zw.co.test.covid.model.Country;

public interface CountryRepository extends MongoRepository<Country,String> {
}
