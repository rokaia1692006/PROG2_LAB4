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
public class Product {
    private String productID , productName , manufacturerName , supplierName;
    private int quantity; // units available to sell
    private float price; 
    private static HashSet <String> IDS = new HashSet<>();
public final String ValidateID (String productID){
if(IDS.contains(productID)){
    return null;
    
}
else{
IDS.add(productID);
return productID;
}


}
public void loadSet(HashSet <String> ID){
    if(ID  != null ){
        IDS  = ID;
    
    }

}
    public Product(String productID, String productName, String manufacturerName, String supplierName, int quantity, float price) {
        String id = ValidateID(productID);
        if(id == null){
        throw new IllegalArgumentException("DUPLICATE IDS");
        
        }
        this.productID  = id;
        this.productName = productName;
        this.manufacturerName = manufacturerName;
        this.supplierName = supplierName;
        setQuantity(quantity);
        this.price = price;
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
    public String getSaerchKey(){
    return this.productID;
    }
    //ID,NAME,MANUFACTURER,SUPPLIER,QUANT,PRICE
    public String lineRepresentation(){
    String line = this.productID+","+this.productName+","+this.manufacturerName+","+this.supplierName+","+this.quantity+","+this.price;
    return line;
    
    
    
    }
    
   

}
