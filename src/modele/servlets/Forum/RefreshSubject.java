package modele.servlets.Forum;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import modele.services.ForumS.ForumS;

@WebServlet("/RefreshSubject")

@MultipartConfig

public class RefreshSubject extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String IMAGES_FOLDER = "/Images";

	public String uploadPath;

	public void init() throws ServletException {
		uploadPath = getServletContext().getRealPath(IMAGES_FOLDER);
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists())
			uploadDir.mkdir();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Part part = request.getPart("img");
		String fileName = getFileName(part);
		String pattern = Pattern.quote(System.getProperty("file.separator"));
		String nomFichier = fileName.split(pattern)[fileName.split(pattern).length - 1 ].split("\\.")[0]; 
		String fullPath = uploadPath + File.separator + Integer.toString(nomFichier.hashCode()) + "." +fileName.split(pattern)[fileName.split(pattern).length - 1 ].split("\\.")[1] ; 
		part.write(fullPath);
		System.out.println("TRRRRRRRRRRRRRRRRRRRRRRRRRRRRR") ; 

		try {
			ForumS.insertSujet(request, response, request.getParameter("sjt"), Integer.toString(nomFichier.hashCode()) + "." +fileName.split(pattern)[fileName.split(pattern).length - 1 ].split("\\.")[1], request.getParameter("contenu"), Integer.valueOf(request.getParameter("idTheme")));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String getFileName(Part part) {
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename"))
				return content.substring(content.indexOf("=") + 2, content.length() - 1);
		}
		return "Default.file";
	}

}
