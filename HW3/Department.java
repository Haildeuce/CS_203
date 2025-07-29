import java.util.ArrayList;
import java.util.List;

/**
 * Department class representing a department in the company
 */
public class Department {
    private String name;
    private List<Employee> employees;
    
    // Constructor
    public Department(String name) {
        this.name = name;
        this.employees = new ArrayList<>();
    }
    
    // Add employee to department
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }
    
    // Get all employees in the department
    public List<Employee> getEmployees() {
        return new ArrayList<>(employees);
    }
    
    // Get department name
    public String getName() {
        return name;
    }
    
    // Calculate average salary in the department
    public double getAverageSalary() {
        if (employees.isEmpty()) return 0.0;
        
        double totalSalary = employees.stream()
                                   .mapToDouble(Employee::getSalary)
                                   .sum();
        return totalSalary / employees.size();
    }
    
    // Get lowest paid employee in the department
    public Employee getLowestPaidEmployee() {
        if (employees.isEmpty()) return null;
        
        Employee lowestPaid = employees.get(0);
        for (Employee emp : employees) {
            if (emp.getSalary() < lowestPaid.getSalary()) {
                lowestPaid = emp;
            }
        }
        return lowestPaid;
    }
    
    // Count number of leads in the department
    public int getLeadCount() {
        int count = 0;
        for (Employee emp : employees) {
            if (emp.isLead()) {
                count++;
            }
        }
        return count;
    }
    
    // Get employee with longest tenure in the department
    public List<Employee> getLongestTenureEmployees() {
        List<Employee> longestTenure = new ArrayList<>();
        if (employees.isEmpty()) return longestTenure;
        
        int maxYears = employees.stream()
                              .mapToInt(Employee::getYearsOfService)
                              .max()
                              .orElse(0);
        
        for (Employee emp : employees) {
            if (emp.getYearsOfService() == maxYears) {
                longestTenure.add(emp);
            }
        }
        return longestTenure;
    }
    
    @Override
    public String toString() {
        return String.format("Department: %s, Employees: %d", name, employees.size());
    }
}