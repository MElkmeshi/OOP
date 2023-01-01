/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Project;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author melkmeshi
 */
public interface CRUD {

    public int getIndex(int id);

    public ArrayList READ();

    public ArrayList READ(Employee emp);

    public ArrayList READ(String agencyUserName);

    public void DELETE(int index)throws IOException;

    public void DELETE(String AgneyUserName)throws IOException;

    public void SAVE()throws IOException;

    public void Sync() throws IOException, ParseException, ClassNotFoundException;

}
