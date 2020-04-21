package zw.co.test.covid.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import zw.co.test.covid.model.Token;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@Service
public class EmailUtils implements EmailUtilService {

    @Autowired
    private JavaMailSender javaMailSender;


    @Override
   public void verifyTokenEmail(Token token) throws MessagingException, IOException {

        MimeMessage msg = javaMailSender.createMimeMessage();

        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo(token.getEmail());

        helper.setSubject("Token Verification");

        // default = text/plain
        //helper.setText("Check attachment for image!");

        // true = text/html
        helper.setText("<h1>Your Token is:</h1>"+token.getToken(), true);


        javaMailSender.send(msg);

    }
}
