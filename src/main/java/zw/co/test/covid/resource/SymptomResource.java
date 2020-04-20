package zw.co.test.covid.resource;

import lombok.AllArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.test.covid.model.Symptom;
import zw.co.test.covid.service.SymptomService;
import java.util.Optional;

@RestController
@RequestMapping("api/symptom")
@AllArgsConstructor
public class SymptomResource {


    private SymptomService symptomService;

//    @PostMapping("/")
//    public ResponseEntity create(){
//
//    }
//    @PutMapping("/{id}")
//    public ResponseEntity update(@PathVariable String id, @RequestBody Symptom symptom){
//
//    }
    @GetMapping("/{id}")
    public  ResponseEntity findOne(@PathVariable String id){
        Optional<Symptom> symptom = Optional.of(symptomService.findOne(id).get());
        symptom.orElseThrow(() -> new ResourceNotFoundException("ijij"));
        return  ResponseEntity.ok(symptom);
    }

    @GetMapping("/")
    public ResponseEntity findAll(){

        return ResponseEntity.ok(symptomService.findAll());

    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable String id){
        symptomService.deleteById(id);
        return ResponseEntity.ok("s");
    }
}
