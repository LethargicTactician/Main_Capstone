package gonzalez.capstone.userServiceAPI.repository;
// package gonzalez.capstone.userServiceAPI.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import gonzalez.capstone.userServiceAPI.models.Coaches;
import gonzalez.capstone.userServiceAPI.models.Users;

import java.util.UUID;

@Repository
public interface CoachRepository extends MongoRepository<Coaches, UUID> {
    Coaches findByCoachid(Users user);
    // Coaches findByRole(Users userRole);
    void deleteByCoachid(Users user);
    // Coaches findByCoachid(Users user);
}

