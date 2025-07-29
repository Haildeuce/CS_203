/**
 * Employee class representing an individual employee
 * Encapsulates employee data and provides methods for data access
 */
public class Employee {
    private String name;
    private String department;
    private double salary;
    private int yearsOfService;
    private String position;
    private int employeeId;
    
    // Constructor
    public Employee(int employeeId, String name, String department, double salary, 
                   int yearsOfService, String position) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.yearsOfService = yearsOfService;
        this.position = position;
    }
    
    // Getter methods
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }
    public int getYearsOfService() { return yearsOfService; }
    public String getPosition() { return position; }
    public int getEmployeeId() { return employeeId; }
    
    // Setter methods
    public void setName(String name) { this.name = name; }
    public void setDepartment(String department) { this.department = department; }
    public void setSalary(double salary) { this.salary = salary; }
    public void setYearsOfService(int yearsOfService) { this.yearsOfService = yearsOfService; }
    public void setPosition(String position) { this.position = position; }
    
    // Check if employee is a lead (assuming leads have "Lead" or "Manager" in their position)
    public boolean isLead() {
        return position.toLowerCase().contains("senior") || 
               position.toLowerCase().contains("manager");
    }
    
    // toString method for displaying employee information
    @Override
    public String toString() {
        return String.format("ID: %d, Name: %s, Department: %s, Salary: $%.2f, " +
                           "Years of Service: %d, Position: %s", 
                           employeeId, name, department, salary, yearsOfService, position);
    }
    
    // equals method for comparison
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Employee employee = (Employee) obj;
        return employeeId == employee.employeeId;
    }
}