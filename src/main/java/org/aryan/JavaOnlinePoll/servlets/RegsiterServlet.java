package org.aryan.JavaOnlinePoll.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

import org.aryan.JavaOnlinePoll.service.Auth;
import org.aryan.JavaOnlinePoll.service.ConnectDB;



public class RegsiterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username, password;
		
		username = request.getParameter("username");
		password = request.getParameter("password");
		
		Auth auth = new Auth();
		
		boolean result = auth.authenticate(username, password);
		
		if(result) {
			ConnectDB createConn = new ConnectDB();
	        Connection conn = createConn.createConnection();
	        
	        if(conn != null) {
	        	try {
	        		String insertQuery = "INSERT INTO users (username, password) VALUES (?, ?)";
	        		PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);
	        		preparedStatement.setString(1, username);
	                preparedStatement.setString(2, password);
	                int rowsInserted = preparedStatement.executeUpdate();
	                
	                if (rowsInserted > 0) { 
	                	request.setAttribute("hasMessage", true);
	                	request.setAttribute("message", "You have registered successfully. Please Login to continue to our website");
	                	request.getRequestDispatcher("/Login.jsp").forward(request,response);
	                    return;
	                } else {
	                	 request.setAttribute("hasError", true);
	                	 request.setAttribute("errorMessage", "Unable to register. Please try again later");
	                     request.getRequestDispatcher("/register.jsp").forward(request, response);
	                    return;
	                }
	        	} catch(Exception e) {
	        		 request.setAttribute("hasError", true);
	        		 request.setAttribute("errorMessage", e);
	                 request.getRequestDispatcher("/register.jsp").forward(request, response);
	                 e.printStackTrace();
	                return;
	        	} finally {
	                try {
	                    if (conn != null) {
	                        conn.close();
	                    }
	                } catch (SQLException e) {
	                	 request.setAttribute("hasError", true);
	                	 request.setAttribute("errorMessage", e);
	                     request.getRequestDispatcher("/register.jsp").forward(request, response);
	                     e.printStackTrace();
	                    return;
	                }
	            }
	        }
						
		} else {
			 request.setAttribute("hasError", true);
			 request.setAttribute("errorMessage", "Please enter the required details");
	         request.getRequestDispatcher("/register.jsp").forward(request, response);
			return;
		}
	}

}
