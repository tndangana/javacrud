package zw.co.test.covid.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
@Data
public class ApiExceptionResponse {

    private final  String message;
    private final HttpStatus httpStatus;
    private final LocalDateTime localDateTime;

    public ApiExceptionResponse(String message, HttpStatus httpStatus, LocalDateTime localDateTime) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.localDateTime = localDateTime;
    }
}
