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
            	employee.setLoginID(rs.getString("loginID"));
            	employee.setActive(rs.getBoolean("active"));
            	employee.setPassword(rs.getString("password"));
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
	public synchronized boolean  createOrUpdateEmployee(Employee employee) {     
		boolean returnResult = false;
        try {

        	connection = DbUtil.getConnection();
			
			PreparedStatement preparedStatement = connection.prepareStatement(
					"insert into Employees(loginID, password,  active, firstName, lastName, email, phoneNumber) " +
					"values (?, ?, ?, ?, ?, ?, ?) " +
					"on duplicate key update " +
					"loginID = values(loginID), " +
					"password = values(password), " +
					"active = values(active), " +						
		    		"firstName = values(firstName), " +
		    		"lastName = values(lastName), " +
		    		"email = values(email), " +
					"phoneNumber = values(phoneNumber)", Statement.RETURN_GENERATED_KEYS);
			
			// Parameters start with 1
			preparedStatement.setString(1, employee.getLoginID());
			preparedStatement.setString(2, employee.getPassword());
			preparedStatement.setBoolean(3, employee.isActive());
			preparedStatement.setString(4, employee.getFirstName());
			preparedStatement.setString(5, employee.getLastName());
			preparedStatement.setString(6, employee.getEmail());
			preparedStatement.setString(7, employee.getPhoneNumber());

			
			// rowCount = 1 for success insert.  rowCount = 2 for success update
			int rowCount = preparedStatement.executeUpdate();
			System.out.println("createOrUpdateEmployee():  rowCount=" + rowCount);
            if(rowCount == 1 || rowCount == 2)
            {
            	returnResult = true;
      
            }
            
            
            
        }catch (SQLException ex) {
        
            Logger lgr = Logger.getLogger(EmployeeDAO.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        }
        
        return returnResult;
        
	} 

}
