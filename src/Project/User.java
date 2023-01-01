package Project;
// @author melkmeshi
import java.io.Serializable;
public class User implements Serializable{
    String Name;
    String UserName;
    String Password;
    

    @Override
    public String toString() {
        return "User{" + "Name=" + Name + ", UserName=" + UserName + ", Password=" + Password + '}';
    }
}
