package zw.co.test.covid.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import zw.co.test.covid.model.Token;

import java.util.Optional;

public interface TokenRepository extends MongoRepository<Token,String> {

    Boolean existsByToken(String token);
    Optional<Token> findByToken(String token);
    Optional<Token> findByEmail(String email);

}
