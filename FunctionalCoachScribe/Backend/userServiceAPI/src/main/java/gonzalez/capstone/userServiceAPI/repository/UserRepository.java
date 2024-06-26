package gonzalez.capstone.userServiceAPI.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import gonzalez.capstone.userServiceAPI.models.Users;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends MongoRepository<Users, UUID> {
    List<Users> findByLastName(String lastName);
}
