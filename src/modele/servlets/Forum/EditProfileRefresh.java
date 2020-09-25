package modele.servlets.Forum;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import modele.db.Database;
import modele.services.ForumS.ForumS;
import modele.services.ForumS.Utilisateur;
import modele.tools.ForumTools;

@WebServlet("/updateProfile")

@MultipartConfig

public class EditProfileRefresh extends HttpServlet {
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
			Part e = request.getPart("img") ; 
			String fileName = getFileName(e);
			if (! (fileName.equals("Default.file") || fileName.equals(""))) {
				System.out.println(fileName);

				String pattern = Pattern.quote(System.getProperty("file.separator"));
				String nomFichier = fileName.split(pattern)[fileName.split(pattern).length - 1].split("\\.")[0];
				String fullPath = uploadPath + File.separator + Integer.toString(nomFichier.hashCode()) + "."+ fileName.split(pattern)[fileName.split(pattern).length - 1].split("\\.")[1];
				e.write(fullPath);
				HttpSession a = request.getSession();
				Utilisateur mail = (Utilisateur) a.getAttribute("user");
				Connection maco;
				try {
					maco = Database.getConnection();
					ForumTools.updateForumProfile(maco, Integer.toString(nomFichier.hashCode()) + "."+ fileName.split(pattern)[fileName.split(pattern).length - 1].split("\\.")[1], request.getParameter("name"), mail.getId());
					mail.setAvatar(Integer.toString(nomFichier.hashCode()) + "."+ fileName.split(pattern)[fileName.split(pattern).length - 1].split("\\.")[1]);

				} catch (ClassNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} 

			
				
			}else {
				HttpSession a = request.getSession();
				Utilisateur mail = (Utilisateur) a.getAttribute("user");
				Connection maco;
				try {
					maco = Database.getConnection();
					ForumTools.updateForumProfile(maco, null, request.getParameter("name"), mail.getId());

				} catch (ClassNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} 

				
			}
			
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/Forum/sensive/editProfile.jsp").forward(request, response);

		

		
		

		

	}

	public static String getFileName(Part part) {
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename"))
				return content.substring(content.indexOf("=") + 2, content.length() - 1);
		}
		return "Default.file";
	}

}
