package zw.co.test.covid.service;

import java.util.List;
import java.util.Optional;

public interface IService<T> {

    T save(T t);
    Optional<T> findOne(String id);
    Optional<List<T>> findAll();
    void deleteById(String id);
    void deleteByObject(T t);

    default T findByEmail(String email){
       T t = null;
       return t;
    }


}
