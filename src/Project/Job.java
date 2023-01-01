package Project;

import java.util.*;
import static Project.RMS.*;
import java.io.Serializable;

public class Job implements Serializable {
    Agency agency;
    Date start_Date;
    boolean Fulltime;
    int yearsofExperience;
    int id;
    String jobTitle;
    String careerLevel;
    String jobDescription;
    ArrayList<String> jobCategory;


    @Override
    public String toString() {
        String str =  getAgency().getUserName() + "," + format.format(getStart_Date()) + "," + isFulltime() + "," + getYearsofExperience() + "," + getId() + "," + getJobTitle() + "," + this.getCareerLevel() + "," + this.getJobDescription();
        for (int i = 0; i < jobCategory.size(); i++) {
            str+= ("," + jobCategory.get(i));
        }
        return str;
    }    

    public Job(Agency agency, Date start_Date, boolean Fulltime, int yearsofExperience, int id, String jobTitle, String careerLevel, String jobDescription, ArrayList<String> jobCategory) {
        this.agency = agency;
        this.start_Date = start_Date;
        this.Fulltime = Fulltime;
        this.yearsofExperience = yearsofExperience;
        this.id = id;
        this.jobTitle = jobTitle;
        this.careerLevel = careerLevel;
        this.jobDescription = jobDescription;
        this.jobCategory = jobCategory;
    }
    public Job(int id,String jobDescription, Date start_Date, Agency agency, boolean Fulltime) {
        this.id = id;
        this.jobDescription = jobDescription;
        this.start_Date = start_Date;
        this.agency = agency;
        this.Fulltime = Fulltime;
    }

    public ArrayList<String> getJobCategory() {
        return jobCategory;
    }

    public void setJobCategory(ArrayList<String> jobCategory) {
        this.jobCategory = jobCategory;
    }

    public int getYearsofExperience() {
        return yearsofExperience;
    }

    public void setYearsofExperience(int yearsofExperience) {
        this.yearsofExperience = yearsofExperience;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCareerLevel() {
        return careerLevel;
    }

    public void setCareerLevel(String careerLevel) {
        this.careerLevel = careerLevel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public boolean isFulltime() {
        return Fulltime;
    }

    public void setFulltime(boolean Fulltime) {
        this.Fulltime = Fulltime;
    }
    public String getJobDescription() {
        return jobDescription;
    }
    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }
    public Date getStart_Date() {
        return start_Date;
    }
    public void setStart_Date(Date start_Date) {
        this.start_Date = start_Date;
    }
    public Agency getAgency() {
        return agency;
    }
    public void setAgency(Agency agency) {
        this.agency = agency;
    }
    
}
