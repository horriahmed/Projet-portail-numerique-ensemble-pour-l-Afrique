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
public class ListUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListUtilisateur() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * Il y'a une seule servlet ListUtilisateur, qui prend un paramètre calledBY, suivant ce paramètre on donne un affchage différent
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			UserS.listUser(request,response);
			this.getServletContext().getRequestDispatcher("/WEB-INF/ListUtilisateur/ListUtilisateur.jsp" ).forward( request, response );		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
