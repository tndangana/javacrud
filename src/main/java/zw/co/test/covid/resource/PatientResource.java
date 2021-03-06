package zw.co.test.covid.resource;

import lombok.AllArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.test.covid.exception.ApiRequestException;
import zw.co.test.covid.model.Patient;
import zw.co.test.covid.service.PatientService;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/patient")
@AllArgsConstructor
public class PatientResource {

    private PatientService patientService;

    @PostMapping("/")
    public ResponseEntity<Patient> create(@Valid @RequestBody Patient patient) throws ResourceNotFoundException {

        if(patient.equals(null) || patient.equals("")){
          throw  new ResourceNotFoundException("Patient should not be empty");
        }

         return new ResponseEntity<>(patientService.save(patient),HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody Patient patient, @PathVariable String id) throws ResourceNotFoundException {
        Optional<Patient> optionalPatient = Optional.of(patientService.findOne(id).get());

        optionalPatient.orElseThrow(() -> new ResourceNotFoundException("Patient should not be empty") );
        optionalPatient.get().setDateOfBirth(patient.getDateOfBirth());
        optionalPatient.get().setFirstName(patient.getFirstName());
//        optionalPatient.get().setGender(patient.getGender());
        optionalPatient.get().setLastName(patient.getLastName());

        return  ResponseEntity.ok(patientService.save(optionalPatient.get()));

    }
    @GetMapping("/{id}")
    public ResponseEntity findOne(@PathVariable String id) {
        try{
            Optional<Patient> patient = Optional.of(patientService.findOne(id).get());
            if(!patient.isPresent()){
                throw  new ApiRequestException("Testing 123");
            }
            return ResponseEntity.ok().body(patient);

        }catch (ApiRequestException e){
            throw  new ApiRequestException("Testing 123");
        }


    }

    @GetMapping("/")
    public ResponseEntity findAll(){
        return ResponseEntity.ok(patientService.findAll().get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id){
        patientService.deleteById(id);

        return ResponseEntity.ok().build();
    }



}
