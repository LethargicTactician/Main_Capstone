package gonzalez.capstone.userServiceAPI.services;

import java.util.*;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import gonzalez.capstone.userServiceAPI.models.Coaches;
import gonzalez.capstone.userServiceAPI.models.Users;
import gonzalez.capstone.userServiceAPI.repository.CoachRepository;
import gonzalez.capstone.userServiceAPI.repository.UserRepository;

@Service
public class CoachService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CoachRepository coachRepository;

    public ResponseEntity<String> assignCoach(UUID userId, List<String> hours, List<String> coachingDays, List<String> course) {
        Users user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());

        // Check if the user is not already a coach
        if (!"coach".equals(user.getRole())) {
            // Update the user's role to coach
            user.setRole("coach");
            userRepository.save(user);

            // Create a new Coaches entry
            Coaches coach = new Coaches();
            coach.setCoachId(UUID.randomUUID());
            coach.setCoachid(user);
            coach.setHours(hours);
            coach.setCoachingDays(coachingDays);
            coach.setCourse(course);

            // Save the coach entry to the database
            coachRepository.save(coach);

            // Return a success response
            return ResponseEntity.status(HttpStatus.SC_CREATED).body("User assigned as coach successfully");
        } else{
            
            String message = "User is already a coach";
            return ResponseEntity.status(HttpStatus.SC_CONFLICT).body(message);
        }
        // return null;
    }
}
