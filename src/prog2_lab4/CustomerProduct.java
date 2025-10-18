/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2_lab4;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 *
 * @author malak
 */
public class CustomerProduct {
    private String customerSSN, productID;
    private LocalDate purchaseDate;
    private boolean paid;

    public CustomerProduct(String customerSSN, String productID, LocalDate purchaseDate) {
        setCustomerSSN(customerSSN);
        this.productID = productID;
        this.purchaseDate = purchaseDate;
    }
    public String lineRepresentation(){
        return getCustomerSSN()+","+getProductID()+","+getPurchaseDate()+","+isPaid();
    }
    public String getSearchKey(){
        DateTimeFormatter format=DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return getCustomerSSN()+","+getProductID()+","+getPurchaseDate().format(format);
    }
    public String getCustomerSSN() {
        return customerSSN;
    }

    public void setCustomerSSN(String customerSSN) {
        if(customerSSN==null||customerSSN.trim().isEmpty())
            System.out.println("Error. Empty SSN.");
        else if(customerSSN.length()!=10){
            System.out.println("Error. SSN must be ten digits.");
        }
        else{
            for(int i=0;i<10;i++){
                if(!Character.isDigit(customerSSN.charAt(i))){
                    System.out.println("Error. SSN must be ten digits.");
                    return;
                }
            }
            this.customerSSN = customerSSN;
        }
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
    
    
}
