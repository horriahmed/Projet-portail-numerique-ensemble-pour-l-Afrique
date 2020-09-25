package modele.servlets.activite;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.services.activite.Activite;
@WebServlet("/formulaire")
public class FormulaireAssociatif extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	public FormulaireAssociatif() {
		super();
	}
		
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/Activite/ListFormulaire.jsp" ).forward( request, response );	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request , response) ; 	
	}
	
}