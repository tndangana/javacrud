package zw.co.test.covid.service;

import zw.co.test.covid.model.Symptom;

public interface SymptomService extends IService<Symptom> {

    Symptom findByName(String name);
}
