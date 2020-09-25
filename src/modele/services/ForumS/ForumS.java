package modele.services.ForumS;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modele.db.Database;
import modele.tools.ForumTools;

public class ForumS {
	public static void getCategory(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException {
		Connection maco = Database.getConnection() ; 
		HttpSession a = request.getSession();
		Utilisateur mail = (Utilisateur) a.getAttribute("user");
		if(mail != null) {
			int id = mail.getId() ; 
			ArrayList<Theme> mesThemes = ForumTools.listTheme(maco , mail.getId()) ;
			request.setAttribute("theme", mesThemes);
		}
		
		
	}
	
	public static void getDiscussion(HttpServletRequest request, HttpServletResponse response,int idtheme) throws ClassNotFoundException, SQLException {
		Connection maco = Database.getConnection() ; 
		ArrayList<Sujet> mesSujet = ForumTools.listSujet(idtheme, maco);
		request.setAttribute("sujet", mesSujet);


		//ArrayList<Theme> mesThemes = ForumTools.listTheme(maco) ;
		//request.setAttribute("theme", mesThemes);
		
	}
	
	public static void getComments(HttpServletRequest request, HttpServletResponse response,int idtheme,int idSujet) throws ClassNotFoundException, SQLException {
		Connection maco = Database.getConnection() ; 
		ArrayList<Commentaire> mesCommentaire = ForumTools.listCommentaire(idtheme, idSujet, maco) ; 
		request.setAttribute("commentaire", mesCommentaire);

	}
	public static void insertComments(HttpServletRequest request, HttpServletResponse response,int idtheme,int idSujet,int auteur,String commentaire , ArrayList<String> pieces) throws ClassNotFoundException, SQLException {
		Connection maco = Database.getConnection() ; 
		ForumTools.insertNewComments(auteur, idtheme,commentaire,idSujet,maco, pieces) ; 

	}
	
	public static void insertTheme(HttpServletRequest request, HttpServletResponse response,String nom,String link ) throws ClassNotFoundException, SQLException {
		Connection maco = Database.getConnection() ; 
		ForumTools.insertTheme(link, nom, maco); 

	}
	public static void insertSujet(HttpServletRequest request, HttpServletResponse response,String nom,String link ,String contenu , int id) throws ClassNotFoundException, SQLException {
		Connection maco = Database.getConnection() ; 
		ForumTools.insertSujet(link, nom, contenu, id, maco);
	}
	

	public static void getBanner(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException {
		Connection maco = Database.getConnection() ; 
		Sujet banner = ForumTools.getBanner(maco) ; 
		request.setAttribute("banner", banner);
		
	}
	
	
	public static void getSujetById(HttpServletRequest request, HttpServletResponse response,int id) throws ClassNotFoundException, SQLException {
		Connection maco = Database.getConnection() ; 
		ArrayList<Sujet> mesSujet = ForumTools.listSujetById(maco, id);
		request.setAttribute("sujetUtilisateur", mesSujet);


		//ArrayList<Theme> mesThemes = ForumTools.listTheme(maco) ;
		//request.setAttribute("theme", mesThemes);
		
	}

	public static void getCategoryUser(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException {
		Connection maco = Database.getConnection() ; 
		HttpSession a = request.getSession();
		Utilisateur mail = (Utilisateur) a.getAttribute("user");
		if(mail != null) {
			int id = mail.getId() ; 
			ArrayList<Theme> mesThemes = ForumTools.listThemeOnlyUser(maco , mail.getId()) ;
			request.setAttribute("themeUser", mesThemes);
		}
		
		
	}
	
	public static void getSujet(HttpServletRequest request, HttpServletResponse response , int idSujet , int idTheme) throws ClassNotFoundException, SQLException {
		Connection maco = Database.getConnection() ; 
		Sujet banner = ForumTools.getSujet(maco, idSujet, idTheme) ; 
		request.setAttribute("sjt", banner);
		System.out.println(banner);
		
	}
	
	public static void main(String[] arg) throws ClassNotFoundException, SQLException {
		Connection maco = Database.getConnection();
		
		

	}

}
