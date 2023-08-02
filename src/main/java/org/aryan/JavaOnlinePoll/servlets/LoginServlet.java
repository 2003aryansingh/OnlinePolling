package org.aryan.JavaOnlinePoll.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.aryan.JavaOnlinePoll.service.Auth;
import org.aryan.JavaOnlinePoll.service.ConnectDB;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
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
	        		
	                    String sql = "SELECT user_id, username, password FROM users WHERE username = ? AND password = ?";
	                    PreparedStatement statement = conn.prepareStatement(sql);
	                    statement.setString(1, username);
	                    statement.setString(2, password);

	                    ResultSet resultSet = statement.executeQuery();

	                    if (resultSet.next()) {
	                    	int userId = resultSet.getInt("user_id");
	                    	
	                    	HttpSession session = request.getSession();
	                    	
	                    	session.setAttribute("username", username);
	                    	session.setAttribute("userId", userId);
	                    	
	                    	request.getRequestDispatcher("/votes.jsp").forward(request, response);
	                    } else {
	                    	request.setAttribute("hasError", true);
	   	        		 	request.setAttribute("errorMessage", "Invalid Login Credentials");
	   	        		 	request.getRequestDispatcher("/Login.jsp").forward(request, response);
	                    }
	        	} catch(Exception e) {
	        		 request.setAttribute("hasError", true);
	        		 request.setAttribute("errorMessage", "Some error occurred :(");
	                 request.getRequestDispatcher("/Login.jsp").forward(request, response);
	                e.printStackTrace();
	                return;
	        	} finally {
	                try {
	                    if (conn != null) {
	                        conn.close();
	                    }
	                } catch (SQLException e) {
	                	 request.setAttribute("hasError", true);
	                	 request.setAttribute("errorMessage", "Some Error occurred !");
	                     request.getRequestDispatcher("/Login.jsp").forward(request, response);
	                    e.printStackTrace();
	                    return;
	                }
	            }
	        }
						
		} else {
			 request.setAttribute("hasError", true);
			 request.setAttribute("errorMessage", "Please enter the required details");
	         request.getRequestDispatcher("/Login.jsp").forward(request, response);
			return;
		}
	}
}
