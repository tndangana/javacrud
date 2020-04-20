package zw.co.test.covid.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import zw.co.test.covid.model.User;

import java.util.Optional;

public interface UserRepsository extends MongoRepository<User,String> {

    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
}
