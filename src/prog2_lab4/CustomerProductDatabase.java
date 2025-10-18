/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2_lab4;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author malak
 */
public class CustomerProductDatabase extends Database{

    public CustomerProductDatabase(String fileName) {
        super(fileName);
    }
    @Override
    CustomerProduct createRecordFrom(String line){
        String[] parts=line.split(",");
        DateTimeFormatter format=DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return new CustomerProduct(parts[0], parts[1], LocalDate.parse(parts[2],format));
    }
}
