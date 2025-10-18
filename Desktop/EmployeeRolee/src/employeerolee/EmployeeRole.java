package employeerolee;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class EmployeeRole {

     private ProductDatabase productsDatabase;
     private CustomerProductDatabase customerProductDatabase;
     
     
    public EmployeeRole() {
        productsDatabase = new ProductDatabase();
        customerProductDatabase = new CustomerProductDatabase();
    }

    public void setProductsDatabase(ProductDatabase productsDatabase) {
        this.productsDatabase = productsDatabase;
    }

    public void setCustomerProductDatabase(CustomerProductDatabase customerProductDatabase) {
        this.customerProductDatabase = customerProductDatabase;
    }

    public ProductDatabase getProductsDatabase() {
        return productsDatabase;
    }

    public CustomerProductDatabase getCustomerProductDatabase() {
        return customerProductDatabase;
    }
    
    
    public void addProduct(String productID, String productName, String manufacturerName, String supplierName, int quantity) throws IOException{
        
        
        String line1 = productID+","+productName+","+manufacturerName+","+supplierName+","+quantity;
 
        FileWriter writer = new FileWriter("Products.txt", true); //nest3ml append bema en kda kda hanktb f akhr el line
        PrintWriter p = new PrintWriter(writer);
        p.println(line1); //hena hanst3ml el linerepresenation()
        p.close();
        writer.close(); 
    }
    
    public Product[] getListOfProducts() throws FileNotFoundException{
        
        Product[] product = new Product[100];
        File file = new File("Employees.txt");
        Scanner scan = new Scanner(file);
        int count=0;
        
       while (scan.hasNextLine()) //tol ma fe lines, hadkhol el loop
       {
           String line = scan.nextLine();
           String[] splitted = line.split(","); //3mlthm splitted 3shan a7othm fel constructor
           
           if ( splitted.length == 5 ){
               //for validity, a check en 5 items separated f3lan 
               product[count++] = new Product(splitted[0],splitted[1],splitted[2],splitted[3],splitted[4]);
           }
           
       }
       return product;
    }
    
    public CustomerProduct[] getListOfPurchasingOperations() throws FileNotFoundException{
        CustomerProduct[] customerproduct = new CustomerProduct[100];
        File file = new File("CustomerProduct.txt");
        Scanner scan = new Scanner(file);
        int count=0;
        
       while (scan.hasNextLine()) //tol ma fe lines, hadkhol el loop
       {
           String line = scan.nextLine();
           String[] splitted = line.split(","); //3mlthm splitted 3shan a7othm fel constructor
           
           if ( splitted.length == 3 ){
               //for validity, a check en 3 items separated f3lan 
               customerproduct[count++] = new CustomerProduct(splitted[0],splitted[1],splitted[2]);
           }
           
       }
       return customerproduct;
    }

    //dummy 3shan el errors bss
    private static class CustomerProductDatabase {

        public CustomerProductDatabase() {
        }
    }

    private static class CustomerProduct {

        public CustomerProduct() {
        }

        private CustomerProduct(String string, String string0, String string1) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }
    
    
}