

package prog2_lab4;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class AdminRole {
    
    private EmployeeUserDatabase database;
    
    public AdminRole(){
        database = new EmployeeUserDatabase("Employees.txt");
    }
    
    public void addEmployee( String employeeID, String name, String email, String address, String phoneNumber) throws FileNotFoundException, IOException
    {
       EmployeeUser user = new EmployeeUser(employeeID,name,email,address,phoneNumber);
       String line = user.lineRepresentation();
       if(!database.contains(employeeID)){
       database.createRecordFrom(line);
       database.insertRecord(user);}
       else{
       throw new IllegalArgumentException("ID ALREADY EXISTS");
       
       }
       
        
    }
    
    public EmployeeUser[] getListOfEmployees() throws FileNotFoundException {
        
        ArrayList<EmployeeUser> u = (ArrayList<EmployeeUser>) (ArrayList<?>) database.returnAllRecords();
        EmployeeUser[] user = u.toArray(EmployeeUser[]::new);
        return user;
        
      }
    
        
    public void removeEmployee(String key) throws FileNotFoundException, IOException {
        
        database.deleteRecord(key);
    }
        
      public void logout() 
      {
          database.saveToFile();
      }
    
}

