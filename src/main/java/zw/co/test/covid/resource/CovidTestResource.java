package zw.co.test.covid.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.test.covid.exception.ResourceNotFoundException;
import zw.co.test.covid.model.Covidtest;
import zw.co.test.covid.model.Symptom;
import zw.co.test.covid.service.CovidTestService;
import zw.co.test.covid.service.PatientService;
import zw.co.test.covid.service.SymptomService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/covidtest")

public class CovidTestResource {
    @Autowired
    private CovidTestService covidTestService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private SymptomService symptomService;
    String patientId;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody Covidtest covidtest){

           List<Symptom> symptomList = new ArrayList<>();
           patientService.findOne(covidtest.getPatientId()).ifPresent((patient)->patientId = patient.getId());
           covidtest.getSymptoms().stream().forEach(symptom -> {
               if(symptomService.findOne(symptom.getId()).isPresent())
               {
                   symptomList.add(symptom);
               }
           });
           Covidtest covidtest1 = new Covidtest();
           covidtest1.setPatientId(patientId);
           covidtest1.setSymptoms(symptomList);

        return ResponseEntity.ok(covidTestService.save(covidtest1));

    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody Covidtest covidtest,@PathVariable String id){
        List<Symptom> symptomList = new ArrayList<>();
        patientService.findOne(covidtest.getPatientId()).ifPresent((patient)->patientId = patient.getId());
        Covidtest covidTest = new Covidtest();
        Optional.of(covidTestService.findOne(id).get()).ifPresent(covidtest1 -> {

           covidtest.getSymptoms().stream().forEach(symptom -> {

               if(symptomService.findOne(symptom.getId()).isPresent())
               {
                   symptomList.add(symptom);
               }

           });

           covidTest.setSymptoms(symptomList);
           covidTest.setPatientId(patientId);
       });

        return ResponseEntity.ok(covidTestService.save(covidTest));

    }
    @GetMapping("/{id}")
    public ResponseEntity findOne(@PathVariable String id) throws ResourceNotFoundException {
        Optional<Covidtest> covidtest = Optional.of(covidTestService.findOne(id).get());
        covidtest
                .orElseThrow(() -> new ResourceNotFoundException("Covid Test not found",null,null));
        return  ResponseEntity.ok(covidtest);
    }

    @GetMapping("/")
    public ResponseEntity findAll(){
         return  ResponseEntity.ok(covidTestService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id){
        Optional<Covidtest> covidtest = Optional.of(covidTestService.findOne(id).get());
        covidtest.ifPresent(covidtest1 -> covidTestService.deleteById(id));

        return ResponseEntity.ok(covidtest);
    }

}
