package gonzalez.capstone.userServiceAPI;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import java.util.UUID;

@Document(collection = "users")
public class Users {
    @Id
    private UUID userid;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean isCoach;
    private boolean isAdmin;
    private LocalDate joinDate;



    public Users(){
        this.userid = UUID.randomUUID();
        this.firstName = null;
        this.lastName = null;
        this.email = null;
        this.password = null;
        this.isCoach =  false;
        this.isAdmin = false;

    }
    //----------------- GETTERS -------------------
    public UUID getUserid() {
        return userid;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isCoach() {
        return isCoach;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    //------------SETTERS ----------------------------

    public void setUserid(UUID userid) {
        this.userid = userid;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCoach(boolean coach) {
        this.isCoach = coach;
    }

    public void setAdmin(boolean admin) {
        this.isAdmin = admin;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }
}
