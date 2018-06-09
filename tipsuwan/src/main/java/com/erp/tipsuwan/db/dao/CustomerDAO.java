package com.erp.tipsuwan.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.erp.tipsuwan.db.common.DbUtil;
import com.erp.tipsuwan.db.common.Version;
import com.erp.tipsuwan.db.model.Customer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerDAO {
	
	private Connection connection = null;
	
	public List<Customer> getAllCustomers() {
        List<Customer> Customers = new ArrayList<Customer>();
        try {
        	connection = DbUtil.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from Customers");
            while (rs.next()) {
            	Customer Customer = new Customer();
            	Customer.setCustomerID(rs.getInt("customerID"));
            	Customer.setActive(rs.getBoolean("active"));
            	Customer.setEmail(rs.getString("email"));
            	Customer.setFirstName(rs.getString("firstName"));
            	Customer.setLastName(rs.getString("lastName"));
            	Customer.setPhoneNumber(rs.getString("phoneNumber"));
            	Customer.setOutstandingMoneyBalance(rs.getFloat("outstandingMoneyBalance"));
            	Customer.setOutstandingBagBalance(rs.getInt("oustandingBagBalance"));
            	
            	//System.out.println(Customer.toString());

            	Customers.add(Customer);

            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("getAllCustomers():  ERROR SQLException  error_message=" + e.getMessage());
            Logger lgr = Logger.getLogger(Version.class.getName());
            lgr.log(Level.SEVERE, e.getMessage(), e);
        }

        return Customers;           
    }
	
	// 'synchronized' for mutual exclusion between multiple creations.
	public synchronized boolean  createOrUpdateCustomerInfo(Customer customer, String role) {     
		boolean returnResult = false;
        try {

        	connection = DbUtil.getConnection();
			
			PreparedStatement preparedStatement = connection.prepareStatement(
					"insert into Customers_Roles(loginID, role_name) " +
					"values (?, ?) " +
					"on duplicate key update " +
					"loginID = values(loginID), " +
					"role_name = values(role_name) ", Statement.RETURN_GENERATED_KEYS);
			
			// Parameters start with 1
			preparedStatement.setInt(1, customer.getCustomerID());
			preparedStatement.setString(2, role);

			
			// rowCount = 1 for success insert.  rowCount = 2 for success update
			int rowCount = preparedStatement.executeUpdate();
			System.out.println("createOrUpdateCustomerRole():  rowCount=" + rowCount);
            if(rowCount == 1 || rowCount == 2)
            {
            	returnResult = true;
      
            }
            
        }catch (SQLException ex) {
            Logger lgr = Logger.getLogger(CustomerDAO.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return returnResult;
        
	} 
}
