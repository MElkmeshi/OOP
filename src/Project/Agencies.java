package Project;

import java.io.*;
import java.util.*;

public class Agencies implements Users {

    private ArrayList<Agency> list;

    public Agencies() {
        list = new ArrayList<>();
    }

    public void Add(Agency agency) throws IOException {
        list.add(agency);
        Save();
    }
    public Agency Get(int index) {
        if (index == -1) {
            return new Agency();
        }
        return list.get(index);
    }

    public ArrayList GetAll() {
        return list;
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
        File file = new File("Agencies.Bin");
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
        out.writeObject(list);
        out.close();
        /*File file = new File(fileName);
        if(!file.exists()){
            file.createNewFile();
        }
        Writer write = new FileWriter(file);
        write.write("Name,UserName,Address,Industry,Password\n");
        for (int i = 0; i < list.size(); i++)
            write.write(AgencyToCsv(list.get(i))+"\n");
        write.close();*/
    }

    @Override
    public void Sync() throws FileNotFoundException, IOException, ClassNotFoundException {
        File file = new File("Agencies.Bin");
        if (!file.exists()) {
            return;
        }
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
        list = (ArrayList<Agency>) in.readObject();
        in.close();
        /*File file = new File("Agencies.csv");
        if(!file.exists()){
            return;
        }
        Scanner read = new Scanner(file);
        read.nextLine();
        if(!read.hasNextLine()){
            read.close();
            return;
        }
        while(read.hasNextLine()){
            String[] strarr = read.nextLine().split(",");
            this.Add(new Agency(strarr[0],strarr[1],strarr[2],strarr[3],strarr[4]));
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
