package zw.co.test.covid.util;

import lombok.Data;

@Data
public class Response {

    String message;

    public Response(String message){
        this.message = message;
    }
}
