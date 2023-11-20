package gonzalez.capstone.userServiceAPI.models;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "teachers")
public class Teachers {

    @Id
    private UUID teacherid;

    @DBRef
    private Users UUID;

    // private 


    
}
