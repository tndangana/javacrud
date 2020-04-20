package zw.co.test.covid.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zw.co.test.covid.model.Country;
import zw.co.test.covid.repository.CountryRepository;
import zw.co.test.covid.service.CountryService;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    @Autowired
    private CountryRepository countryRepository;

    @Override
    public Country save(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public Optional<Country> findOne(String id) {
        return Optional.ofNullable(countryRepository.findById(id).get());
    }

    @Override
    public Optional<List<Country>> findAll() {
        return Optional.ofNullable(countryRepository.findAll());
    }

    @Override
    public void deleteById(String id) {
         countryRepository.deleteById(id);
    }

    @Override
    public void deleteByObject(Country country) {
              countryRepository.delete(country);
    }
}
