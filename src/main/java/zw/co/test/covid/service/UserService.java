package zw.co.test.covid.service;

import zw.co.test.covid.model.User;

public interface UserService extends IService<User> {

    Boolean existsByEmail(String email);
}
