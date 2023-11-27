package gonzalez.capstone.userServiceAPI.repository;

import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import gonzalez.capstone.userServiceAPI.models.Staff;
import gonzalez.capstone.userServiceAPI.models.Users;

@Repository
public interface StaffRepository extends MongoRepository<Staff, UUID>{
    Staff findByUserInfoLastName(String lastName);
    Staff findByStaffid(Users user);
    void deleteByUserInfo(Users user);
    Staff findByUserInfo(Users user);
}
