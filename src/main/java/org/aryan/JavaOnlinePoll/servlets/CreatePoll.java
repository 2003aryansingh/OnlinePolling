package org.aryan.JavaOnlinePoll.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.aryan.JavaOnlinePoll.service.ConnectDB;

/**
 * Servlet implementation class CreatePoll
 */
public class CreatePoll extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String question = request.getParameter("question");
        String option1 = request.getParameter("option1");
        String option2 = request.getParameter("option2");
        String option3 = request.getParameter("option3");
        String option4 = request.getParameter("option4");
        
        ConnectDB createConn = new ConnectDB();
        Connection conn = createConn.createConnection();
        
        HttpSession session = request.getSession();
		
        Integer user_id = (Integer) session.getAttribute("userId");
        
        if(conn != null) {
        	try {
        		String insertPollQuery = "INSERT INTO polls (question, user_id) VALUES (?, ?) RETURNING poll_id";
                PreparedStatement insertPollStmt = conn.prepareStatement(insertPollQuery);
                insertPollStmt.setString(1, question);
                insertPollStmt.setInt(2, user_id);
                int poll_id = -1;
                if (insertPollStmt.execute()) {
                    ResultSet resultSet = insertPollStmt.getResultSet();
                    if (resultSet.next()) {
                        poll_id = resultSet.getInt("poll_id");
                    }
                    resultSet.close();
                }
                insertPollStmt.close();
                
                if (poll_id != -1) {
                	
                    String insertOptionsQuery = "INSERT INTO options (poll_id, option_text) VALUES (?, ?)";
                    PreparedStatement insertOptionsStmt = conn.prepareStatement(insertOptionsQuery);
                    insertOptionsStmt.setInt(1, poll_id);
                    insertOptionsStmt.setString(2, option1);
                    insertOptionsStmt.addBatch();

                    insertOptionsStmt.setInt(1, poll_id);
                    insertOptionsStmt.setString(2, option2);
                    insertOptionsStmt.addBatch();

                    insertOptionsStmt.setInt(1, poll_id);
                    insertOptionsStmt.setString(2, option3);
                    insertOptionsStmt.addBatch();

                    insertOptionsStmt.setInt(1, poll_id);
                    insertOptionsStmt.setString(2, option4);
                    insertOptionsStmt.addBatch();

                    
                    insertOptionsStmt.executeBatch();
                    insertOptionsStmt.close();
                    
                    request.setAttribute("success", true);
           		    request.setAttribute("poll_id", poll_id);
                    request.getRequestDispatcher("/createPoll.jsp").forward(request, response);          
                } else {
                	request.setAttribute("hasError", true);
           		    request.setAttribute("errorMessage", "Some error occurred :(");
                    request.getRequestDispatcher("/createPoll.jsp").forward(request, response);
                }
        	} catch(Exception e) {
        		request.setAttribute("hasError", true);
       		    request.setAttribute("errorMessage", "Some error occurred :(");
                request.getRequestDispatcher("/createPoll.jsp").forward(request, response);
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
                     request.getRequestDispatcher("/createPoll.jsp").forward(request, response);
                     e.printStackTrace();
                     return;
                }
        	}
        } else {
        	
        }
	}

}
