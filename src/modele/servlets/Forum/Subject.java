package modele.servlets.Forum;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.services.ForumS.ForumS;
import modele.services.ForumS.Sujet;

@WebServlet("/sujet")

public class Subject extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ForumS.getDiscussion(request, response,Integer.valueOf(request.getParameter("id")));
			ForumS.getCategoryUser(request, response);
			ArrayList<Sujet> mesSujet = (ArrayList<Sujet>) request.getAttribute("sujet") ; 
			if(mesSujet.isEmpty()) {
				this.getServletContext().getRequestDispatcher("/WEB-INF/Forum/sensive/AucunSujet.jsp").forward(request, response);

				
			}else {
				this.getServletContext().getRequestDispatcher("/WEB-INF/Forum/sensive/sujet.jsp").forward(request, response);

				
			}


		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		doGet(request, response);
	}
		
	

}
