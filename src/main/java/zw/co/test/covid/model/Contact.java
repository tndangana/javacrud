package zw.co.test.covid.model;


import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "contact")
@Data
public class Contact extends BaseId {

    private String patientId;
    private String mobile;
    private String address;
    private String countryId;

}


