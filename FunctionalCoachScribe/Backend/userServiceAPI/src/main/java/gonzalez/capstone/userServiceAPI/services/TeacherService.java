package gonzalez.capstone.userServiceAPI.services;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

//---------------------- MODELS ---------------
import gonzalez.capstone.userServiceAPI.models.Teachers;
import gonzalez.capstone.userServiceAPI.models.Users;
import gonzalez.capstone.userServiceAPI.repository.TeacherRepository;
import gonzalez.capstone.userServiceAPI.repository.UserRepository;

@Service
public class TeacherService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    public ResponseEntity<String> assignTeacher(UUID userId, List<String> hours, int roomNum, List<String> course) {
    Users user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());

    // Check if the user is not already a coach
    if (!"teacher".equals(user.getRole())) {
        // Update the user's role to coach
        user.setRole("teacher");
        userRepository.save(user);

        // Create a new Coaches entry
        Teachers teacher = new Teachers();
        teacher.setTeacherid(UUID.randomUUID());
        teacher.setUserInfo(user);
        teacher.setOfficeHours(hours);
        teacher.setOfficeNum(roomNum);
        teacher.setTeacherCourses(course);

        // Save the coach entry to the database
        teacherRepository.save(teacher);

        // Return a success response
        return ResponseEntity.status(HttpStatus.SC_CREATED).body("User assigned as professor successfully");
    } else{
        
        String message = "User is already a professor";
        return ResponseEntity.status(HttpStatus.SC_CONFLICT).body(message);
    }
        // return null;
    }
    
    public ResponseEntity<String> removeTeacherRole(UUID userId) {
        Users user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());

        if ("teacher".equals(user.getRole())) {
            // Delete the teacher instance
            Teachers teacher = teacherRepository.findByUserInfo(user);
            if (teacher != null) {
                teacherRepository.deleteById(teacher.getTeacherid());
            }

            // Change the user role back to student
            user.setRole("student");
            userRepository.save(user);

            return ResponseEntity.status(HttpStatus.SC_OK).body("Teacher role removed successfully");
        } else {
            // Handle the case where the user is not a teacher
            String message = "User is not a teacher";
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(message);
        }
    }
}
