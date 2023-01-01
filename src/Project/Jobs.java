package Project;

// @author melkmeshi
import java.io.*;
import java.text.ParseException;
import java.util.*;
import static Project.RMS.*;

public class Jobs implements CRUD{

    private ArrayList<Job> list;
    static int ID = 1;

    public Jobs() {
        list = new ArrayList<>();
    }

    public void CREATE(Agency agency, Date start_Date, boolean Fulltime, int yearsofExperience, String jobTitle, String careerLevel, String jobDescription, ArrayList<String> jobCategory) throws IOException {
        list.add(new Job(agency, start_Date, Fulltime, yearsofExperience, ID++, jobTitle, careerLevel, jobDescription, jobCategory));
        this.SAVE();
    }

    public void CREATE(Agency agency, Date start_Date, boolean Fulltime, int yearsofExperience, int id, String jobTitle, String careerLevel, String jobDescription, ArrayList<String> jobCategory) throws IOException {
        list.add(new Job(agency, start_Date, Fulltime, yearsofExperience, id, jobTitle, careerLevel, jobDescription, jobCategory));
        ID = id + 1;
        this.SAVE();
    }

    public Job Get(int index) {
        return list.get(index);
    }
   
    @Override
    public int getIndex(int id) {
        for (int i = 0; i < list.size(); i++) {
            if (id == list.get(i).getId()) {
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
        ArrayList<Job> res = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            if (!Collections.disjoint(list.get(i).getJobCategory(), emp.getJobCategory())) {
            res.add(list.get(i));
            }
        }
        return res;
    }
    
    public ArrayList READ(Employee emp, String word) {
        ArrayList<Job> res = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            String data = list.get(i).getJobDescription() + " " + list.get(i).getCareerLevel() + " " + list.get(i).getJobTitle() + " " + list.get(i).getAgency().getName() + " " + String.join(" ", list.get(i).getJobCategory() + " " + list.get(i).getAgency().getAddress()+ " " + list.get(i).getAgency().getName());
            if (!Collections.disjoint(list.get(i).getJobCategory(), emp.getJobCategory()) && data.contains(word)) {
            res.add(list.get(i));
            }
        }
        return res;
    }

    @Override
    public ArrayList READ(String agencyUserName) {
        ArrayList<Job> res = new ArrayList<Job>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getAgency().getUserName().equals(agencyUserName)) {
                res.add(list.get(i));
            }
        }
        return res;
    }

    @Override
    public void DELETE(int index) throws IOException {
        list.remove(index);
        this.SAVE();
    }

    @Override
    public void DELETE(String AgneyUserName) throws IOException {
        for (int i = 0; i < list.size(); i++) {
            if (AgneyUserName.equals(list.get(i).getAgency().getUserName())) {
                this.DELETE(i);
                System.out.println(i);
            }
        }
        this.SAVE();
    }

    @Override
    public void SAVE() throws IOException {
        File file = new File("Jobs.Bin");
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
        out.writeObject(list);
        out.close();
        /*File file = new File("Jobs.csv");
        if (!file.exists()) {
            file.createNewFile();
        }
        Writer write = new FileWriter(file);
        write.write("Agency Username,Start Date,is Full Time,Years of Experience,Job ID,Job Title,Job Career Level,Job Description\n");
        for (int i = 0; i < list.size(); i++) {
            write.write(list.get(i).toString() + "\n");
        }
        write.close();*/
    }

    @Override
    public void Sync() throws IOException, ParseException, ClassNotFoundException {
        File file = new File("Jobs.Bin");
        if (!file.exists()) {
            return;
        }
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
        list = (ArrayList<Job>) in.readObject();
        in.close();
        if (list.size()  != 0) {
            ID = list.get(list.size()-1).getId()+1;   
        }
        /*File file = new File("Jobs.csv");
        if (!file.exists()) {
            return;
        }
        Scanner read = new Scanner(file);
        if (!read.hasNextLine()) {
            return;
        }
        read.nextLine();
        while (read.hasNextLine()) {
            String[] strarr = read.nextLine().split(",");
            ArrayList<String> jobCategory = new ArrayList<>();
            for (int i = 8; i < strarr.length; i++) {
                jobCategory.add(strarr[i]);
            }
            CREATE(agency.Get(agency.SearchByUserName(strarr[0])), format.parse(strarr[1]), Boolean.parseBoolean(strarr[2]), Integer.parseInt(strarr[3]), Integer.parseInt(strarr[4]), strarr[5], strarr[6], strarr[7], jobCategory);
        }
        read.close();*/
    }

}
