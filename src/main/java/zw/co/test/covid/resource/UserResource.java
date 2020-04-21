package zw.co.test.covid.resource;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.EmitUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import zw.co.test.covid.configuration.JwtTokenProvider;
import zw.co.test.covid.model.Role;
import zw.co.test.covid.model.Token;
import zw.co.test.covid.model.User;
import zw.co.test.covid.payloads.ApiResponse;
import zw.co.test.covid.payloads.JwtAuthenticationResponse;
import zw.co.test.covid.payloads.LoginRequest;
import zw.co.test.covid.payloads.SignUpRequest;
import zw.co.test.covid.repository.RoleRepository;
import zw.co.test.covid.repository.UserRepsository;
import zw.co.test.covid.service.RoleService;
import zw.co.test.covid.service.TokenService;
import zw.co.test.covid.service.UserService;
import zw.co.test.covid.util.EmailUtilService;
import zw.co.test.covid.util.EmailUtils;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
public class UserResource {



    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService ;
    @Autowired
    private TokenService tokenService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;
    @Autowired
    private EmailUtilService emailUtilService;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) throws IOException, MessagingException {


        Optional<User> user = Optional.of(Optional.ofNullable(userService.findByEmail(loginRequest.getEmail())).get());
        System.out.println("hghhhghgh"+user);


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
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) throws IOException, MessagingException {

        if(userService.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User(signUpRequest.getFirstName(), signUpRequest.getLastName(),
                signUpRequest.getEmail(), signUpRequest.getMobileNumber(),
                signUpRequest.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedAt(LocalDate.now());
        user.setUpdatedAt(LocalDate.now());

        Role userRole = roleService.findByRoleName("ROLE_USER").get();
//                .orElseThrow(() -> new AppException("User Role not set."));
        user.setRoles(Collections.singleton(userRole));
        User result = userService.save(user);
        Token token =  tokenService.generateToken(result);
        emailUtilService.verifyTokenEmail(token);

        return ResponseEntity.status(201).body(new ApiResponse(true, "Message has been sent"));


    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findUserById(@PathVariable String id) throws Exception {

        User user = Optional.of(userService.findOne(id).get())
                .orElseThrow(() -> new Exception("User not found"));

        return ResponseEntity.status(201).body(user);

    }
    public ResponseEntity<?> findAll(){
       return new ResponseEntity<>(userService.findAll().get(),HttpStatus.OK);
    }


}