package zw.co.test.covid.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface IResource<T> {


    ResponseEntity<T> create(@Valid @RequestBody  T t);
    ResponseEntity<T> update(@PathVariable String id, @Valid @RequestBody T t);
    ResponseEntity<List<T>> findAll();
    ResponseEntity<T> findById(@PathVariable  String id);
    ResponseEntity<?> deleteById(@PathVariable String id);


}
