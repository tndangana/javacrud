package zw.co.test.covid.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Covidtest")
@Data
public class Covidtest extends BaseId {

    private String patientId;
    @DBRef
    private List<Symptom> symptoms;
}
