package modele.servlets.reunion;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.services.ReunionS;

/**
 * Servlet implementation class ListReunion
 */
@WebServlet("/ListReunion")
public class ListReunion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListReunion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if(request.getParameter("validate") != null 
					&& Integer.parseInt(request.getParameter("validate")) ==  1) {
				ReunionS.validateReunion(request, response);
			}
			if(request.getParameter("delete") != null 
					&& Integer.parseInt(request.getParameter("delete")) ==  1) {
				ReunionS.deleteReunion(request, response);
			}
			if(request.getParameter("deletePersonne") != null) {
				ReunionS.deleteVotant(request, response);
			}
			if(request.getParameter("personne") != null) {
				ReunionS.listPersonnes(request, response);
			}
			ReunionS.listReunion(request,response);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/Reunion/ListReunion.jsp" ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
