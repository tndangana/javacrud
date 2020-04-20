package zw.co.test.covid.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "role")
@Data
public class Role extends BaseId {

    private String roleName;


}
