package gonzalez.capstone.userServiceAPI.controller;

import org.springframework.web.bind.annotation.*;

import gonzalez.capstone.userServiceAPI.models.Coaches;
import gonzalez.capstone.userServiceAPI.models.Teachers;
import gonzalez.capstone.userServiceAPI.models.Users;
import gonzalez.capstone.userServiceAPI.repository.CoachRepository;
import gonzalez.capstone.userServiceAPI.repository.TeacherRepository;
import gonzalez.capstone.userServiceAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import gonzalez.capstone.userServiceAPI.services.CoachService;
import gonzalez.capstone.userServiceAPI.services.TeacherService;

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
    @Autowired
    public TeacherRepository teacherRepository;

    //--- Servers --->
    public UserService userService;
    public CoachService coachService;
    public TeacherService teacherService;

    @Autowired
    public UserRestController(UserRepository userRepository, CoachRepository coachesRepository, UserService userService, TeacherRepository teacherRepository) {
        this.userRepository = userRepository;
        this.coachesRepository = coachesRepository;
        this.userService = userService;
        this.teacherRepository = teacherRepository;
    }

    //---------------- COACH ENDPOINTS -------------------------
    //#region Coach endpoints region
    
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
    @PutMapping(path = "/updateCoach/{coachUuid}")
    @ResponseStatus(HttpStatus.OK)
    public void updateCoach(@PathVariable(required = true) UUID coachUuid, @RequestBody Coaches coach) {
        if (!coach.getCoachId().equals(coachUuid)) {
            throw new RuntimeException("The two values did not match");
        }
        coachesRepository.save(coach);
    }
    //#endregion Coach endpoints region

    //---------------- PROFESSOR ENDPOINTS ---------------------
      //#region teacher endpoints
    @GetMapping(path = "/getTeacher")
    @ResponseStatus(HttpStatus.OK)
    public List<Teachers> findAllTeacherList() {
        return teacherRepository.findAll();
    }

    // Get teacher information for a user
    @GetMapping(path = "/{userUUID}/teacher")
    @ResponseStatus(HttpStatus.OK)
    public Teachers getUserTeacher(@PathVariable(required = true) UUID userUUID) {
        Users user = userRepository.findById(userUUID).orElseThrow(() -> new NoSuchElementException());
        
        if ("teacher".equals(user.getRole())) {
            return teacherRepository.findByTeacherid(user);
        }        
        return null;
    }

    // Get teacher information for a user based on name
    @GetMapping(path = "/{userLastName}/teacher")
    @ResponseStatus(HttpStatus.OK)
    public Teachers getByNameTeachers(@PathVariable(required = true) String userLastName) {
        List<Users> users = userRepository.findByLastName(userLastName);
    
        if (users.isEmpty()) {
            throw new NoSuchElementException("No user found with last name " + userLastName);
        }
    
        Users user = users.get(0);
        return teacherRepository.findByUserInfoLastName(user.getLastName());
    }

    // -------------------------> Assign a user as a Teacher
    @PostMapping(path = "/{userUUID}/assignTeacher")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> assignUserAsTeacherEntity(@PathVariable(required = true) UUID userUUID) {
        Users user = userRepository.findById(userUUID).orElseThrow(() -> new NoSuchElementException());

        // Use the updateUserRoleToCoach method in the UserService
        ResponseEntity<String> response = userService.updateUserRoleToTeacher(user.getUserid());

        return response;
    }

    //-------------------------> REMOVE Teacher ROLE 
    @PutMapping(path = "/{userUUID}/removeProfessorRole")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> removeProfessorRole(@PathVariable(required = true) UUID userUUID) {
        Users user = userRepository.findById(userUUID).orElseThrow(() -> new NoSuchElementException());
    
        // Check if the user is a teacher
        if ("teacher".equals(user.getRole())) {
            // Remove the teacher role
            user.setRole("student");  // Set it to whatever the default role is
            userRepository.save(user);
    
            // Return a success response
            return ResponseEntity.status(HttpStatus.OK).body("Teacher role removed successfully");
        } else {
            // Return a response indicating that the user is not a teacher
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User is not a Professor");
        }
    }

    // -------------------------> ASSIGN PROFESSOR DETAILS
    @PutMapping(path = "/updateTeacher/{teacherUUID}")
    @ResponseStatus(HttpStatus.OK)
    public void updateCoach(@PathVariable(required = true) UUID teacherUUID, @RequestBody Teachers teacher) {
        if (!teacher.getTeacherid().equals(teacherUUID)) {
            throw new RuntimeException("The two values did not match");
        }
        teacherRepository.save(teacher);
    }
    //#endregion teacher endpoints

    //------------------------ USER BASIC CRUD OPERATIONS ---------------------------------
       //#region Basic crud for users
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
    //#endregion Basic crud for users
}
