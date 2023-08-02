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
import java.util.ArrayList;
import java.util.List;

import org.aryan.JavaOnlinePoll.service.ConnectDB;

/**
 * Servlet implementation class ViewPolls
 */
public class ViewPolls extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        String userId = request.getParameter("id");
        Integer id = Integer.parseInt(userId);
        
        ConnectDB createConn = new ConnectDB();
        Connection conn = createConn.createConnection();
        
        try {
        	if(conn != null) {
            	String selectPollQuery = "SELECT * FROM polls WHERE poll_id = ?";
                PreparedStatement selectPollStmt = conn.prepareStatement(selectPollQuery);
                selectPollStmt.setInt(1, id);
                
                ResultSet resultSet = selectPollStmt.executeQuery();
                
                if (resultSet.next()) {
                    int retrievedPollId = resultSet.getInt("poll_id");
                    String question = resultSet.getString("question");
                   
                    request.setAttribute("poll_id", retrievedPollId);
                    request.setAttribute("question", question);
//                    request.getRequestDispatcher("poll.jsp").forward(request, response);
                } else {
                	request.setAttribute("hasError", true);
	        		request.setAttribute("errorMessage", "There was some error on the server side");
	        		request.getRequestDispatcher("/error.jsp").forward(request, response);
                }
                
                String selectOptionsQuery = "SELECT option_text FROM options WHERE poll_id = ?";
                PreparedStatement selectOptionsStmt = conn.prepareStatement(selectOptionsQuery);
                selectOptionsStmt.setInt(1, id);

                
                ResultSet optionsResultSet = selectOptionsStmt.executeQuery();

                
                List<String> optionsList = new ArrayList<>();

                while (optionsResultSet.next()) {
                    String optionText = optionsResultSet.getString("option_text");
                   
                    optionsList.add(optionText);
                }
                request.setAttribute("optionsList", optionsList);
                request.getRequestDispatcher("/polls.jsp").forward(request, response);
            } else {
            	request.setAttribute("hasError", true);
        		request.setAttribute("errorMessage", "There was some error on the server side");
        		request.getRequestDispatcher("/error.jsp").forward(request, response);
            }
        } catch(Exception e) {
        	request.setAttribute("hasError", true);
    		request.setAttribute("errorMessage", e);
    		request.getRequestDispatcher("/error.jsp").forward(request, response);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
            	 request.setAttribute("hasError", true);
            	 request.setAttribute("errorMessage", e);
                 request.getRequestDispatcher("/Login.jsp").forward(request, response);
                e.printStackTrace();
                return;
            }
        }
        
		
	}

}
