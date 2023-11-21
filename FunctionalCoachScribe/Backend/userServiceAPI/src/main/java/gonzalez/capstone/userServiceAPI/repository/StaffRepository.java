package gonzalez.capstone.userServiceAPI.repository;

import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import gonzalez.capstone.userServiceAPI.models.Staff;

@Repository
public interface StaffRepository extends MongoRepository<Staff, UUID>{
    
}
