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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aryan.JavaOnlinePoll.service.ConnectDB;

/**
 * Servlet implementation class RenderPolls
 */
public class RenderPolls extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("userId");
		int user_id = Integer.parseInt(id);
		
		System.out.println(user_id);
		
		ConnectDB createConn = new ConnectDB();
        Connection conn = createConn.createConnection();
        
        try {
        	if(conn != null) {
        		ArrayList<Integer> pollIds = getUserPollIds(user_id, conn, request);
        		
        		HashMap<Integer, HashMap<String, Double>> pollResults = new HashMap<>();
        		for (Integer pollId : pollIds) {
                    HashMap<String, Double> optionPercentages = calculatePollVotePercentages(pollId, conn);
                    pollResults.put(pollId, optionPercentages);
                    
                }
        		
        		 System.out.println(pollResults);
        		
        		 request.setAttribute("pollResults", pollResults);
                 request.getRequestDispatcher("renderpolls.jsp").forward(request, response);
        		
        	} else {
        		request.setAttribute("hasError", true);
       		 	request.setAttribute("errorMessage", "Connection Problem");
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
            	 request.setAttribute("errorMessage", "Some Error occurred !");
                 request.getRequestDispatcher("/error.jsp").forward(request, response);
                e.printStackTrace();
                return;
            }
        }
	}
	
	private ArrayList<Integer> getUserPollIds(int userId, Connection connection, HttpServletRequest request) {
        ArrayList<Integer> pollIds = new ArrayList<>();
        ArrayList<String> questions = new ArrayList<>();
        String sql = "SELECT DISTINCT poll_id, question FROM polls WHERE user_id = ?";
        try {
        	PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, userId);
            try  {
            	ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                	System.out.println(rs.getInt("poll_id"));
                    pollIds.add(rs.getInt("poll_id"));
                    questions.add(rs.getString("question"));
                }
                
                request.setAttribute("questions", questions);
                
            } catch(Exception e) {
            	System.out.println(e);
            }
        } catch(Exception e) {
        	System.out.println(e);
        }
        
        return pollIds;
    }
	
	private HashMap<String, Double> calculatePollVotePercentages(int pollId, Connection connection) {
        HashMap<String, Double> optionPercentages = new HashMap<>();
        String sql = "SELECT option_text, vote_count FROM options WHERE poll_id = ?";
        try  {
        	PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, pollId);
            try  {
            	ResultSet rs = stmt.executeQuery();
                int totalVotes = 0;
                while (rs.next()) {
                    totalVotes += rs.getInt("vote_count");
                    optionPercentages.put(rs.getString("option_text"), (double) rs.getInt("vote_count"));
                }
                for (Map.Entry<String, Double> entry : optionPercentages.entrySet()) {
                    double percentage = (entry.getValue() / totalVotes) * 100;
                    String formattedNumber = String.format("%.2f", percentage);
                    double result = Double.parseDouble(formattedNumber);
                    entry.setValue(result);
                }
            } catch(Exception e) {
            	System.out.println(e);
            }
        } catch(Exception e) {
        	System.out.println(e);
        }
        System.out.println(optionPercentages);
        return optionPercentages;
    }

}
