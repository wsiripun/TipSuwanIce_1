package tipsuwan;

import java.util.List;

import com.erp.tipsuwan.db.dao.EmployeeDAO;
import com.erp.tipsuwan.db.model.Employee;

public class TestEmployee {
    public static void main(String[] args) {
    	TestEmployee testEmployee = new TestEmployee();
    	testEmployee.getAllEmployees();
    	testEmployee.createOrUpdateEmployee();
    	
    	System.out.println("\n=========   AFTER CREATE OR UPDATE======\n\n");
    	testEmployee.getAllEmployees();

    }
    
    private void getAllEmployees() {
    	EmployeeDAO employeeDAO = new EmployeeDAO();
    	System.out.println("TESTING:  TestEmployee.java");
    	List<Employee> employees = employeeDAO.getAllEmployees();
    	
		for (Employee temp : employees) {
			System.out.println(temp);
		}
    	
    }
    
    
    private void createOrUpdateEmployee() {
    	EmployeeDAO employeeDAO = new EmployeeDAO();
    	Employee emp = new Employee();
    	
    	System.out.println("TESTING: TestEmployee.createOrUpdateEmployee()    loginID=" + emp.getLoginID());
    	

    	emp.setLoginID("newEmpID_4");		// 0 = creating new employee.  1+ = create if not exist. If exist, update
    	emp.setPassword("newEmpID_3_pass");    	
    	emp.setActive(true);
    	emp.setFirstName("newEmpID_3_FN");
    	emp.setLastName("newEmpID_3_LN");
    			
    	boolean result  = employeeDAO.createOrUpdateEmployee(emp);
    	System.out.println ("\n\n==========");
    	System.out.println(" createOrUpdateResult =" + result);
    }

}
