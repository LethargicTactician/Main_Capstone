package gonzalez.capstone.userServiceAPI.models;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "staff")
public class Staff {
    @Id
    private UUID staffid;
    @DBRef
    private Users UUID;

    private String title;
    private int roomNumber;

    //getters and setters 

    public UUID getStaffid() {
        return staffid;
    }
    public void setStaffid(UUID staffid) {
        this.staffid = staffid;
    }
    //do i really need this? Yesnt?
    public Users getUUID() {
        return UUID;
    }
    public void setUUID(Users uUID) {
        UUID = uUID;
    }
    
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getRoomNumber() {
        return roomNumber;
    }
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }    
    
}
