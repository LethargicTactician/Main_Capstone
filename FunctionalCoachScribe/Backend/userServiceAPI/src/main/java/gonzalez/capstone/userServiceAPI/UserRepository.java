package gonzalez.capstone.userServiceAPI;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.UUID;

public interface UserRepository extends MongoRepository<Users, UUID> {
}
