import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * EmployeeDataProcessor class handles CSV file reading and data analysis
 */
public class EmployeeDataProcessor {
    private List<Employee> employees;
    private Map<String, Department> departments;
    
    // Constructor
    public EmployeeDataProcessor() {
        this.employees = new ArrayList<>();
        this.departments = new HashMap<>();
    }
    
    /**
     * Load employee data from CSV file
     */
    public void loadEmployeeData(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean isFirstLine = true;
            int employeeIdCounter = 1;
            
            while ((line = br.readLine()) != null) {
                // Skip header line
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                
                String[] data = line.split(",");
                if (data.length >= 7) {
                    try {
                        int id = employeeIdCounter++;
                        String firstName = data[0].trim();
                        String lastName = data[1].trim();
                        String name = firstName + " " + lastName;
                        String department = data[2].trim();
                        String position = data[3].trim();
                        double salary = Double.parseDouble(data[4].trim());
                        int yearsOfService = Integer.parseInt(data[5].trim());
                        String leadStatus = data[6].trim();
                        
                        Employee employee = new Employee(id, name, department, salary, 
                                                       yearsOfService, position);
                        employees.add(employee);
                        
                        // Add to department
                        departments.computeIfAbsent(department, Department::new)
                                  .addEmployee(employee);
                        
                    } catch (NumberFormatException e) {
                        System.err.println("Error parsing line: " + line);
                    }
                }
            }
        }
    }
    
    /**
     * Identify the lowest-paid employee in each department
     */
    public void findLowestPaidEmployeesByDepartment() {
        System.out.println("===  Lowest-Paid Employee by Department ===");
        
        for (Department dept : departments.values()) {
            Employee lowestPaid = dept.getLowestPaidEmployee();
            if (lowestPaid != null) {
                System.out.printf("Department: %s - %s (Salary: $%.2f)%n", 
                                dept.getName(), lowestPaid.getName(), lowestPaid.getSalary());
            }
        }
        System.out.println();
    }
    
    /**
     *  List employees with less than 4 years of service
     */
    public void findEmployeesWithLessThanFourYears() {
        System.out.println("===  Employees with Less Than 4 Years of Service ===");
        
        List<Employee> newEmployees = employees.stream()
                                              .filter(emp -> emp.getYearsOfService() < 4)
                                              .collect(Collectors.toList());
        
        if (newEmployees.isEmpty()) {
            System.out.println("No employees found with less than 4 years of service.");
        } else {
            for (Employee emp : newEmployees) {
                System.out.println(emp);
            }
        }
        System.out.println();
    }
    
    /**
     *  Calculate average salary in Marketing department
     */
    public void calculateMarketingAverageSalary() {
        System.out.println("===  Average Salary in Marketing Department ===");
        
        Department marketing = departments.get("Marketing");
        if (marketing != null) {
            double avgSalary = marketing.getAverageSalary();
            System.out.printf("Average salary in Marketing: $%.2f%n", avgSalary);
        } else {
            System.out.println("Marketing department not found.");
        }
        System.out.println();
    }
    
    /**
     *  Find employee(s) with longest tenure
     */
    public void findLongestTenureEmployees() {
        System.out.println("===  Employee(s) with Longest Tenure ===");
        
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }
        
        int maxYears = employees.stream()
                               .mapToInt(Employee::getYearsOfService)
                               .max()
                               .orElse(0);
        
        List<Employee> longestTenure = employees.stream()
                                               .filter(emp -> emp.getYearsOfService() == maxYears)
                                               .collect(Collectors.toList());
        
        System.out.printf("Longest tenure: %d years%n", maxYears);
        for (Employee emp : longestTenure) {
            System.out.println(emp);
        }
        System.out.println();
    }
    
    /**
     *  Display departments with number of leads
     */
    public void displayDepartmentsWithLeadCount() {
        System.out.println("===  Departments with Lead Count ===");
        
        for (Department dept : departments.values()) {
            int leadCount = dept.getLeadCount();
            System.out.printf("Department: %s - Leads: %d%n", dept.getName(), leadCount);
        }
        System.out.println();
    }
    
    /**
     *  Display departments with average salary
     */
    public void displayDepartmentsWithAverageSalary() {
        System.out.println("===  Departments with Average Salary ===");
        
        for (Department dept : departments.values()) {
            double avgSalary = dept.getAverageSalary();
            System.out.printf("Department: %s - Average Salary: $%.2f%n", 
                            dept.getName(), avgSalary);
        }
        System.out.println();
    }
    
    /**
     * Display summary statistics
     */
    public void displaySummary() {
        System.out.println("=== Data Summary ===");
        System.out.printf("Total Employees: %d%n", employees.size());
        System.out.printf("Total Departments: %d%n", departments.size());
        System.out.println("Departments: " + String.join(", ", departments.keySet()));
        System.out.println();
    }
}