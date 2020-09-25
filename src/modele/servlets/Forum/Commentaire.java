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
import modele.services.ForumS.Utilisateur;

@WebServlet("/discussion")

public class Commentaire extends HttpServlet {
	/**
	 */
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int idSujet = Integer.valueOf(request.getParameter("idSujet")) ; 
		int idTheme = Integer.valueOf(request.getParameter("idTheme"));
		try {
			ForumS.getComments(request, response, idTheme, idSujet);
			ForumS.getSujet(request, response, idSujet, idTheme);
			ForumS.getCategoryUser(request,response);
			this.getServletContext().getRequestDispatcher("/WEB-INF/Forum/sensive/commentaire.jsp").forward(request, response);

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
