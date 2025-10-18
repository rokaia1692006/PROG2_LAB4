package prog2_lab4;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import prog2_lab4.EmployeeUser;
public class EmployeeDatabase extends Database<EmployeeUser> {

public EmployeeDatabase(String fileName) {
super(fileName);
}

@Override
EmployeeUser createRecordFrom(String line) {
String[] parts = line.split(",");
return new EmployeeUser(parts[0], parts[1], parts[2], parts[3], parts[4]);
}

@Override
        
String getSearchKey(EmployeeUser record) {
return record.getSearchkey();
}

@Override
String lineRepresentation(EmployeeUser record) {
return record.lineRepresentation();
}
}
