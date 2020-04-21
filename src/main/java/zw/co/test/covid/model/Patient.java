package zw.co.test.covid.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "patient")
@Data
public class Patient extends BaseId {

    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private Gender gender;


}



