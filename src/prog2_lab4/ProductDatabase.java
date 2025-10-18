/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2_lab4;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import prog2_lab4.Database;

/**
 *
 * @author it
 */
public class ProductDatabase extends Database<Product>{
    private ArrayList<Product> records =  new ArrayList<>();
    private String FileName;
public ProductDatabase(String fileName) {
super(fileName);
this.FileName = fileName;
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
public String getSearchKey(Product record){
return record.getSaerchKey();
}
   @Override
   public String lineRepresentation(Product record){
   return record.lineRepresentation();
   
   }
    
}

    
    
    

