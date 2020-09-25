package modele.servlets.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.services.UserS;

/**
 * Servlet implementation class UpdateUserTresorier
 */
public class UpdateUserAdministrateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateUserAdministrateur() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserS.updateUser(request,response);
		this.getServletContext().getRequestDispatcher("/WEB-INF/UpdateUserAdministrateur.jsp" ).forward( request, response );
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
