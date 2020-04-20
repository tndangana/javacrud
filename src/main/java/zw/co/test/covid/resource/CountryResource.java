package zw.co.test.covid.resource;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.test.covid.exception.ResourceNotFoundException;
import zw.co.test.covid.model.Country;
import zw.co.test.covid.service.CountryService;

import java.util.Optional;

@RestController
@RequestMapping("api/country")
@AllArgsConstructor
public class CountryResource {

    private CountryService countryService;


    @PostMapping("/")
    public ResponseEntity create(@RequestBody Country country) {

        return ResponseEntity
                .status(201)
                .body(countryService.save(country));

    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody Country country, @PathVariable String id) {
        Optional<Country> optionalCountry = Optional.of(countryService.findOne(id).get());
        if (!optionalCountry.isPresent()) {

        }
        optionalCountry.get().setName(country.getName());

        return ResponseEntity.ok(countryService.save(optionalCountry.get()));
    }

    @GetMapping("/")
    public ResponseEntity findAll() {
        return ResponseEntity.ok(countryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity findOne(@PathVariable String id){
        Country country = countryService.findOne(id)
                .orElseThrow(()-> new ResourceNotFoundException("Country not found",null,null));
           return ResponseEntity.ok(country);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id){
        countryService.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
