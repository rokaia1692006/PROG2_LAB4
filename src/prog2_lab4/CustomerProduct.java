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
public class CustomerProduct implements Record{
    private String customerSSN, productID;
    private LocalDate purchaseDate;
    private boolean paid;

    public CustomerProduct(String customerSSN, String productID, LocalDate purchaseDate) {
        setCustomerSSN(customerSSN);
        setProductID(productID);
        setPurchaseDate(purchaseDate);
    }
    @Override
    public String lineRepresentation(){
        DateTimeFormatter format=DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return getCustomerSSN()+","+getProductID()+","+getPurchaseDate().format(format)+","+isPaid();
    }
    @Override
    public String getSearchKey(){
        DateTimeFormatter format=DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return getCustomerSSN()+","+getProductID()+","+getPurchaseDate().format(format);
    }
    public String getCustomerSSN() {
        return customerSSN;
    }
    public boolean isEmpty(String validate){
        if(validate==null||validate.trim().isEmpty())
            return true;
        else
            return false;
    }
    public void setCustomerSSN(String customerSSN) {
        if(isEmpty(customerSSN))
            throw new IllegalArgumentException("Error. Empty SSN.");
        else if(customerSSN.length()!=10)
            throw new IllegalArgumentException("Error. SSN must be ten digits.");
        else{
            for(int i=0;i<customerSSN.length();i++){
                if(!Character.isDigit(customerSSN.charAt(i))){
                    throw new IllegalArgumentException("Error. SSN must be ten digits.");
                }
            }
            this.customerSSN = customerSSN;
        }
    }

    public String getProductID() {
        return productID;
    }
    public void setProductID(String productID) {
        if(isEmpty(productID))
            throw new IllegalArgumentException("Error. Empty product ID.");
        else if(productID.length()!=5)
            throw new IllegalArgumentException("Error. Product ID must be P/p followed by four digits.");
        else if(productID.charAt(0)!='P'&&productID.charAt(0)!='p')
            throw new IllegalArgumentException("Error. Product ID must be P/p followed by four digits.");
        else{
            for(int i=1;i<productID.length();i++){
                if(!Character.isDigit(productID.charAt(i))){
                    throw new IllegalArgumentException("Error. Product ID must be P/p followed by four digits.");
                }
            }
            this.productID = productID;
    }
}
    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        if(purchaseDate==null)
            throw new IllegalArgumentException("Error. Purchase date can't be empty.");
        else if(purchaseDate.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("Error. Purchase date can't be in the future.");
        this.purchaseDate=purchaseDate;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
    
    
}