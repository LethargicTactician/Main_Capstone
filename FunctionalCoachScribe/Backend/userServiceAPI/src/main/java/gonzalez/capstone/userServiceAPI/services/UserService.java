package gonzalez.capstone.userServiceAPI.services;

import java.util.UUID;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import gonzalez.capstone.userServiceAPI.models.Coaches;
import gonzalez.capstone.userServiceAPI.models.Users;
import gonzalez.capstone.userServiceAPI.repository.CoachRepository;
// import com.netflix.discovery.converters.Auto;
import gonzalez.capstone.userServiceAPI.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CoachRepository coachRepository;

    public ResponseEntity<String> updateUserRoleToCoach(UUID userId) {
        Users user = userRepository.findById(userId).orElse(null);

        if (user != null && !"coach".equals(user.getRole())) {
            // Update the user's role to coach
            user.setRole("coach");
            userRepository.save(user);

            // Create a corresponding coach entry
            Coaches coach = new Coaches();
            coach.setCoachId(UUID.randomUUID());
            coach.setCoachid(user);

            // Save the coach entry to the database
            coachRepository.save(coach);

            // Return a success response
            return ResponseEntity.status(HttpStatus.SC_CREATED).body("User assigned as coach successfully");
        } else {
            // Return a response with a conflict status (HTTP 409)
            return ResponseEntity.status(HttpStatus.SC_CONFLICT).body("User is already a coach");
        }
    }
}


