package modele.servlets.activite;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.services.activite.Activite;
@WebServlet("/listLettreInformation")
public class ListLettreInformation extends HttpServlet {

	private static final long serialVersionUID = 1L;

		public ListLettreInformation() {
	        super();
	        // TODO Auto-generated constructor stub
	    }

		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
			Activite.listLettreInformation(request,response);
			this.getServletContext().getRequestDispatcher("/WEB-INF/Activite/ListLettreInformation.jsp" ).forward( request, response );
		}

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doGet(request, response);
		}

}
