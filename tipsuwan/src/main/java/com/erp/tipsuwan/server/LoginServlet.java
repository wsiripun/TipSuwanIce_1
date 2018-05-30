package com.erp.tipsuwan.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;


@Path("loginmgr")
public class LoginServlet {
	
	//http://localhost:591/tipsuwan/loginmgr/tutorial/helloname/abc_444  chrome
	//http://localhost:591/tipsuwan/loginmgr/tutorial/helloname/abc_444
	
	// http://localhost:591/tipsuwan/loginmgr/login
	// @Consumes(MediaType.APPLICATION_FORM_URLENCODED )
    @GET
    @Path("login")  
    public void login( @Context HttpServletRequest request, @Context HttpServletResponse response) 
    														throws ServletException, IOException {
        // The redirection to "222 also worked in May 2018
    	//response.setHeader("Location", "/tipsuwan/tutorial/helloname/222");
        //response.sendRedirect("/tipsuwan/tutorial/helloname/222");
        
        response.setHeader("Location", "/tipsuwan/main.html");
        response.sendRedirect("/tipsuwan/main.html");
    	
    }
    
    
    
//	How to get ID and Role on client side    
//    
//		$.post("/tipsuwan/loginmgr/getIDandRole",
//		        function(data,status){
//		            alert("Data: " + data + "\nStatus: " + status);
//		         });  
    @GET
    @Path("getIDandRole")
    public String getIDandRole( @Context HttpServletRequest request, @Context HttpServletResponse response) 
			throws ServletException, IOException {
    	// Get userName from the login session.
    	String userName = request.getRemoteUser();		// e.g., userName=sale1
    	System.out.println("USER NAME=" + userName);
    	
    	// TODO: Use 'userName' to retrieve the roles from DB.  	<===   TODO
    	// Return hard code for now.
    	return "sale1:Sale";
    	
    }
    
    
    @GET
    @Path("logout")
    public String logout( @Context HttpServletRequest request, @Context HttpServletResponse response) 
			throws ServletException, IOException {
    	
    	request.getSession().invalidate();		// user needs to login again after invalidate
    	return "logoutSuccess";
    	
    }
    

}







