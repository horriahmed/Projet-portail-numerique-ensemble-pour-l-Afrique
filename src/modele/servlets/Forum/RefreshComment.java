package modele.servlets.Forum;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modele.db.Database;
import modele.services.ForumS.ForumS;
import modele.services.ForumS.Utilisateur;
import modele.tools.ForumTools;

@WebServlet("/updateComment")

public class RefreshComment extends javax.servlet.http.HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int idSujet = Integer.valueOf(request.getParameter("idSujet"));
			int idTheme = Integer.valueOf(request.getParameter("idTheme"));
			HttpSession a = request.getSession();
			Utilisateur mail = (Utilisateur) a.getAttribute("user");
			String test = request.getParameter("msg");

			ForumS.insertComments(request, response, idTheme, idSujet, mail.getId(), test , new ArrayList());

			this.getServletContext().getRequestDispatcher("/WEB-INF/Forum/commentaire.jsp").forward(request, response);

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
			try {
				int idSujet = Integer.valueOf(request.getParameter("idSujet"));
				int idTheme = Integer.valueOf(request.getParameter("idTheme"));
				HttpSession a = request.getSession();
				Utilisateur mail = (Utilisateur) a.getAttribute("user");
				String test = request.getParameter("msg") ; 

				ForumS.insertComments(request, response, idTheme, idSujet, mail.getId(), test, new ArrayList());

				this.getServletContext().getRequestDispatcher("/WEB-INF/Forum/commentaire.jsp").forward(request, response);

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
	}}
