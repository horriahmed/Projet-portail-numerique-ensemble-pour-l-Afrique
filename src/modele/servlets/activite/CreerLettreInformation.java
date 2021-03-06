package modele.servlets.activite;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.services.activite.Activite;
@WebServlet("/creerLettreInformation")
public class CreerLettreInformation extends HttpServlet {
	

	  
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		public CreerLettreInformation() {
	        super();
	        // TODO Auto-generated constructor stub
	    }

		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			Activite.creerLettreInformation(request,response);
			this.getServletContext().getRequestDispatcher("/WEB-INF/Activite/CreerLettreInformation.jsp" ).forward( request, response );
		}

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doGet(request, response);
		}

}
