package zw.co.test.covid.resource;

import lombok.AllArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.test.covid.model.Symptom;
import zw.co.test.covid.service.SymptomService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/symptom")
@AllArgsConstructor
public class SymptomResource {


    private SymptomService symptomService;

    @PostMapping("/")
    public ResponseEntity create(@Valid Symptom symptom) throws Exception {

        Optional.of(symptomService.findByName(symptom.getName()))
                .orElseThrow(() -> new Exception("Symptom already exists"));
               symptom.setCreatedAt(LocalDate.now());
               symptom.setUpdatedAt(LocalDate.now());
          return ResponseEntity.status(201).body(symptomService.save(symptom));
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable String id, @RequestBody Symptom symptom) throws Exception {

      Symptom symptom1 =  Optional.of(symptomService.findOne(id).get())
                .orElseThrow(() -> new Exception("Symptom not found"));
        symptom1.setUpdatedAt(LocalDate.now());
            return ResponseEntity.status(201).body(symptomService.save(symptom1));



    }
    @GetMapping("/{id}")
    public  ResponseEntity findOne(@PathVariable String id){
        Optional<Symptom> symptom = Optional.of(symptomService.findOne(id).get());
        symptom.orElseThrow(() -> new ResourceNotFoundException("ijij"));
        return  ResponseEntity.ok(symptom);
    }

    @GetMapping("/")
    public ResponseEntity<List<Symptom>> findAll(){

        return new ResponseEntity<>(symptomService.findAll().get(), HttpStatus.OK);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable String id){
        symptomService.deleteById(id);
        return ResponseEntity.ok("s");
    }
}
