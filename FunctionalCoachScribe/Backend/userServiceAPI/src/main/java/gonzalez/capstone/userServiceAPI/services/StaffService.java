package gonzalez.capstone.userServiceAPI.services;


import java.util.NoSuchElementException;
import java.util.UUID;
import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import gonzalez.capstone.userServiceAPI.models.Staff;
import gonzalez.capstone.userServiceAPI.models.Users;
import gonzalez.capstone.userServiceAPI.repository.StaffRepository;
import gonzalez.capstone.userServiceAPI.repository.UserRepository;

@Service
public class StaffService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StaffRepository staffRepository;

    public ResponseEntity<String> assignStaff(UUID userId, int roomNum, String staffTitle) {
    Users user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());

    // Check if the user is not already a coach
    if (!"staff".equals(user.getRole())) {
        // Update the user's role to coach
        user.setRole("staff");
        userRepository.save(user);

        // Create a new Coaches entry
        Staff staff = new Staff();
        staff.setStaffid(UUID.randomUUID());
        staff.setUserInfo(user);
        staff.setRoomNumber(roomNum);
        staff.setTitle(staffTitle);

        // Save the coach entry to the database
        staffRepository.save(staff);

        // Return a success response
        return ResponseEntity.status(HttpStatus.SC_CREATED).body("User assigned staff successfully");
    } else{
        
        String message = "User is already part of staff";
        return ResponseEntity.status(HttpStatus.SC_CONFLICT).body(message);
    }
        // return null;
    }
    
    public ResponseEntity<String> removeStaffRole(UUID userId) {
        Users user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());

        if ("staff".equals(user.getRole())) {
            // Delete the staff instance
            Staff staff = staffRepository.findByUserInfo(user);
            if (staff != null) {
                staffRepository.deleteById(staff.getStaffid());
            }

            // Change the user role back to student
            user.setRole("student");
            userRepository.save(user);

            return ResponseEntity.status(HttpStatus.SC_OK).body("Staff role removed successfully");
        } else {
            // Handle the case where the user is not a staff
            String message = "User is not part of staff";
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(message);
        }
    }
}
