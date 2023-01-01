package Project;

import java.io.*;
import java.util.*;

public class Employees implements Users {

    private ArrayList<Employee> list;

    public Employees() {
        list = new ArrayList<>();
    }

    public void Add(Employee employee) throws IOException {
        list.add(employee);
        Save();
    }

    public Employee Get(int index) {
        return list.get(index);
    }

    @Override
    public int SearchByUserName(String UserName) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUserName().equals(UserName)) {
                return i;
            }
        }
        return -1;
    }    
    @Override
    public ArrayList GetAll() {
        return list;
    }
    @Override
    public boolean Delete(int index) throws IOException {
        if (index != -1) {
            list.remove(index);
            Save();
            return true;
        } else {
            return false;
        }
    }
    @Override
    public void Save() throws IOException {
        File file = new File("Employees.Bin");
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
        out.writeObject(list);
        out.close();
       /* File file = new File("Employees.csv");
        if(!file.exists()){
            file.createNewFile();
        }
        Writer write = new FileWriter(file);
        write.write("Name,UserName,Password,Age,Degree,Years of Experience\n");
        for (int i = 0; i < list.size(); i++)
            write.write(list.get(i).toString()+"\n");
        write.close();*/
    }
    @Override
    public void Sync() throws FileNotFoundException, IOException, ClassNotFoundException {
        File file = new File("Employees.Bin");
        if (!file.exists()) {
            return;
        }
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
        list = (ArrayList<Employee>) in.readObject();
        in.close();
        /*File file = new File("Employees.csv");
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
            ArrayList<String> jobCategory= new ArrayList<>();
            for (int i = 6; i < strarr.length; i++) {
                jobCategory.add(strarr[i]);
            }
            Add(new Employee(strarr[0],strarr[1],strarr[2],Integer.parseInt(strarr[3]),strarr[4],Integer.parseInt(strarr[5]),jobCategory));
        }
        read.close();*/
    }
    @Override
    public boolean Login(String UserName, String Password) {
        if (SearchByUserName(UserName) == -1) {
            return false;
        }
        return Password.equals(list.get(SearchByUserName(UserName)).getPassword());
    }
    @Override
    public boolean isUnique(String UserName) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUserName().equals(UserName)) {
                return false;
            }
        }
        return true;
    }
}
