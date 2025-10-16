/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2_lab4;

import java.util.ArrayList;

public interface Database<T> {
    void readFromFile();
    T createRecordFrom(String line);
    ArrayList<T> returnAllRecords();
    boolean contains(String key);
    T getRecord(String key);
    void insertRecord(T record);
    void deleteRecord(String key);
    void saveToFile();
    
   
}


