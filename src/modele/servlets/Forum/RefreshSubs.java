package modele.servlets.Forum;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.db.Database;
import modele.services.ForumS.Utilisateur;
import modele.tools.ForumTools;


@WebServlet("/refresh")

public class RefreshSubs extends javax.servlet.http.HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	int id = Integer.valueOf(request.getParameter("identifiant")) ; 
		int userId = ((Utilisateur)request.getSession().getAttribute("user")).getId() ;
		try {
			Connection maco = Database.getConnection() ;
			ForumTools.unsubscribeTheme(userId, id, maco);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		System.out.println("Hello" + id) ; 

		this.getServletContext().getRequestDispatcher("/WEB-INF/Forum/refresh.jsp").forward(request, response);

		
		
	
	}
		
	

}