package modele.servlets.Forum;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.db.Database;
import modele.services.ForumS.Utilisateur;
import modele.tools.ForumTools;

@WebServlet("/deleteApi")
public class ApiCategory extends HttpServlet {
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			
			if(request.getParameter(("idSujet")) == null) {
				try {
					int idTheme = Integer.valueOf((String) request.getParameter(("idTheme"))) ;

					Connection maco = Database.getConnection() ;
					ForumTools.deleteTheme(idTheme, maco);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 

				this.getServletContext().getRequestDispatcher("/WEB-INF/Forum/api.jsp").forward(request, response);
				
			}else {
				int idTheme = Integer.valueOf((String) request.getParameter(("idTheme"))) ;
				int idSujet = Integer.valueOf((String) request.getParameter(("idSujet"))) ;
				if(request.getParameter(("ids")) == null) {
					Connection maco;
					try {
						maco = Database.getConnection();
						ForumTools.deleteSujet(idSujet, idTheme, maco);

					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				}else {
					Connection maco;
					try {
						maco = Database.getConnection();
						ForumTools.setBanner(idSujet, idTheme, maco);

					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				}
				
				

				
				
			}
	
			

			

			

			
			
		
		}

}
