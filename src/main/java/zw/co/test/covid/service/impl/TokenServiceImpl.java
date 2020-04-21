package zw.co.test.covid.service.impl;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zw.co.test.covid.model.Token;
import zw.co.test.covid.model.User;
import zw.co.test.covid.repository.TokenRepository;
import zw.co.test.covid.repository.UserRepsository;
import zw.co.test.covid.service.TokenService;
import zw.co.test.covid.util.EmailUtilService;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private EmailUtilService emailUtilService;
    @Autowired
    private UserRepsository userRepsository;

    @Override
    public Boolean existsByToken(String token) {
        return tokenRepository.existsByToken(token);
    }

    @Override
    public Token findByToken(String token) {
        return tokenRepository.findByToken(token).get();
    }

    @Override
    public void resendToken(String email) throws IOException, MessagingException {
        Optional<Token> token = Optional.of(tokenRepository.findByEmail(email).get());
        if(token.isPresent()){
            emailUtilService.verifyTokenEmail(token.get());
        }
        else if(!token.isPresent()){

                userRepsository.findByEmail(email).ifPresent(user1 -> {
                token(user1);
                    try {
                        emailUtilService.verifyTokenEmail(tokenRepository.save(token.get()));
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });


        }

    }

    @Override
    public Token generateToken(User user) {

        return tokenRepository.save(token(user));
    }

   public Token token(User user){
        String generatedToken = RandomStringUtils.randomAlphanumeric(5);
        Token token = new Token();
        token.setToken(generatedToken);
        token.setEmail(user.getEmail());
        token.setUserId(user.getId());
        return token;
    }



    @Override
    public Token save(Token token) {
        return tokenRepository.save(token);
    }

    @Override
    public Optional<Token> findOne(String id) {
        return Optional.ofNullable(tokenRepository.findById(id).get());
    }

    @Override
    public Optional<List<Token>> findAll() {
        return Optional.ofNullable(tokenRepository.findAll());
    }

    @Override
    public void deleteById(String id) {
        tokenRepository.deleteById(id);
    }

    @Override
    public void deleteByObject(Token token) {
        tokenRepository.delete(token);
    }
}
