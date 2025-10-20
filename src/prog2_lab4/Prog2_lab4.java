/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prog2_lab4;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
/**
 *
 * @author it
 */
public class Prog2_lab4 {
    public static LocalDate formatter(String date){
     DateTimeFormatter format=DateTimeFormatter.ofPattern("dd-MM-yyyy");
     try{
     LocalDate ld = LocalDate.parse(date, format);
     return ld;
     }catch(Exception e){
     throw new IllegalArgumentException("INVALID DATE");
     
     
     }
    
    }
public static void PRINTRec(Record rec[]){
for(Record r : rec){
System.out.println(r.lineRepresentation());
}

}   
    public static void menu(Scanner scan , AdminRole ad,EmployeeRole emp){
       
         while(true){
            boolean done = false;
            System.out.println("1 to add EMPLOYEE");
            System.out.println("2 to list EMPLOYEEs");
            System.out.println("3 to remove EMPLOYEE");
            System.out.println("4 add PRODUCT");
            System.out.println("5 to list PRODUCTs");
            System.out.println("6 to purchase PRODUCT");
            System.out.println("7 to list PURCHASING OPERATIONs");
            System.out.println("8 to return PRODUCT");
            System.out.println("9 to apply PAYMENT");
            System.out.println("10 to LOGOUT");
            System.out.println("11 to EXIT");
            System.out.print("Enter choice: ");
            
            int c = scan.nextInt();
            scan.nextLine();
            switch (c) {
                case 1:
                    System.out.println("ENTER EMPLOYEE ID: ");
                    String empid = scan.nextLine();
                    System.out.println("ENTER EMPLOYEE NAME : ");
                    String empname = scan.nextLine();
                    System.out.println("ENTER EMPLOYEE EMAIL : ");
                    String empemail = scan.nextLine();
                    System.out.println("ENTER EMPLOYEE ADDRESS : ");
                    String empaddress = scan.nextLine();
                    System.out.println("ENTER EMPLOYEE PHONE : ");
                    String empphone = scan.nextLine();
                    try{
                    ad.addEmployee(empid, empname, empemail, empaddress, empphone);}
                    catch(Exception e){
                        System.err.println("ERROR : "+ e.getMessage());
                        break;
                    
                    }
                    System.out.println("EMPLOYEE ADDED SUCCESSFULLY");
                    
                    break;
                    
                    
                    case 2:
                    System.out.println("LIST OF EMPLOYEES :");
                    try{
                    EmployeeUser[] employees = ad.getListOfEmployees();
                    if(employees.length == 0){
                        System.out.println("NO EMPLOYEES FOUND");
                    } else {
                        PRINTRec(employees);
                    }
                    }
                    catch(Exception e){
                    
                    System.err.println("ERROR : "+ e.getMessage());
                        break;
                    
                    }
                    break;
                    
                    case 3:
                         System.out.println("ENTER EMPLOYEE ID TO REMOVE : ");
                        String remid = scan.nextLine();
                        try{
                        ad.removeEmployee(remid);}
                        catch(Exception e){
                         System.err.println("ERROR : "+ e.getMessage());
                        break;
                        }
                        System.out.println("REMOVED SUCCESSFULLY");
                        break;
                    
                    case 4:
                        System.out.println("ENTER PRODUCT ID : ");
                        String pid = scan.nextLine();
                        System.out.println("ENTER PRODUCT NAME : ");
                        String pname = scan.nextLine();
                        System.out.println("ENTER MANUFACTURER NAME : ");
                        String manu = scan.nextLine();
                        System.out.println("ENTER SUPPLIER NAME : ");
                        String supp = scan.nextLine();
                        System.out.println("ENTER PRODUCT QUANTITY : ");
                        int qty = scan.nextInt();
                        System.out.println("ENTER PRODUCT PRICE : ");
                        float price = scan.nextInt();
                        scan.nextLine();
                        try{
                        emp.addProduct(pid, pname, manu, supp, qty,price);
                            System.out.println("ADDED PRODUCT");
                        
                        }
                        catch(Exception e){
                        System.err.println("ERROR : "+ e.getMessage());
                            break;

                        }
                        break;
                        
                    case 5:
                        System.out.println("LIST PRODUCTS : ");
                        try{
                        Product[] product =emp.getListOfProducts();
                        if(product.length  == 0){
                            System.out.println("NO PRODUCTS");
                        
                        }
                        else{
                            PRINTRec(product);
                        
                        }
                        }catch(Exception e){
                        System.err.println("ERROR : "+ e.getMessage());
                            break;

                        }
                        break;
                        
                        
                        case 6:
                        System.out.println("ENTER CUSTOMER SSN: ");
                        String ssn = scan.nextLine();
                        System.out.println("ENTER PRODUCT ID: ");
                        String prodid = scan.nextLine();
                        
                        try{
                             DateTimeFormatter format=DateTimeFormatter.ofPattern("dd-MM-yyyy");
                        LocalDate pdate = formatter(LocalDate.now().format(format));
                        try{
                    boolean success = emp.purchaseProduct(ssn, prodid, pdate);
                    if(success){
                        System.out.println("PURCHASE SUCCESSFUL");
                    } else {
                        System.out.println("PURCHASE FAILED");
                    }}
                    catch(Exception e){
                     System.err.println("ERROR : "+ e.getMessage());
                            break;

                    }
                        }
                        catch(Exception e){
                            System.err.println("INVALID DATE");
                            break;
                        }
                    
                    break;
                        case 7:
                            try{
                            CustomerProduct[] LPO = emp.getListOfPurchasingOperations();
                            if(LPO.length == 0){
                                System.out.println("NO PURCHASES");
                            }
                            else{
                                PRINTRec(LPO);
                            }}
                            catch(Exception e){  System.err.println("ERROR : "+ e.getMessage());
                            break;}
                            break;
                            
                        
                case 8:
                    System.out.println("ENTER CUSTOMER SSN: ");
                    String rssn = scan.nextLine();
                    System.out.println("ENTER PRODUCT ID: ");
                    String rpid = scan.nextLine();
                    System.out.println("ENTER PURCHASE DATE (DD-MM-YYYY): ");
                    LocalDate purdate = formatter(scan.nextLine());
                    System.out.println("ENTER RETURN DATE (DD-MM-YYYY): ");
                    try{
                             DateTimeFormatter format=DateTimeFormatter.ofPattern("dd-MM-yyyy");
                        LocalDate retdate = formatter(LocalDate.now().format(format));
                    try{
                    double refund = emp.returnProduct(rssn, rpid, purdate, retdate);
                    if(refund == -1){
                        System.out.println("RETURN FAILED");
                    } else {
                        System.out.println("REFUND AMOUNT: " + refund);
                    }}
                    catch(Exception e){
                    System.err.println("ERROR : "+ e.getMessage());
                    break;
                    }}
                    catch(Exception e){
                        System.err.println("ERROR DATE INCORRECT");
                    }
                    break;
                    
                case 9:
                    System.out.println("ENTER CUSTOMER SSN: ");
                    String pssn = scan.nextLine();
                    System.out.println("ENTER PURCHASE DATE (DD-MM-YYYY): ");
                    LocalDate paydate = formatter(scan.nextLine());
                    try{
                    boolean paid = emp.applyPayment(pssn, paydate);
                    if(paid){
                        System.out.println("PAYMENT APPLIED SUCCESSFULLY");
                    } else {
                        System.out.println("PAYMENT FAILED");
                    }}
                    catch(Exception e){
                    System.err.println("ERROR : "+ e.getMessage());
                    break;
                    }
                    break;
                    
                case 10:
                    emp.logout();
                    ad.logout();
                    System.out.println("LOGGING OUT...");
                   
                    break;
                    
                case 11:
                    System.err.println("EXITING SYSTEM");
                    System.exit(0);
                    break;
                    
                default:
                    System.out.println("INVALID CHOICE");
                    break;
            }
           
        
        
        
        }
    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scan =  new Scanner(System.in);
        AdminRole ad = new AdminRole();
        EmployeeRole emp = new EmployeeRole();
       menu(scan,ad,emp);
    }
}
