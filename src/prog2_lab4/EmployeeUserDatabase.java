/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2_lab4;

public class EmployeeUserDatabase extends Database {

    public EmployeeUserDatabase(String fileName) {
        super(fileName);
    }

    @Override
    public Record createRecordFrom(String line) {
        String[] parts = line.split(",");
        return new EmployeeUser(parts[0], parts[1], parts[2], parts[3], parts[4]);
    }
}   
