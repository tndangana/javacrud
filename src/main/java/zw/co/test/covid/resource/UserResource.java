package zw.co.test.covid.resource;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import zw.co.test.covid.configuration.JwtTokenProvider;
import zw.co.test.covid.exception.AppException;
import zw.co.test.covid.model.EnumRole;
import zw.co.test.covid.model.Role;
import zw.co.test.covid.model.User;
import zw.co.test.covid.payloads.ApiResponse;
import zw.co.test.covid.payloads.JwtAuthenticationResponse;
import zw.co.test.covid.payloads.LoginRequest;
import zw.co.test.covid.payloads.SignUpRequest;
import zw.co.test.covid.repository.RoleRepository;
import zw.co.test.covid.repository.UserRepsository;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Set;

@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
public class UserResource {



    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepsository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));

    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest){

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User(signUpRequest.getFirstName(), signUpRequest.getLastName(),
                signUpRequest.getEmail(), signUpRequest.getMobileNumber(),
                signUpRequest.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByRoleName("ROLE_USER")
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

        User result = userRepository.save(user);

        return ResponseEntity.status(201).body(new ApiResponse(true, "User registered successfully"));


    }

}