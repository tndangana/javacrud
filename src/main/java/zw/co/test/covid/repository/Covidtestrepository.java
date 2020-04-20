package zw.co.test.covid.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import zw.co.test.covid.model.Covidtest;

public interface Covidtestrepository extends MongoRepository<Covidtest,String> {
}
