package tipsuwan;

import java.util.List;

import com.erp.tipsuwan.db.dao.EmployeeDAO;
import com.erp.tipsuwan.db.model.Employee;

public class TestEmployee {
    public static void main(String[] args) {
    	TestEmployee testEmployee = new TestEmployee();
    	testEmployee.getAllEmployees();
    	testEmployee.createOrUpdateEmployee();

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
    	
    	System.out.println("TESTING: TestEmployee.createOrUpdateEmployee()    EmpID=" + emp.getEmployeeID());
    	

    	emp.setEmployeeID(0);		// 0 = creating new employee.  1+ = create if not exist. If exist, update
    	emp.setActive(true);
    	emp.setPassword("empNEW_pw");
    	emp.setUserRole("Driver");
    	emp.setFirstName("emp_T4_firstname");
    	emp.setLastName("empLastName");
    			
    	int i  = employeeDAO.createOrUpdateEmployee(emp);
    	System.out.println ("\n\n==========");
    	System.out.println(" NEW EMP ID =" + i);
    }

}
