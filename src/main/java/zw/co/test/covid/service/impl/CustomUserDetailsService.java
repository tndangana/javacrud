package zw.co.test.covid.service.impl;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.test.covid.configuration.UserPrincipal;
import zw.co.test.covid.model.User;
import zw.co.test.covid.repository.UserRepsository;
import zw.co.test.covid.service.TokenService;

import javax.mail.MessagingException;
import java.io.IOException;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepsository userRepository;
    @Autowired
    private TokenService tokenService;

    @SneakyThrows
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail)
            throws UsernameNotFoundException {
        // Let people login with either username or email
        User user = userRepository.findByEmail(usernameOrEmail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail)
                );


        return UserPrincipal.create(user);
    }

    // This method is used by JWTAuthenticationFilter
    @Transactional
    public UserDetails loadUserById(String id) throws Exception {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id : " + id)
        );


        return UserPrincipal.create(user);
    }
}