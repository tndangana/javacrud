package zw.co.test.covid.payloads;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import zw.co.test.covid.model.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class SignUpRequest {

    @NotBlank
    @Size(min = 4, max = 40)
    private String firstName;
    @NotBlank
    @Size(min = 4, max = 40)
    private String lastName;
    @NotBlank
    private String mobileNumber;
    @NotBlank
    @Size(max = 40)
    @Email
    private String email;
    @NotBlank
    @Size(min = 6, max = 20)
    private String password;
    private Role role;

}



