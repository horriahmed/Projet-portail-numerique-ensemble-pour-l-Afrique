package modele.servlets.user;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modele.db.Database;
import modele.services.UserS;
import modele.services.ForumS.Utilisateur;
import modele.services.activite.Activite;
import modele.tools.UserTools;


public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Login() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			UserS.login(request,response);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if((Integer)request.getAttribute("state") != null) {
			if((Integer)request.getAttribute("state") == 1) {
				/*Integer userRole;
				
				try {
					
					userRole = UserS.getUserRole(request, response);
					System.out.println(userRole);
					if(userRole != null) {
						switch(userRole) {
							case 0:
								this.getServletContext().getRequestDispatcher("/WEB-INF/EnvAdherant.jsp" ).forward( request, response );
								break;
							case 1:
								this.getServletContext().getRequestDispatcher("/WEB-INF/EnvMembre.jsp" ).forward( request, response );
								break;
							case 5:
								this.getServletContext().getRequestDispatcher("/WEB-INF/EnvPresidant.jsp" ).forward( request, response );
								break;
							case 7:
								this.getServletContext().getRequestDispatcher("/WEB-INF/EnvSecretaire.jsp" ).forward( request, response );
								break;
						}
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				*/
				HttpSession session = request.getSession() ; 
				String id =  (String)request.getAttribute("id") ;
				Utilisateur user = new Utilisateur(request.getParameter("email"),Integer.valueOf(id));
				try {
					List<Integer> userRole = UserS.getUserRole(request, response);
					String nom = UserS.getNom(request, response);
					String prenom = UserS.getPrenom(request, response);
					Connection connection = null;
					connection = Database.getConnection() ; 
					String avatar = UserTools.getAvatar(request.getParameter("email"), connection);
					user.setAvatar(avatar);
					for(Integer role : userRole) {
						user.addRole(role);
					}
					if(nom != null)
						user.setNom(nom);
					
					if(prenom != null)
						user.setPrneom(prenom);
					
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				session.setAttribute("user", user);
				
				try {
					Activite.listActivite(request, response);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.getServletContext().getRequestDispatcher("/WEB-INF/HomePage.jsp" ).forward( request, response );
				
			} else if((Integer)request.getAttribute("state") == -1 
					|| (Integer)request.getAttribute("state") == -2) {
				this.getServletContext().getRequestDispatcher("/WEB-INF/Login.jsp" ).forward( request, response );
			}
			
		} else {
			this.getServletContext().getRequestDispatcher("/WEB-INF/Login.jsp" ).forward( request, response );
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
