package modele.servlets.activite;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.services.activite.Activite;
@WebServlet("/ListActivite")
public class ListActivite extends HttpServlet {
	

	  
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		public ListActivite() {
	        super();
	        // TODO Auto-generated constructor stub
	    }

		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			try {
				
				
				
				if(request.getParameter("delete") != null 
						&& Integer.parseInt(request.getParameter("delete")) ==  1) {
					Activite.deleteActivite(request, response);
				}
				
				Activite.listActivite(request,response);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.getServletContext().getRequestDispatcher("/WEB-INF/Activite/ListActivite.jsp" ).forward( request, response );
		}

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doGet(request, response);
		}

}
