
package prog2_lab4;

public class EmployeeUser {
    
    private String employeeID;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;
    
    public EmployeeUser(String employeeID, String name, String email, String address, String phoneNumber)
    {
        setEmployeeID(employeeID);
        setName(name);
        setEmail(email);
        setAddress(address);
        setPhoneNumber(phoneNumber);
    }

    private boolean isValid(String tocheck)
    {
        return !(tocheck == null || tocheck.isEmpty() || !tocheck.matches("[A-Za-z]+")); 
        
    }
    public void setEmployeeID(String employeeID) {
        
        EmployeeUserDatabase db = new EmployeeUserDatabase();
        if (!db.contains(employeeID)){
        this.employeeID = employeeID; 
        }
        else{
            System.out.println("Employee ID is not unique; it must be unique.");
        }
    }

    public void setName(String name) {
        
        if ( isValid(name) )
        {this.name = name;
        }
        
        else
        { 
            System.out.println("Error in name; empty name or incorrect format.");
            
        }
    }
    public void setAddress(String address) {
        if (isValid(address)){
        this.address = address;
       
        }
        else {
            System.out.println("Error in address, empty address or incorrect format.");
     
        }
    }

    public void setEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"; 
        if (email.matches(regex)){
        this.email = email;
        return;
        }
        System.out.println("Error, invalid email.");
    }


    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber.length() != 11 || !(phoneNumber.startsWith("0")))
        {
            System.out.println("Error, incorrect phone number.");
            return;
        }
        this.phoneNumber = phoneNumber;
       
    }
    public String getEmployeeID() {
        return employeeID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
     
    public String lineRepresentation()
    {
        return employeeID+","+name+","+email+","+address+","+phoneNumber;
    }
    
    public String getSearchKey()
    {
        return getEmployeeID();

    }

}

