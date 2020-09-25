package modele.servlets.document;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.services.DocumentS;

/**
 * Servlet implementation class ListArchive
 */
@WebServlet("/ListArchive")
public class ListArchive extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListArchive() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("delete") != null
				&& Integer.parseInt(request.getParameter("delete")) == 1) {
			DocumentS.deleteArchive(request,response);
		}
		
		if(request.getParameter("restore") != null
				&& Integer.parseInt(request.getParameter("restore")) == 1) {
			DocumentS.restoreArchive(request,response);
			DocumentS.deleteArchive(request, response);
		}
		
		DocumentS.listArchive(request,response);
		this.getServletContext().getRequestDispatcher("/WEB-INF/Document/ListArchive.jsp" ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
