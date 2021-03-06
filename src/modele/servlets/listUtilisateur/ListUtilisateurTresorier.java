package modele.servlets.listUtilisateur;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.services.UserS;

/**
 * Servlet implementation class ListUtilisateur
 */
public class ListUtilisateurTresorier extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListUtilisateurTresorier() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * Il y'a une seule servlet ListUtilisateur, qui prend un paramètre calledBY, suivant ce paramètre on donne un affchage différent
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			if(request.getParameter("delete") != null 
					&& Integer.parseInt(request.getParameter("delete")) ==  1) {
					UserS.radiateUser(request, response);	
			}
			
			if(request.getParameter("validate") != null 
					&& Integer.parseInt(request.getParameter("validate")) ==  1) {
					UserS.putValidity(request, response);
					System.out.println("update");
			}
		
			UserS.listUser(request,response);
			this.getServletContext().getRequestDispatcher("/WEB-INF/ListUtilisateur/ListUtilisateurTresorier.jsp" ).forward( request, response );
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		};
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
