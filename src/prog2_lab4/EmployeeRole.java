package prog2_lab4;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;




public class EmployeeRole {

     private ProductDatabase productsDatabase;
     private CustomerProductDatabase customerProductDatabase;
     final static float DEFAULT_PRICE = 100;
     
     
    public EmployeeRole() {
        productsDatabase = new ProductDatabase("Products.txt");
        customerProductDatabase = new CustomerProductDatabase("CustomerProduct.txt");
    }

    public void addProduct(String productID, String productName, String manufacturerName, String supplierName,int quantity,float price) throws IOException{
        
        
        Product product = new Product(productID,productName,manufacturerName,supplierName,quantity,price);
         productsDatabase.createRecordFrom(product.lineRepresentation());
         productsDatabase.insertRecord(product);
         
    }
    
    public Product[] getListOfProducts() throws FileNotFoundException{
        
       ArrayList<Product> p = (ArrayList<Product>)(ArrayList<?>) productsDatabase.returnAllRecords(); 
       Product[] product = p.toArray(Product[]::new);
       return product;
    }
    
    public CustomerProduct[] getListOfPurchasingOperations() throws FileNotFoundException{
        
        ArrayList<CustomerProduct> c = (ArrayList<CustomerProduct>) (ArrayList<?>) customerProductDatabase.returnAllRecords();
        CustomerProduct[] cp = c.toArray(CustomerProduct[]::new);
        return cp;
    }

    
    public boolean purchaseProduct ( String customerSSN, String productID, LocalDate purchaseDate) throws FileNotFoundException
    {
        Product product = (Product) productsDatabase.getRecord(productID);

                if (product.getQuantity() == 0)
                {
                    return false;
                }
                else {
                    DateTimeFormatter format=DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    String KEY = customerSSN+","+productID+","+purchaseDate.format(format);
                    if(customerProductDatabase.contains(KEY)){
                    throw new IllegalArgumentException("CANT PURCHASE ITEM TWICE ON THE SAME DAY");
                    }else{
                    product.setQuantity(product.getQuantity() - 1);
                    
                    CustomerProduct cp = new CustomerProduct(customerSSN,productID,purchaseDate);
                    cp.setPaid(false);
                    customerProductDatabase.insertRecord(cp);
                    
                    
                    System.out.println("Eeverything successful.");
                    return true;}
                }
     
    
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
    
    
    public double returnProduct(String customerSSN, String productID, LocalDate purchaseDate ,LocalDate returnDate) throws FileNotFoundException
    {
        CustomerProduct c = new CustomerProduct(customerSSN,productID,purchaseDate);
        String line = c.getSearchKey();
        if ( howManyDays(purchaseDate,returnDate) == -1 || !productsDatabase.contains(productID) || !customerProductDatabase.contains(line))
        {
            return -1;
        }
        else {
            CustomerProduct cp = new CustomerProduct(customerSSN, productID, purchaseDate);
             String key = cp.getSearchKey();
            customerProductDatabase.deleteRecord(key);
      
            Product product = (Product) productsDatabase.getRecord(productID); 
           
            if (product == null) {
                return -1;
            }
            product.setQuantity(product.getQuantity() + 1); 
            double price = product.getPrice();
            
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
        


         System.out.println("Successful set up for the payment.");
         return true;
        
        
    }
    
    
    public void logout() 
    {
       productsDatabase.saveToFile();
       customerProductDatabase.saveToFile();

    }
    
   
    
 
}