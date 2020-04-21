package zw.co.test.covid.service;

import zw.co.test.covid.model.Token;
import zw.co.test.covid.model.User;

import javax.mail.MessagingException;
import java.io.IOException;

public interface TokenService extends IService<Token> {


    Token generateToken(User user);
    Boolean existsByToken(String token);
    Token findByToken(String token);
    void resendToken(String email) throws IOException, MessagingException;




}
