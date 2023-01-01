/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Project;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author melkmeshi
 */
public interface Users {

    public int SearchByUserName(String UserName);

    public boolean Delete(int index) throws IOException;

    public void Save() throws IOException;
    
    public void Sync() throws FileNotFoundException, IOException, ClassNotFoundException;
    
    public boolean Login(String UserName,String Password);
            
    public boolean isUnique(String UserName);
    
    public ArrayList GetAll();
}
