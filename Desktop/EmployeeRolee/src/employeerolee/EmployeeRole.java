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
        
        
        Product product = new Product(productID,productName,manufacturerName,supplierName,quantity);
 
        FileWriter writer = new FileWriter("Products.txt", true); 
        PrintWriter p = new PrintWriter(writer);
        p.println(product.lineRepresentation()); 
        p.close();
        writer.close(); 
        
    }
    
    public Product[] getListOfProducts() throws FileNotFoundException{
        
        Product[] product = new Product[100];
        File file = new File("Products.txt");
        Scanner scan = new Scanner(file);
        int count=0;
        
       while (scan.hasNextLine()) 
       {
           String line = scan.nextLine();
           String[] splitted = line.split(","); 
           
           if ( splitted.length == 5 ){
             
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
        
       while (scan.hasNextLine()) 
       {
           String line = scan.nextLine();
           String[] splitted = line.split(","); 
           
           if ( splitted.length == 4 ){
               
               customerproduct[count++] = new CustomerProduct(splitted[0],splitted[1],splitted[2],splitted[3]);
           }
           
       }
       return customerproduct;
    }

    
    public boolean purchaseProduct ( String customerSSN, String productID, LocalDate purchaseDate) throws FileNotFoundException
    {
        Product[] p = getListOfProducts(); 
        
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
                    productsDatabase.deleteRecord(productID);
                    productsDatabase.createRecord(p[i]);
                    productsDatabase.saveToFile();
                    
                    CustomerProduct cp = new CustomerProduct(customerSSN,productID,purchaseDate);
                    cp.setPaid(false);
                    customerProductDatabase.createRecord(cp.lineRepresentation());
                    customerProductDatabase.saveToFile();
                    
                    
                    return true;
                }
            }
        }
        
        System.out.println("Error, the product ID is not found.");
        return false;
    
    }
    
    
    public int howManyDays( LocalDate purchaseDate, LocalDate returnDate)
    {
        if ( returnDate.isBefore(purchaseDate)) {
            System.out.println("Error, Return date is before the Purchase date.");
            return -1;
            
        }
        if (returnDate.isEqual(purchaseDate)) return 0; 
        if (returnDate.isAfter(purchaseDate.plusDays(14))) {
            System.out.println("Error, 14 days passed since the Purchase date, cannot return.");
            return -1;
            
        }
        return 1; 
        
    }
    
     
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
    
    
    public int FoundInCustomerProduct (String customerSSN, String productID, LocalDate purchaseDate) throws FileNotFoundException
    {
        CustomerProduct[] c = getListOfPurchasingOperations();
        
        for (int i = 0 ; i < c.length ; i++)
        {
            if (c[i] != null && c[i].getCustomerSSN().equals(customerSSN) && c[i].getProductID().equals(productID) && c[i].getPurchaseDate().equals(purchaseDate))
            {
                return 1;
            }
        }
        System.out.println("Error, cannot find the customer details in CustomerProduct.txt ");
        return -1;
        
    }
    
    public double returnProduct(String customerSSN, String productID, LocalDate purchaseDate ,LocalDate returnDate) throws FileNotFoundException
    {
        
        if ( howManyDays(purchaseDate,returnDate) == -1 || FoundInProduct(productID) == -1 || FoundInCustomerProduct(customerSSN,productID,purchaseDate) == -1)
        {
            return -1;
        }
        else {
            CustomerProduct cp = new CustomerProduct(customerSSN, productID, purchaseDate);
             String key = cp.lineRepresentation();
            customerProductDatabase.deleteRecord(key);
            customerProductDatabase.saveToFile();
      
            Product product = productsDatabase.getRecord(productID); 
           
            if (product == null) {
                return -1;
            }
            product.setQuantity(product.getQuantity() + 1); 
            String line = product.lineRepresentation();
            String[] split = line.split(",");
            double price = Double.parseDouble(split[5]);
           
            productsDatabase.saveToFile();
           
            return price;
        }
        
    } 
    
    public boolean applyPayment( String customerSSN, LocalDate purchaseDate) throws FileNotFoundException
    {
        CustomerProduct[] cp = getListOfPurchasingOperations(); 
        CustomerProduct wanted = null;
        for (int i = 0 ; i < cp.length ; i++)
        {
            if (cp[i] != null && cp[i].getCustomerSSN().equals(customerSSN) && cp[i].getPurchaseDate().equals(purchaseDate))
            {
                wanted = cp[i]; 
            }
            
        }
        
        if (wanted == null) {
            System.out.println("Couldn't find the record with the given parameters.");
            return false;
        }
        
        if (wanted.isPaid())
        {
            System.out.println("Product is already updated to 'paid'");
            return true; 
        }
        
        wanted.setPaid(true);
        
        customerProductDatabase.saveToFile();


         System.out.println("Successful set up for the payment.");
         return true;
        
        
    }
    
    
    public void logout() 
    {
       productsDatabase.saveToFile();
       customerProductDatabase.saveToFile();

    }
    
   
    
 
}