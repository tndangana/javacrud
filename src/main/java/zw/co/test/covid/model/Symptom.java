package zw.co.test.covid.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "symptom")
@Data
public class Symptom extends BaseId {

    private String name;

}
