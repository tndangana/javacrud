package zw.co.test.covid.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zw.co.test.covid.model.Symptom;
import zw.co.test.covid.repository.SymptomRepository;
import zw.co.test.covid.service.SymptomService;

import java.util.List;
import java.util.Optional;

@Service
public class SymptomServiceImpl implements SymptomService {


    @Autowired
    private SymptomRepository symptomRepository;

    @Override
    public Symptom save(Symptom symptom) {
        return symptomRepository.save(symptom);
    }

    @Override
    public Optional<Symptom> findOne(String id) {
        return Optional.ofNullable(symptomRepository.findById(id).get());
    }

    @Override
    public Optional<List<Symptom>> findAll() {
        return Optional.ofNullable(symptomRepository.findAll());
    }

    @Override
    public void deleteById(String id) {
                 symptomRepository.deleteById(id);
    }

    @Override
    public void deleteByObject(Symptom symptom) {
         symptomRepository.delete(symptom);
    }

    @Override
    public Symptom findByName(String name) {
        return symptomRepository.findByName(name);
    }
}
