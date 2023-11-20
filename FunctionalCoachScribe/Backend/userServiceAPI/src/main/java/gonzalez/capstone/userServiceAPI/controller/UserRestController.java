package gonzalez.capstone.userServiceAPI.controller;

import org.springframework.web.bind.annotation.*;

import gonzalez.capstone.userServiceAPI.models.Coaches;
import gonzalez.capstone.userServiceAPI.models.Users;
import gonzalez.capstone.userServiceAPI.repository.CoachRepository;
import gonzalez.capstone.userServiceAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import gonzalez.capstone.userServiceAPI.services.CoachService;
// import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import gonzalez.capstone.userServiceAPI.services.UserService;
@RestController
@RequestMapping (value = "/v1/user")
// @CrossOrigin(origins = "UserServiceAPI")
public class UserRestController {

    //Collection Repositories
    @Autowired
    public UserRepository userRepository;

    @Autowired
    public CoachRepository coachesRepository;

    public UserService userService;
    public CoachService coachService;

    @Autowired
    public UserRestController(UserRepository userRepository, CoachRepository coachesRepository, UserService userService) {
        this.userRepository = userRepository;
        this.coachesRepository = coachesRepository;
        this.userService = userService;
    }

    //---------------- COACH ENDPOINTS -------------------------


    
    @GetMapping(path = "/getCoaches")
    @ResponseStatus(HttpStatus.OK)
    public List<Coaches> findAllCoachUsers() {
        return coachesRepository.findAll();
    }

    // Get coach information for a user
    @GetMapping(path = "/{userUUID}/coach")
    @ResponseStatus(HttpStatus.OK)
    public Coaches getUserCoach(@PathVariable(required = true) UUID userUUID) {
        Users user = userRepository.findById(userUUID).orElseThrow(() -> new NoSuchElementException());
        
        // Use equals() to compare strings
        if ("coach".equals(user.getRole())) {
            return coachesRepository.findByCoachid(user);
        }
        
        return null;
    }

    // -------------------------> Assign a user as a coach
    @PostMapping(path = "/{userUUID}/assignCoach")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> assignUserAsCoach(@PathVariable(required = true) UUID userUUID) {
        Users user = userRepository.findById(userUUID).orElseThrow(() -> new NoSuchElementException());

        // Use the updateUserRoleToCoach method in the UserService
        ResponseEntity<String> response = userService.updateUserRoleToCoach(user.getUserid());

        return response;
    }

    //-------------------------> REMOVE COACH ROLE 
    @PutMapping(path = "/{userUUID}/removeCoachRole")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> removeCoachRole(@PathVariable(required = true) UUID userUUID) {
        Users user = userRepository.findById(userUUID).orElseThrow(() -> new NoSuchElementException());
    
        // Check if the user is a coach
        if ("coach".equals(user.getRole())) {
            // Remove the coach role
            user.setRole("student");  // Set it to whatever the default role is
            userRepository.save(user);
    
            // Return a success response
            return ResponseEntity.status(HttpStatus.OK).body("Coach role removed successfully");
        } else {
            // Return a response indicating that the user is not a coach
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User is not a coach");
        }
    }

    // -------------------------> ASSIGN COACH DETAILS
    @PutMapping(path = "/{coachUuid}")
    @ResponseStatus(HttpStatus.OK)
    public void updateCoach(@PathVariable(required = true) UUID coachUuid, @RequestBody Coaches coach) {
        if (!coach.getCoachId().equals(coachUuid)) {
            throw new RuntimeException("The two values did not match");
        }
        coachesRepository.save(coach);
    }



    //------------------------ USER BASIC CRUD OPERATIONS ---------------------------------
    @GetMapping(path = "")
    @ResponseStatus(HttpStatus.OK)
    public List<Users> findAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/{userUUID}")
    @ResponseStatus(HttpStatus.OK)
    public Users getUser(@PathVariable(required = true) UUID userUUID) {
        return userRepository.findById(userUUID).orElseThrow(() -> new NoSuchElementException());
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody Users user) {
        user.setUserid(UUID.randomUUID());
        user.setJoinDate(LocalDate.now());
        userRepository.save(user);
    }

    @PostMapping(path = "/addUsers")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUsers(@RequestBody List<Users> users) {
        for (Users user : users) {
            createUser(user);
        }
    }

    @PutMapping(path = "/{userUUID}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@PathVariable(required = true) UUID userUUID, @RequestBody Users user) {
        if (!user.getUserid().equals(userUUID)) {
            throw new RuntimeException("The two values did not match");
        }

        userRepository.save(user);
    }

    @DeleteMapping(path = "/{userUUID}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable(required = true) UUID userUUID) {
        userRepository.deleteById(userUUID);
    }

    private static int createRandomIntBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

    private static LocalDate createRandomDate(int startYear, int endYear) {
        int day = createRandomIntBetween(1, 28);
        int month = createRandomIntBetween(1, 12);
        int year = createRandomIntBetween(startYear, endYear);
        return LocalDate.of(year, month, day);
    }
}
