package gonzalez.capstone.userServiceAPI.models;
import java.util.List;
import java.util.UUID;
// import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
// import gonzalez.capstone.userServiceAPI.models.Users;

@Document(collection = "coaches")
public class Coaches {

    //One on one relationship with users
    @Id
    private UUID coachId;
    
    @DBRef
    private Users coachid;

    private List<String> hours;
    private List<String> coachingDays;
    private List<String> course;

    // fk?
    public UUID getCoachId() {
        return coachId;
    }

    public void setCoachId(UUID coachId) {
        this.coachId = coachId;
    }

    public Users getCoachid() {
        return coachid;
    }

    public void setCoachid(Users coachid) {
        this.coachid = coachid;
    }

    public List<String> getHours() {
        return hours;
    }
    public void setHours(List<String> hours) {
        this.hours = hours;
    }
    public List<String> getCoachingDays() {
        return coachingDays;
    }
    public void setCoachingDays(List<String> coachingDays) {
        this.coachingDays = coachingDays;
    }
    public List<String> getCourse() {
        return course;
    }
    public void setCourse(List<String> course) {
        this.course = course;
    }
    // public Users getUser() {
    //     return user;
    // }
    // public void setUser(Users user) {
    //     this.user = user;
    // }
    

    
}
