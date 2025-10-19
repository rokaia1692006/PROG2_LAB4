
package prog2_lab4;

public class EmployeeUser implements Record {
    
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
    public void setEmployeeID(String employeeID)
    
    {
        
        EmployeeDatabase emp = new EmployeeDatabase("Employees.txt");
        if (!emp.contains(employeeID)){
        this.employeeID = employeeID; 
        }
        else{
            System.out.println("Employee ID is not unique; it must be unique.");
        }
    }

    public void setName(String name)  {
        
        if ( isValid(name) )
        {this.name = name;
        }
        
        else
        { 
            throw new IllegalArgumentException("Error in name; empty name or incorrect format.");
            
            
        }
    }
    public void setAddress(String address) {
        if (isValid(address)){
        this.address = address;
       
        }
        else {
            throw new IllegalArgumentException("Error in name; empty address or incorrect format.");
     
        }
    }

    public void setEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"; 
        if (email.matches(regex)){
        this.email = email;
        }
        else {
            throw new IllegalArgumentException("Invalid email.");
        }
    }


    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber.length() != 11 || !(phoneNumber.startsWith("0")))
        {
            throw new IllegalArgumentException("Invalid phone number.");
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
     
    @Override
    public String lineRepresentation()
    {
        return employeeID+","+name+","+email+","+address+","+phoneNumber;
    }
    
    @Override
    public String getSearchKey()
    {
        return getEmployeeID();

    }

}

