package zw.co.test.covid.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;


@Document(collection = "user")
@Data
@NoArgsConstructor
public class User extends BaseId{

    @NotBlank
    private String firstName;
    private String lastName;
    @Email
    @NotBlank
    private String email;
    private Boolean verified = Boolean.FALSE;
    private String mobileNumber;
    private String password;
    @DBRef
    private Set<Role> roles = new HashSet<>();



    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String firstName, String lastName, String email, String mobileNumber, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.password = password;

    }
}

