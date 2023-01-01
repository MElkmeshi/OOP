package Project;
import java.io.Serializable;
public class Agency extends User implements Serializable{
    private String Address;
    String industry;

    public Agency() {
    }
    public Agency(String Name, String UserName, String Address,String industry,String Password) {
        this.Name = Name;
        this.UserName = UserName;
        this.Address = Address;
        this.industry = industry;
        this.Password = Password;
    }
    public String getIndustry() {
        return industry;
    }
    public void setIndustry(String industry) {
        this.industry = industry;
    }
    public String getName() {
        return Name;
    }
    public String getUserName() {
        return UserName;
    }
    public void setUserName(String UserName) {
        this.UserName = UserName;
    }
    public void setName(String Name) {
        this.Name = Name;
    }
    public String getAddress() {
        return Address;
    }
    public void setAddress(String Address) {
        this.Address = Address;
    }
    public String getPassword() {
        return Password;
    }
    public void setPassword(String Password) {
        this.Password = Password;
    }
    public String toString(int index) {
        return "Agency Name: " + Name + " UserName: " + UserName + " Password: " + Password + " Address: " + Address + " industry: " + industry + " Index: "+ index;
    }

}
