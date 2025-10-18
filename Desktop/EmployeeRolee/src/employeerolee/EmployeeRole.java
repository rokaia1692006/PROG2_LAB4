package employeerolee;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
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
           
           if ( splitted.length == 4 ){
               //for validity, a check en 3 items separated f3lan 
               customerproduct[count++] = new CustomerProduct(splitted[0],splitted[1],splitted[2],splitted[3]);
           }
           
       }
       return customerproduct;
    }

    
    public boolean purchaseProduct ( String customerSSN, String productID, LocalDate purchaseDate) throws FileNotFoundException
    {
        Product[] p = getListOfProducts(); //hanakhod kol el products eli fel file
        
        for (int i = 0 ; i < p.length ; i++)
        {
            if (p[i]!=null && productID.equals(p[i].getSearchKey()))
            {
                if (p[i].getQuantity() == 0)
                {
                    return false;
                }
                else {
                    p[i].setQuantity(p[i].getQuantity() - 1);
                    //updated the file products 
                    
                    //add to the customerproducts.txt
                    
                    return true;
                }
            }
        }
        
        System.out.println("Error, the product ID is not found.");
        return false;
    
    }
    
    //helped method 3shan a7sb el ayam abl el purchase date wala 3ada 14 youm wala eh
    public int howManyDays( LocalDate purchaseDate, LocalDate returnDate)
    {
        if ( returnDate.isBefore(purchaseDate)) return -1;
        if (returnDate.isEqual(purchaseDate)) return 0; //allowed 3ady, bs for future use 3mltha terg3le 0
        if (returnDate.isAfter(purchaseDate.plusDays(14))) return -1;
        return 1; //within the 14 days
        
    }
    
     //helper brdo
    public int FoundInProduct (String productID) throws FileNotFoundException{
        
        Product[] p = getListOfProducts();
        
        for (int i = 0 ; i < p.length ; i++)
        {
            if (p[i] != null && p[i].getSearchKey().equals(productID))
            {
                return 1;
            }
        }
        System.out.println("Error, product not found in Products.txt");
        return -1;
    }
    
    //helper brdo
    public int FoundInCustomersProduct (String customerSSN, String productID, LocalDate purchaseDate) throws FileNotFoundException
    {
        CustomerProduct[] c = getListOfPurchasingOperations();
        
        for (int i = 0 ; i < c.length ; i++)
        {
            if (c[i] != null && c[i].getCustomerSSN().equals(customerSSN) && c[i].getProductID().equals(productID) && c[i].getPurchaseDate().equals(purchaseDate))
            {
                return 1;
            }
        }
        return -1;
        
    }
    
    public double returnProduct(String customerSSN, String productID, LocalDate purchaseDate ,LocalDate returnDate) throws FileNotFoundException
    {
        
        if ( howManyDays(purchaseDate,returnDate) == -1 || FoundInProduct(productID) == -1 || FoundInCustomersProduct(customerSSN,productID,returnDate) == -1)
        {
            // cannot return the product
        }
        else {
            //return the product
        }
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //dummy 3shan el errors bss
    private static class CustomerProductDatabase {

        public CustomerProductDatabase() {
        }
    }

    private static class CustomerProduct {

        public CustomerProduct() {
        }

        private CustomerProduct(String string, String string0, String string1, String string2) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private Object getCustomerSSN() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private Object getProductID() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private Object getPurchaseDate() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }
    
    
}