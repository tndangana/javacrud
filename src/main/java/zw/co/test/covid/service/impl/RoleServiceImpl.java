package zw.co.test.covid.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zw.co.test.covid.model.Role;
import zw.co.test.covid.repository.RoleRepository;
import zw.co.test.covid.service.RoleService;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Optional<Role> findOne(String id) {
        return Optional.ofNullable(roleRepository.findById(id).get());
    }

    @Override
    public Optional<List<Role>> findAll() {
        return Optional.ofNullable(roleRepository.findAll());
    }

    @Override
    public void deleteById(String id) {
       roleRepository.deleteById(id);
    }

    @Override
    public void deleteByObject(Role role) {
       roleRepository.delete(role);
    }

    @Override
    public Optional<Role> findByRoleName(String enumRole) {
        return Optional.ofNullable(roleRepository.findByRoleName(enumRole).get());
    }
}
