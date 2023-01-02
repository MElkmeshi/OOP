package Project;

// @author melkmeshi
import java.io.*;
import java.text.ParseException;
import java.util.*;

public class Interviews implements CRUD {

    private int ID = 1;
    private ArrayList<Interview> list;

    public Interviews() {
        list = new ArrayList<>();
    }

    public void CREATE(Job job, Employee employee, int Id) throws IOException {//for ead from file
        list.add(new Interview(Id, job, employee));
        ID = Id + 1;
        this.SAVE();
    }

    public Boolean CREATE(Job job, Employee employee) throws IOException {//for main
        if (employee.getYearsofExperience() >= job.getYearsofExperience()) {
            list.add(new Interview(ID++, job, employee));
            this.SAVE();
            return true;
        }
        return false;
    }

    public Interview Get(int index) {
        return list.get(index);
    }

    public void DELETE(Employee emp) throws IOException {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getEmployee().getUserName().equals(emp.getUserName())) {
                list.remove(i);
            }
        }
        this.SAVE();
    }

    @Override
    public int getIndex(int id) {
        for (int i = 0; i < list.size(); i++) {
            if (id == list.get(i).getID()) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public ArrayList READ() {
        return list;
    }

    @Override
    public ArrayList READ(Employee emp) {
        ArrayList<Interview> res = new ArrayList<Interview>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getEmployee().getUserName().equals(emp.getUserName())) {
                res.add(list.get(i));
            }
        }
        return res;
    }

    @Override
    public ArrayList READ(String agencyUserName) {
        ArrayList<Interview> res = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if ((list.get(i).getJob().getAgency().getUserName().equals(agencyUserName)) && (list.get(i).getIsApproved() == null || list.get(i).getIsApproved() == true)) {
                res.add(list.get(i));
            }
        }
        return res;
    }

    @Override
    public void DELETE(int index) throws IOException {
        list.remove(index);
        SAVE();
    }

    @Override
    public void DELETE(String AgneyUserName) throws IOException {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getJob().getAgency().getUserName().equals(AgneyUserName)) {
                list.remove(i);
            }
        }
        this.SAVE();
    }

    @Override
    public void SAVE() throws IOException {
        File file = new File("Interviews.Bin");
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
        out.writeObject(list);
        out.close();
        /*File file = new File("Interviews.csv");
        if(!file.exists()){
            file.createNewFile();
        }
        Writer write = new FileWriter(file);
        write.write("Job ID,Employee Username,Interview ID\n");
        for (int i = 0; i < list.size(); i++)
            write.write(InterviewToCSV(list.get(i))+"\n");
        write.close();*/
    }

    @Override
    public void Sync() throws IOException, ParseException, ClassNotFoundException {
        File file = new File("Interviews.Bin");
        if (!file.exists()) {
            return;
        }
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
        list = (ArrayList<Interview>) in.readObject();
        in.close();
        if (list.size()  != 0) {
            ID = list.get(list.size()-1).getID()+1;   
        }
        /*File file = new File("Interviews.csv");
        if(!file.exists()){
            return;
        }
        Scanner read = new Scanner(file);
        if(!read.hasNextLine()){
            return;
        }
        read.nextLine();
        while(read.hasNextLine()){
            String[] strarr = read.nextLine().split(",");
            CREATE(job.getJob(job.getIndex(Integer.parseInt(strarr[0]))),emp.GetEmployee(emp.SearchByUserName(strarr[1])),Integer.parseInt(strarr[2]));
        }
        read.close(); */
    }
}
