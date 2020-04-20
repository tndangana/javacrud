package zw.co.test.covid.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zw.co.test.covid.model.Patient;
import zw.co.test.covid.repository.PatientRepository;
import zw.co.test.covid.service.PatientService;

import java.util.List;
import java.util.Optional;


@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;


    @Override
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Optional<Patient> findOne(String id) {
        return Optional.ofNullable(patientRepository.findById(id).get());
    }

    @Override
    public Optional<List<Patient>> findAll() {
        return Optional.ofNullable(patientRepository.findAll());
    }

    @Override
    public void deleteById(String id) {
             patientRepository.deleteById(id);
    }

    @Override
    public void deleteByObject(Patient patient) {
        patientRepository.delete(patient);
    }
}
