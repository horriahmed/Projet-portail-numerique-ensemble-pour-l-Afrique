package modele.servlets.Forum;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modele.services.ForumS.ForumS;
import modele.services.ForumS.Utilisateur;

/**
 * Servlet implementation class HomeForum
 */
@WebServlet("/HomeForum")
public class HomeForum extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeForum() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try {
			HttpSession a = request.getSession();
			Utilisateur mail = (Utilisateur) a.getAttribute("user");
			if(mail == null) {
				this.getServletContext().getRequestDispatcher("/WEB-INF/Forum/sensive/log.jsp").forward(request, response);

				
			}
				ForumS.getBanner(request, response);
				ForumS.getSujetById(request, response,mail.getId());
				ForumS.getCategory(request, response);
				ForumS.getCategoryUser(request, response);
				this.getServletContext().getRequestDispatcher("/WEB-INF/Forum/sensive/home.jsp").forward(request, response);
				
			
			

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
