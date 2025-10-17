/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2_lab4;

import java.util.ArrayList;

public interface Database<db> {
    void readFromFile();
    db createRecordFrom(String line);
    ArrayList<db> returnAllRecords();
    boolean contains(String key);
    db getRecord(String key);
    void insertRecord(db record);
    void deleteRecord(String key);
    void saveToFile();
    
   
}


