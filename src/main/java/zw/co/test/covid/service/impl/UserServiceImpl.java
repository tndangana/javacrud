package zw.co.test.covid.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zw.co.test.covid.model.User;
import zw.co.test.covid.repository.UserRepsository;
import zw.co.test.covid.service.TokenService;
import zw.co.test.covid.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService  {

    @Autowired
    private UserRepsository userRepsository;

    @Override
    public User save(User user)
    {
        return userRepsository.save(user);
    }

    @Override
    public Optional<User> findOne(String id) {
        return Optional.ofNullable(userRepsository.findById(id).get());
    }

    @Override
    public Optional<List<User>> findAll() {
        return Optional.ofNullable(userRepsository.findAll());
    }

    @Override
    public void deleteById(String id) {
           userRepsository.deleteById(id);
    }

    @Override
    public void deleteByObject(User user) {
          userRepsository.delete(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepsository.findByEmail(email).get();
    }

    @Override
    public Boolean existsByEmail(String email) {

        return userRepsository.existsByEmail(email);
    }
}
