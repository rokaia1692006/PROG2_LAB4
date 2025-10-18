/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2_lab4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author LapTop
 */
public abstract class Database {

    private ArrayList<Record> records;
    private String fileName;

    public Database(String fileName) {
        this.fileName = fileName;
        this.records = new ArrayList<>();
    }
//using getters to allow sub classes to access them

    public ArrayList<Record> getRecords() {
        return records;
    }

    public String getFileName() {
        return fileName;
    }

    abstract Record createRecordFrom(String line);//diff in each data base bas they both have the method 

    public void readFromFile() {
        records.clear();// to be sure en file is empty
        try (BufferedReader br = new BufferedReader(new FileReader(fileName)))// buffer reader by5lny a read line line from file
        {
            String line;
            while ((line = br.readLine()) != null) {
                records.add(createRecordFrom(line));
            }
        } catch (IOException e) {
            System.out.println("error reading from file : " + e.getMessage());
        }
    }

    public ArrayList<Record> returnAllRecords() {
        return records;
    }

    abstract String getSearchKey(Record record);//3ashn employee wproduct kol wa7d by call el function b tar2to 

    public boolean contains(String key) {
        for (int i = 0; i < records.size(); i++) {
            if (getSearchKey(records.get(i)).equals(key)) {
                return true;
            }
        }
        return false;
    }

    public Record getRecord(String key) {
        for (int i = 0; i < records.size(); i++) {
            if (getSearchKey(records.get(i)).equals(key)) {
                return records.get(i);
            }
        }
        return null;
    }

    public void insertRecord(Record record) {
        String key = getSearchKey(record);
        if (contains(key)) {
            System.out.println("a record with the same key already exists");
        } else {
            records.add(record);
        }
    }

    public void deleteRecord(String key) {
        for (int i = 0; i < records.size(); i++) {
            if (getSearchKey(records.get(i)).equals(key)) {
                records.remove(i);
                break;
            }
        }
    }

    public void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            for (int i = 0; i < records.size(); i++) {
                Record emp = records.get(i);
                pw.println(emp.lineRepresentation());
            }
        } catch (IOException e) {
            System.out.println("error saving in file: " + e.getMessage());
        }
    }
}
//..

