package org.aryan.JavaOnlinePoll.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.aryan.JavaOnlinePoll.service.ConnectDB;

/**
 * Servlet implementation class SubmitPoll
 */
public class SubmitPoll extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String selectedOption = request.getParameter("selectedOption");
		Integer pollId =  Integer.parseInt(request.getParameter("poll_id"));
		
		
		ConnectDB createConn = new ConnectDB();
        Connection conn = createConn.createConnection();
        
        try {
        	if(conn != null) {
            	String updateVoteQuery = "UPDATE options SET vote_count = vote_count + 1 WHERE poll_id = ? AND option_text = ?";
                PreparedStatement updateVoteStmt = conn.prepareStatement(updateVoteQuery);
                
                updateVoteStmt.setInt(1, pollId);
                updateVoteStmt.setString(2, selectedOption);
                
                updateVoteStmt.executeUpdate();
                
                
                request.setAttribute("success", true);
       		 	request.setAttribute("message", "Your opinion has been recorded.. Thank You !");
       		 	request.getRequestDispatcher("/success.jsp").forward(request, response);
            } else {
            	request.setAttribute("hasError", true);
       		 	request.setAttribute("errorMessage", "Some error occurred :(");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            }
        } catch(Exception e) {
        	request.setAttribute("hasError", true);
   		 	request.setAttribute("errorMessage", "Some error occurred :(");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
            	 request.setAttribute("hasError", true);
            	 request.setAttribute("errorMessage", "Some Error occurred !");
                 request.getRequestDispatcher("/error.jsp").forward(request, response);
                e.printStackTrace();
                return;
            }
        }
	}

}
