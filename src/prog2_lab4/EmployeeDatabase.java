/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2_lab4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class EmployeeDatabase {
    private ArrayList<db> records;
    private String fileName;

    public EmployeeDatabase(String fileName) {
        this.fileName = fileName;
        this.records=new ArrayList<>();
    }
    

    @Override
    public void readFromFile() 
    {
        records.clear();// to be sure en file is empty
        try (BufferedReader br= new BufferedReader(new FileReader(fileName)))// buffer reader by5lny a read line line from file
        {
            String line;
            while((line=br.readLine())!=null)
            {
            records.add(createRecordFrom(line));
            }
        }catch (IOException e){
                    System.out.println("error reading from file : "+ e.getMessage());
                    }
        }

    @Override
    public db createRecordFrom(String line) {
        String[] parts=line.split(","); //3ashn 2a2sm el line 
        return new db(parts[0],parts[1],parts[2],parts[3],parts[4]);// ba3ml employee pbject 3ashn a7oto fel arraylist
        
    }

    @Override
    public ArrayList<db> returnAllRecords() 
        {
            
        return records;}

    @Override
    public boolean contains(String key) {
   for(int i=0;i<records.size();i++)
   {
   db emp=records.get(i);
   if(emp.getSearchKey().equals(key))
   {
       return true;
   }
   }
   return false;
    }

    @Override
    public db getRecord(String key) {
        for(int i=0;i<records.size();i++)
        {
            db emp= records.get(i);
           
        if(emp.getSearchKey().equals(key))
        {
        return emp;
        }
        }
        return null;
   }

    @Override
    public void insertRecord(db record) 
    {
        records.add(record);
    }

    @Override
    public void deleteRecord(String key) 
    {
        for(int i=0;i<records.size();i++)
        {
            db emp=records.get(i);
              if(emp.getSearchKey().equals(key)){
              records.remove(i);
              break;
              }
        
        }
   
    }

    @Override
    public void saveToFile() {
        try (PrintWriter pw=new PrintWriter(new FileWriter(fileName)))
        {
        for(int i=0;i<records.size();i++)
        {
         db emp=records.get(i);
         pw.println(emp.lineRepresentation());
        }} catch (IOException e){
                System.out.println("error saving in file: "+e.getMessage());}
        
    }
    
  
    
    
    
}
    
   
    
    
    
    




