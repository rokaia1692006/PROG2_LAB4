
package newclass;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;



//dumy classes 3shan bs el error yeroh
public class EmployeeUserDatabase{
    
}
public class EmployeeUser{

    EmployeeUser(String string, String string0, String string1, String string2, String string3) {
        throw new UnsupportedOperationException("Not supported yet."); //el netbeans eli 3mlo 3shan error el constructor bs
    }
    
}




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
        //hanst3ml el construction eli f employeeUser wel linerepresentation() bs mesh dlw2ty
        
        String line1 = employeeID+","+name+","+email+","+address+","+phoneNumber;
 
        FileWriter writer = new FileWriter("Employees.txt", true); //nest3ml append bema en kda kda hanktb f akhr el line
        PrintWriter p = new PrintWriter(writer);
        p.println(line1); //hena hanst3ml el employee.linerepresenation()
        p.close();
        writer.close();
    }
    
    public EmployeeUser[] getListOfEmployees() throws FileNotFoundException {
        
        EmployeeUser[] users = new EmployeeUser[100];
        File file = new File("Employees.txt");
        Scanner scan = new Scanner(file);
        int count=0;
        
       while (scan.hasNextLine()) //tol ma fe lines, hadkhol el loop
       {
           String line = scan.nextLine();
           String[] splitted = line.split(","); //3mlthm splitted 3shan a7othm fel constructor
           
           if ( splitted.length == 5 ){
               //for validity, a check en 5 items separated f3lan 
               users[count++] = new EmployeeUser(splitted[0],splitted[1],splitted[2],splitted[3],splitted[4]);
           }
           
       }
       return users;
    }
        
    public void removeEmployee(String key) throws FileNotFoundException, IOException {
        
        File file = new File("Employees.txt");
        Scanner scan = new Scanner(file);
        FileWriter writer = new FileWriter("Employees.txt", false); //false 3shan ha overwrite
        int linecount=0;
        String[] line = new String[100];
        String current;
        
        while(scan.hasNextLine())
        {
           current = scan.nextLine();
           String[] splitted = current.split(",");
           
           if (!(splitted[0].equals(key))){
               line[linecount++] = current; //ha-store kol el lines ELA el line eli awelo el key 'employeeID'
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
              {p.println(users[i].lineRepresentation());} //el line representation mwgoda fel EmployeeUser, lama a3ml abstract haytshal el error.
          }
          p.close();
          writer.close();
      }
    
}
