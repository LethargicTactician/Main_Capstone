package gonzalez.capstone.userServiceAPI.models;

import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "teachers")
public class Teachers {

    @Id
    private UUID teacherid;

    @DBRef
    private Users userInfo;

    // private 
    private List<String> officeHours;
    private int officeNum;
    private List<String> teacherCourses;

    //getters and setters
    public UUID getTeacherid() {
        return teacherid;
    }
    public void setTeacherid(UUID teacherid) {
        this.teacherid = teacherid;
    }
    public Users getUserInfo() {
        return userInfo;
    }
    public void setUserInfo(Users userInfo) {
        this.userInfo = userInfo;
    }
    public List<String> getOfficeHours() {
        return officeHours;
    }
    public void setOfficeHours(List<String> officeHours) {
        this.officeHours = officeHours;
    }
    public int getOfficeNum() {
        return officeNum;
    }
    public void setOfficeNum(int officeNum) {
        this.officeNum = officeNum;
    }
    public List<String> getTeacherCourses() {
        return teacherCourses;
    }
    public void setTeacherCourses(List<String> teacherCourses) {
        this.teacherCourses = teacherCourses;
    }


    

    
}
