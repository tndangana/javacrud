package zw.co.test.covid.service.impl;

import org.springframework.stereotype.Service;
import zw.co.test.covid.model.Covidtest;
import zw.co.test.covid.repository.Covidtestrepository;
import zw.co.test.covid.service.CovidTestService;

import java.util.List;
import java.util.Optional;

@Service
public class CovidTestServiceImpl implements CovidTestService {

    private Covidtestrepository covidtestrepository;

    @Override
    public Covidtest save(Covidtest covidtest) {
        return covidtestrepository.save(covidtest);
    }

    @Override
    public Optional<Covidtest> findOne(String id) {
        return Optional.ofNullable(covidtestrepository.findById(id).get());
    }

    @Override
    public Optional<List<Covidtest>> findAll() {
        return Optional.ofNullable(covidtestrepository.findAll());
    }

    @Override
    public void deleteById(String id) {
        covidtestrepository.deleteById(id);
    }

    @Override
    public void deleteByObject(Covidtest covidtest) {
         covidtestrepository.delete(covidtest);
    }
}
