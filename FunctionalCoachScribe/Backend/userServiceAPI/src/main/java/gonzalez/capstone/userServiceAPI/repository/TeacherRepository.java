package gonzalez.capstone.userServiceAPI.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import gonzalez.capstone.userServiceAPI.models.Teachers;
import gonzalez.capstone.userServiceAPI.models.Users;

// import java.util.List;
import java.util.UUID;

@Repository
public interface TeacherRepository extends MongoRepository<Teachers, UUID> {
    Teachers findByUserInfoLastName(String lastName);
    Teachers findByTeacherid(Users user);
    void deleteByUserInfo(Users user);
    Teachers findByUserInfo(Users user);
}
