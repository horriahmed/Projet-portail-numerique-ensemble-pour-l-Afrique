package modele.servlets.activite;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import modele.services.activite.Activite;

/**
 * Servlet implementation class RefreshActivite
 */
@WebServlet("/RefreshActivite")
@MultipartConfig
public class RefreshActivite extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String IMAGES_FOLDER = "/Images";

	public String uploadPath;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RefreshActivite() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void init() throws ServletException {
		uploadPath = getServletContext().getRealPath(IMAGES_FOLDER);
		System.out.println(uploadPath);
		
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists())
			uploadDir.mkdir();
	}

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		init();
		Part part = request.getPart("image");
		String fileName = getFileName(part);
		System.out.println("fileName : " + fileName);
		
		if(fileName == null || (fileName != null && fileName.isEmpty())) {
			try {
				Activite.createActivite(request, response, null);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
		}else {
			String pattern = Pattern.quote(System.getProperty("file.separator"));
			System.out.println("pattern : "+pattern);
			String fullPath = uploadPath + File.separator + fileName.split(pattern)[fileName.split(pattern).length - 1 ];
			part.write(fullPath);
			System.out.println(fullPath) ; 
			
			try {
				Activite.createActivite(request, response ,fullPath.split(pattern)[fullPath.split(pattern).length - 1]);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/Activite/CreerActivite.jsp" ).forward( request, response );

	}
	
	private String getFileName(Part part) {
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename"))
				return content.substring(content.indexOf("=") + 2, content.length() - 1);
		}
		return "Default.file";
	}
	

}
