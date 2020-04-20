package zw.co.test.covid.model;


import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "country")
@Data
public class Country extends BaseId {

    private String name;

}
