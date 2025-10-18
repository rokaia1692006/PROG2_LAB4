
package prog2_lab4;
import java.util.Scanner;

public class Prog2_lab4 {

   
    public static void main(String[] args) {
        
      
        //test
        Scanner scan = new Scanner(System.in);
        EmployeeUser user = null;
        boolean flag = true;


while (flag) {

    System.out.print("Enter phone number: ");
    String phone = scan.nextLine();

    System.out.print("Enter the name: ");
    String name = scan.next();
    scan.nextLine();

    System.out.print("Enter the address: ");
    String address = scan.next();
    scan.nextLine();

    System.out.print("Enter the employeeID: ");
    String employeeID = scan.next();
    scan.nextLine();
    
    System.out.print("Enter the email: ");
    String email = scan.nextLine();

  
   user = new EmployeeUser(employeeID, name, email, address, phone);

    if (user.getEmployeeID() != null && user.getName() != null &&
        user.getAddress() != null && user.getPhoneNumber() != null && user.getEmail() != null) {
        flag = false;
    } else {
        System.out.println("Some data was invalid, please try again.\n");
    }
     }  
   //tests 3shan el methods
   System.out.println(user.lineRepresentation());
   System.out.println(user.getSearchKey());
    }
    
}
