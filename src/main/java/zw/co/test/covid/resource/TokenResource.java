package zw.co.test.covid.resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.test.covid.model.User;
import zw.co.test.covid.service.TokenService;
import zw.co.test.covid.service.UserService;

import java.util.Optional;

@RestController
@RequestMapping("api/token")
public class TokenResource {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserService userService;


    @PostMapping("/{token}")
    public ResponseEntity verifyToken(@PathVariable String token){

       Optional.ofNullable(tokenService.findByToken(token)).ifPresent(token1 -> {
           Optional<User> user = Optional.ofNullable(userService.findByEmail(token1.getEmail()));
           if(user.isPresent()){
              user.get().setVerified(true);
              userService.save(user.get());
           }
       });

       return ResponseEntity.ok("User has been verified");
    }


}
