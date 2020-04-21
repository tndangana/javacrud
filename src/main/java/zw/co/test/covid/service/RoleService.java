package zw.co.test.covid.service;

import zw.co.test.covid.model.Role;

import java.util.Optional;

public interface RoleService extends IService<Role> {
    Optional<Role> findByRoleName(String enumRole);
}
