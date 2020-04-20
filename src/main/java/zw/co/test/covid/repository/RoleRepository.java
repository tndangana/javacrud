package zw.co.test.covid.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import zw.co.test.covid.model.EnumRole;
import zw.co.test.covid.model.Role;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role,String> {
    Optional<Role>  findByRoleName(String enumRole);
}
