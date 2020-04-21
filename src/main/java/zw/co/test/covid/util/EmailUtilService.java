package zw.co.test.covid.util;

import zw.co.test.covid.model.Token;

import javax.mail.MessagingException;
import java.io.IOException;

public interface EmailUtilService {


  void  verifyTokenEmail(Token token) throws MessagingException, IOException;
}
