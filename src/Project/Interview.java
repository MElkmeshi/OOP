package Project;

// @author melkmeshi
import java.io.Serializable;
import java.util.Date;

public class Interview implements Serializable{
    private int ID;
    private Job job;
    private Employee employee;
    private Boolean isApproved = null;
    private Date interviewDate = new Date();

    public Boolean getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(Boolean isApproved) {
        this.isApproved = isApproved;
    }

    public Date getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(Date interviewDate) {
        this.interviewDate = interviewDate;
    }

    public Interview(int ID, Job job, Employee employee) {
        this.ID = ID;
        this.job = job;
        this.employee = employee;
    }
    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public Job getJob() {
        return job;
    }
    public void setJob(Job job) {
        this.job = job;
    }
    public Employee getEmployee() {
        return employee;
    }
    public void setEmployee(Employee employee) {
        this.employee = employee;
    } 
}