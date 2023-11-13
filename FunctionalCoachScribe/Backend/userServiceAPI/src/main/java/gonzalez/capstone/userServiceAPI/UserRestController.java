package gonzalez.capstone.userServiceAPI;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
@RestController
@RequestMapping (value = "/user")
public class UserRestController {
//    public List<Users> findByTitleContainingOrDescriptionContaining(String txt, String txt2);
 // need to have "value" - was giving me trouble not finding path...

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/users")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Users> findAllUsers() {
        return userRepository.findAll();
    }

//        @GetMapping(path = "/search/{searchText}")
//        @ResponseStatus(code = HttpStatus.OK)
//        public List<Book> searchBooks(@PathVariable(required = true) String searchText) {
//            return userRepository.findByTitleContainingOrDescriptionContaining(searchText, searchText);
//        }

    @GetMapping(path = "/{userid}")
    @ResponseStatus(code = HttpStatus.OK)
    public Users getUser(@PathVariable UUID userid) {
        return userRepository.findById(userid).orElseThrow(() -> new NoSuchElementException());
    }

    @PostMapping(path="/fuckyou")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createProduct(@RequestBody Users product){
        userRepository.save(product);
    }

    // @PostMapping(path = "/addUsers")
    // @ResponseStatus(code = HttpStatus.CREATED)
    // public void createUser(@RequestBody List<Users> users) {
    //     for (Users user : users) {
    //         createUser(users);
    //     }
    // }

    @PutMapping(path = "/{userid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBook(@PathVariable(required = true) UUID userid, @RequestBody Users user) {
        if (!user.getUserid().equals(userid)) {
            throw new RuntimeException("the two values did not match");
        }

        userRepository.save(user);
    }

    @DeleteMapping(path = "/{userid}")
    @ResponseStatus(HttpStatus.OK)
    public void DeleteBook(@PathVariable(required = true) UUID userid) {
        userRepository.deleteById(userid);
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
