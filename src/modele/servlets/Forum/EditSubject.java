package modele.servlets.Forum;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import modele.services.ForumS.ForumS;


@WebServlet("/editSubject")
public class EditSubject extends HttpServlet {
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ForumS.getDiscussion(request, response,Integer.valueOf(request.getParameter("id")));
			this.getServletContext().getRequestDispatcher("/WEB-INF/Forum/editSujet.jsp" ).forward( request, response );
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
        }
		
	
	
	
	

	public static void main(String[] args) {
		

	}

}
