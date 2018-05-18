package com.erp.tipsuwan.db.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.erp.tipsuwan.db.common.DbUtil;
import com.erp.tipsuwan.db.model.Employee;

import java.util.logging.Level;
import java.util.logging.Logger;


public class EmployeeDAO {
	private Connection connection = null;
	
	public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<Employee>();
        try {
        	connection = DbUtil.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from Employees");
            while (rs.next()) {
            	Employee employee = new Employee();
            	employee.setEmployeeID(rs.getInt("employeeID"));
            	employee.setActive(rs.getBoolean("active"));
            	employee.setPassword(rs.getString("password"));
            	employee.setUserRole(rs.getString("userRole"));
            	employee.setFirstName(rs.getString("firstName"));
            	employee.setLastName(rs.getString("lastName"));
            	
            	//System.out.println(employee.toString());

            	employees.add(employee);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;           

    }
	
	// 'synchronized' for mutual exclusion between multiple creations.
	//
	//  employeeID must be 0 or positive integer. If not, it causes exception.
	//  employeeID=0 means creating new employee.
	//  employeeID=1+ means creating new employee if the ID does not exist.  If exist, perform update.
	public synchronized int  createOrUpdateEmployee(Employee employee) {     
		int newEmployeeID = -1;
        try {

        	connection = DbUtil.getConnection();
			
			PreparedStatement preparedStatement = connection.prepareStatement(
					"insert into Employees(employeeID, active, password, userRole, firstName, lastName, email, phoneNumber) " +
					"values (?, ?, ?, ?, ?, ?, ?, ?) " +
					"on duplicate key update " +
					"employeeID = values(employeeID), " +
					"active = values(active), " +
					"password = values(password), " +
					"userRole = values(userRole), " +		
		    		"firstName = values(firstName), " +
		    		"lastName = values(lastName), " +
		    		"email = values(email), " +
					"phoneNumber = values(phoneNumber)", Statement.RETURN_GENERATED_KEYS);
			
			// Parameters start with 1
			preparedStatement.setInt(1, employee.getEmployeeID());
			preparedStatement.setBoolean(2, employee.isActive());
			preparedStatement.setString(3, employee.getPassword());
			preparedStatement.setString(4, employee.getUserRole());
			preparedStatement.setString(5, employee.getFirstName());
			preparedStatement.setString(6, employee.getLastName());
			preparedStatement.setString(7, employee.getEmail());
			preparedStatement.setString(8, employee.getPhoneNumber());

			

			preparedStatement.executeUpdate();			
			
			// Retrieve the row number which is used as orderID
            ResultSet rs1 = preparedStatement.getGeneratedKeys();
            if(rs1.next())
            {
                newEmployeeID = rs1.getInt(1);
                // System.out.println("createOrUpdateEmployee():  newEmployeeID=" + newEmployeeID);
            }
            
            
            
        }catch (SQLException ex) {
        
            Logger lgr = Logger.getLogger(EmployeeDAO.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        }
        
        return newEmployeeID;
        
	} 

}
