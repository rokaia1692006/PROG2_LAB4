/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2_lab4;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author it
 */
public class ProductDatabase implements Database<Product>{
    private ArrayList<Product> records =  new ArrayList<>();
    private String FileName;
    

    @Override
    public void readFromFile() {
        try{
        File file = new File(FileName);
        Scanner scan = new Scanner(file);
        while(scan.hasNextLine()){
            String line = scan.nextLine();
            try{
            records.add(createRecordFrom(line));
        
            }
            catch(IllegalArgumentException e){
                System.err.println("ERROR : "+ e.getMessage());
            
            }
        
        }
        
        }
        catch (Exception e){
            System.err.println("ERROR : "+e.getMessage());
        
        
        
        }
    }

    public ProductDatabase(String FileName) {
      
        this.FileName = FileName;
    }

    @Override
    public Product createRecordFrom(String line) {
        String [] stuff = line.split(",");
        if(contains(stuff[0])){
        throw new IllegalArgumentException("REPITITION IN FILE");
        }
            Product pr = new Product(stuff[0], stuff[1], stuff[2], stuff[3], Integer.parseInt(stuff[4]), Float.parseFloat(stuff[5]));
            return pr;
    }

    @Override
    public ArrayList<Product> returnAllRecords() {
    return this.records;
    }

    @Override
    public boolean contains(String key) {
    for (Product pr : records){
    if(pr.getSaerchKey().equals(key)){
    return true;
    }
    
    }
    
    return false;
    }

    @Override
    public Product getRecord(String key) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insertRecord(Product record) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteRecord(String key) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void saveToFile() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
