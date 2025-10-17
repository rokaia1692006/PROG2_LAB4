
package newclass;

//dummy class 3shan bs el error yeroh
public class EmployeeUserDatabase{
    
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
    
}
