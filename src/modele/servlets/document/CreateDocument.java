package modele.servlets.document;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.beans.Fichier;
import modele.db.Database;
import modele.services.UploadForm;
import modele.tools.DocumentTools;

@MultipartConfig
public class CreateDocument extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CreateDocument() {
        super();
        // TODO Auto-generated constructor stub
    }

    public static final String CHEMIN      = "chemin";

    public static final String roadToFile = "C://Users//Bilal//git//Final//ProjetSIA//document//";
    
	public static final String ATT_FICHIER = "fichier";
    public static final String ATT_FORM    = "form";

    public static final String VUE         = "/WEB-INF/Document/CreateDocument.jsp";
    
    public Fichier fichier = new Fichier();

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		        /* Affichage de la page d'upload */
	        String role = request.getParameter("role");
	        System.out.println(role);
	        Integer idrole = null;
	        
	        if(role != null && !role.isEmpty()) {
	        	idrole = Integer.parseInt(role);
	        	request.setAttribute("role",idrole);
	        	if(idrole != 1) {
	        		fichier.setType(Integer.parseInt(role));
	        	}
	        		
	        }

    		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
         * Lecture du paramètre 'chemin' passé à la servlet via la déclaration
         * dans le web.xml
         */
        String chemin = this.getServletConfig().getInitParameter( CHEMIN );


 
        
        /* Préparation de l'objet formulaire */
        UploadForm form = new UploadForm();
        /*

        /* Traitement de la requête et récupération du bean en résultant */
        this.fichier = form.enregistrerFichier(this.fichier, request, chemin );
        
        Connection connection = null;
		try {
			connection = Database.getConnection();
			if(fichier.getNom() != null)
				DocumentTools.insertDocument(fichier, roadToFile,connection);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		}catch (SQLException e) {
			request.setAttribute("SQL", -1);
			e.printStackTrace();			
		} finally {
			if(connection!=null)
				try {
					connection.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
        /* Stockage du formulaire et du bean dans l'objet request */
        request.setAttribute( ATT_FORM, form );
        request.setAttribute( ATT_FICHIER, fichier );

        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}

}
