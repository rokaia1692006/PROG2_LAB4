/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2_lab4;

import java.util.HashSet;

/**
 *
 * @author it
 */
// all info from products.txt but thats not my issue rn 
//ID,NAME,MANUFACTURER,SUPPLIER,QUANT,PRICE
//Note: The product id is unique for each product ok...
//P2394,Laptop,Apple,TechSupplier,10,1500
//uer takes a unit and quantity dec by 1 AND THEY RETURN IT why?
// the products apperntly have a return policy
// 0 = all products sold
// one constructor takes all 
// contains 4 methods getquantity() setQauntity() 
// getSaerchKey return product id
//>lineRepresentation "" prints data of product coma seperated ""
public class Product implements Record{
    private String productID , productName , manufacturerName , supplierName;
    private int quantity; // units available to sell
    private float price; 
   





    public Product(String productID, String productName, String manufacturerName, String supplierName, int quantity, float price) {
        
       
           if(!validateName(productName)  || !validateName(manufacturerName)  || !validateName(supplierName) ){
        throw new IllegalArgumentException("NAME CANNOT CONTAIN NUMBERS OR SPECIAL CHARACHTER");
        }
        
        if(!validatePrice(price)){
        throw new IllegalArgumentException("PRICE CANNOT BE ZERO OR NEGATIVE");
        
        }
     
        this.productID  = productID;
        this.productName = productName;
        this.manufacturerName = manufacturerName;
        this.supplierName = supplierName;
        setQuantity(quantity);
        this.price = price;
       
    }
    private boolean validatePrice(float price){
    if(price <= 0){
    return false;
    
    }
    else {
    return true;
    
    }
    
    }
private boolean validateName(String name){

if(name.matches("^[a-zA-Z]+$")){
return true;


}
else{
    
    return false;
}



}
    public int getQuantity() {
        return quantity;
    }

    public final void setQuantity(int quantity) {
        if(quantity < 0){
            System.err.println("NEGATIVE QUANTITY ");
            this.quantity = 0;
        }
        else{
        this.quantity = quantity;
        }
    }
    @Override
    public String getSearchKey(){
    return this.productID;
    }
    @Override
    //ID,NAME,MANUFACTURER,SUPPLIER,QUANT,PRICE
    public String lineRepresentation(){
    String line = this.productID+","+this.productName+","+this.manufacturerName+","+this.supplierName+","+this.quantity+","+this.price;
    return line;
    
    
    
    }
    
   


}
