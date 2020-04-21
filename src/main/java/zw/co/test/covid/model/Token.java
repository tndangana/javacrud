package zw.co.test.covid.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Document(collection = "token")
@Data
public class Token extends BaseId {
    @NotBlank
    private String token;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String userId;
}







