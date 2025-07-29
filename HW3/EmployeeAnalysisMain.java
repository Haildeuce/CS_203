import java.io.IOException;

/**
 * Main class to run the Employee Data Analysis program
 */
public class EmployeeAnalysisMain {
    
    public static void main(String[] args) {
        System.out.println("=== Employee Data Analysis Program ===");
        System.out.println();
        
        // Create an instance of EmployeeDataProcessor
        EmployeeDataProcessor processor = new EmployeeDataProcessor();
        
        try {
            // Load employee data from CSV file
            System.out.println("Loading employee data from CSV file...");
            processor.loadEmployeeData("employee_data.csv");
            
            // Display summary
            processor.displaySummary();
            
            // Execute all analysis tasks
            processor.findLowestPaidEmployeesByDepartment();
            processor.findEmployeesWithLessThanFourYears();
            processor.calculateMarketingAverageSalary();
            processor.findLongestTenureEmployees();
            processor.displayDepartmentsWithLeadCount();
            processor.displayDepartmentsWithAverageSalary();
            
            System.out.println("Analysis completed successfully!");
            
        } catch (IOException e) {
            System.err.println("Error reading the CSV file: " + e.getMessage());
            System.err.println("Please ensure 'employee_data.csv' exists in the current directory.");
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}