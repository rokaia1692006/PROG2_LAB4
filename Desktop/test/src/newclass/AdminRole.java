
package newclass;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;


public class AdminRole {
    private EmployeeUserDatabase database;
    
    public AdminRole(){
        database = new EmployeeUserDatabase();
    }

    public void setDatabase(EmployeeUserDatabase database) {
        this.database = database;
    }

    public EmployeeUserDatabase getDatabase() {
        return database;
    }
    
    public void addEmployee( String employeeID, String name, String email, String address, String phoneNumber) throws FileNotFoundException, IOException
    {
       
        
        EmployeeUser user = new EmployeeUser(employeeID,name,email,address,phoneNumber);
 
        FileWriter writer = new FileWriter("Employees.txt", true); 
        PrintWriter p = new PrintWriter(writer);
        p.println(user.lineRepresentation()); 
        p.close();
        writer.close();
    }
    
    public EmployeeUser[] getListOfEmployees() throws FileNotFoundException {
        
        EmployeeUser[] users = new EmployeeUser[100];
        File file = new File("Employees.txt");
        Scanner scan = new Scanner(file);
        int count=0;
        
       while (scan.hasNextLine()) 
       {
           String line = scan.nextLine();
           String[] splitted = line.split(","); 
           
           if ( splitted.length == 5 ){
               
               users[count++] = new EmployeeUser(splitted[0],splitted[1],splitted[2],splitted[3],splitted[4]);
           }
           
       }
       return users;
    }
        
    public void removeEmployee(String key) throws FileNotFoundException, IOException {
        
        File file = new File("Employees.txt");
        Scanner scan = new Scanner(file);
        FileWriter writer = new FileWriter("Employees.txt", false); 
        int linecount=0;
        String[] line = new String[100];
        String current;
        
        while(scan.hasNextLine())
        {
           current = scan.nextLine();
           String[] splitted = current.split(",");
           
           if (!(splitted[0].equals(key))){
               line[linecount++] = current; 
           }
        }
        
        for (int i = 0 ; i < linecount; i++)
        {
            writer.write(line[i] + "\n");
        }
        writer.close();
    }
        
      public void logout() throws IOException
      {
          FileWriter writer = new FileWriter("Employees.txt",false);
          PrintWriter p = new PrintWriter(writer);
          
          EmployeeUser[] users = getListOfEmployees();
          
          for (int i = 0; i < users.length; i ++)
          {
              if (users[i] != null)
              {p.println(users[i].lineRepresentation());} 
          }
          p.close();
          writer.close();
      }
    
}
