package Project;
import java.util.*;
import java.io.Serializable;
public class Employee extends User implements Serializable{
    private int Age;
    private String Degree;
    private int yearsofExperience;
    private ArrayList<String> jobCategory;

    public Employee(String Name,String UserName,String Password,int Age, String Degree, int yearsofExperience, ArrayList<String> jobCategory) {
        this.Name = Name;
        this.UserName = UserName;
        this.Password = Password;
        this.Age = Age;
        this.Degree = Degree;
        this.yearsofExperience = yearsofExperience;
        this.jobCategory = jobCategory;
    }

    public int getYearsofExperience() {
        return yearsofExperience;
    }

    public void setYearsofExperience(int yearsofExperience) {
        this.yearsofExperience = yearsofExperience;
    }

    public ArrayList<String> getJobCategory() {
        return jobCategory;
    }

    public void setJobCategory(ArrayList<String> jobCategory) {
        this.jobCategory = jobCategory;
    }


    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }
    public int getAge() {
        return Age;
    }
    public void setAge(int Age) {
        this.Age = Age;
    }
    public String getName() {
        return Name;
    }
    public void setName(String Name) {
        this.Name = Name;
    }
    public String getDegree() {
        return Degree;
    }
    public void setDegree(String Degree) {
        this.Degree = Degree;
    }
    public String getPassword() {
        return Password;
    }
    public void setPassword(String password) {
        this.Password = password;
    }

    @Override
    public String toString() {
        String str = Name + "," + UserName + "," + Password + "," + Age + "," + Degree + "," + yearsofExperience;
        for (int i = 0; i < jobCategory.size(); i++) {
            str+= ("," + jobCategory.get(i));
        }
        return str;
    }
    
}