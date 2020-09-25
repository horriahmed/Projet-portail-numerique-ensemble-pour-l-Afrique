package modele.services.activite;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import modele.classe.activite.Lien;
import modele.db.Database;
import modele.tools.ForumTools;
import modele.tools.ReunionTools;
import modele.tools.activite.ActiviteTools;
import modele.tools.activite.TypeTools;
import modele.tools.activite.lienTools;

public class Activite {
	
	public static void createActivite(HttpServletRequest request, HttpServletResponse response, String image_link) throws ClassNotFoundException {
		String sujet = request.getParameter("sujet");
		String type = request.getParameter("type");
		String lieu = request.getParameter("lieu");
		String titre = request.getParameter("titre");
		String Stitre1 = request.getParameter("sous-titre1");
		String description1 = request.getParameter("description-1");
		String Stitre2 = request.getParameter("sous-titre2");
		String description2 = request.getParameter("description-2");
		String Stitre3 = request.getParameter("sous-titre3");
		String description3 = request.getParameter("description-3");
		String titreLien = request.getParameter("titre_lien");
		String url=request.getParameter("url");

		
		// Heure et Date Début
		String date_debut_req = request.getParameter("date_debut");
		String heure_debut_req = request.getParameter("heure_debut");
		
		
		// Heure et Date Fin
		String date_fin_req= request.getParameter("date_fin");
		String heure_fin_req = request.getParameter("heure_fin");
		

		
		
		if( sujet == null || Stitre1 == null || description1==null
				|| type == null || titre == null || lieu == null) {
	
			System.out.println("missing parameter");
			return ;
		}
		
		Timestamp date_debut = null;
		Timestamp date_fin = null;
		if((date_debut_req != null && !date_debut_req.isEmpty()) 
				&& (heure_debut_req != null && !heure_debut_req.isEmpty())
				&& (date_fin_req != null && !date_fin_req.isEmpty())
				&& (heure_fin_req != null && !heure_fin_req.isEmpty())) {
			
			LocalDate date_start = LocalDate.parse(date_debut_req);
			LocalTime time_start = LocalTime.parse(heure_debut_req);
			
			LocalDate date_end = LocalDate.parse(date_fin_req);
			LocalTime time_end = LocalTime.parse(heure_fin_req);
			
			
			
	        DateTime date_Debut_Activite = new DateTime(date_start.getYear(),date_start.getMonthOfYear(),date_start.getDayOfMonth(),
	        		time_start.getHourOfDay(), time_start.getMinuteOfHour(),0,0);
			
			DateTime date_Fin_Activite = new DateTime(date_end.getYear(),date_end.getMonthOfYear(),date_end.getDayOfMonth(),
	        		time_end.getHourOfDay(), time_end.getMinuteOfHour(),0,0);
			
			
			date_debut = new Timestamp(date_Debut_Activite.getMillis()); 
			date_fin = new Timestamp(date_Fin_Activite.getMillis()); 
			
			System.out.println(date_debut.toString());
			System.out.println(date_fin.toString());
			
			if(date_debut.after(date_fin)) {
				System.out.println("Date debut d'activit� est superieur a la  Date de sa fin");
				request.setAttribute( "state", -1); // Erreur lors de la saisi
				return ;
			}
		}
		
		
		
		Connection connection = null;
		try {
			connection = Database.getConnection();
		
				Lien l1 = null;
				Integer idlien = null;
				if((titreLien != null  && !titreLien.isEmpty())
						&& (url != null && !url.isEmpty())) {
					l1 = new Lien(titreLien,url);
					lienTools.insertLien(l1, connection);
					
				}
				
				if(l1 != null) {
					idlien=lienTools.getIdLien(l1, connection);
				}
				
				TypeTools.insertType(type, connection);
				Integer idtype=TypeTools.getIdType(type, connection);
				
				ActiviteTools.insertActivite(titre, lieu, sujet, idtype, idlien, Stitre1, Stitre2, Stitre3, description1, description2, description3, date_debut, date_fin,image_link, connection);
				
				
	
		
		} catch (SQLException e) {
			System.out.println("Date non valide Exception SQL duplicate Date");
			request.setAttribute( "state", -2);
			e.printStackTrace();			
			return ;
		} finally {
			
			request.setAttribute( "state", 1);
			if(connection!=null)
				try {
					connection.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
	}
	
	
	public static void listActivite(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException {
	
		Connection connection = null;
		try {
			connection = Database.getConnection();
			
			List<HashMap<Object,Object>> list = ActiviteTools.getActivites(connection);
		
			if(!list.isEmpty()) {
				request.setAttribute("list", list);
			}
			else
			{
				// si la liste est vide ( y a aucune activite de pr�vu)
			request.setAttribute( "state", 1 );
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		}finally {

			if(connection!=null)
				try {
					connection.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		
	}
	
	public static void deleteActivite(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException {
		String idac = request.getParameter("idactivite");
		if( idac.isEmpty()) {
			System.out.println("missing parameter");
			return ;
		}
		
		// Vars
		Integer idActivite = Integer.parseInt(idac);
		Connection connection = null;
		try {
			connection = Database.getConnection();
			ActiviteTools.deleteActivite(idActivite, connection);
		
		}catch (SQLException e) {
			e.printStackTrace();
			
		} finally {

			if(connection!=null)
				try {
					connection.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		request.setAttribute( "delete", 1 );
		
	}


	public static void getArticle(HttpServletRequest request, HttpServletResponse response) {
		String idActivite = request.getParameter("idactivite");
		
		if(idActivite == null || (idActivite != null && idActivite.isEmpty())) {
			System.out.println("missing parameter");
			return ;
		}
		Integer idactivite = Integer.parseInt(idActivite);
		Connection connection = null;
		try {
			connection = Database.getConnection();
			
			HashMap<Object,Object> list = ActiviteTools.getActivite(idactivite, connection);
			System.out.println(list);
			if(list != null) {
				request.setAttribute("activite", list);
			}
		}catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
			
		} finally {

			if(connection!=null)
				try {
					connection.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		request.setAttribute( "state", 1 );
		
	}


	public static void getLettre(HttpServletRequest request, HttpServletResponse response) {
		String idActivite = request.getParameter("idactivite");
		
		if(idActivite == null || (idActivite != null && idActivite.isEmpty())) {
			System.out.println("missing parameter");
			return ;
		}
		Integer idactivite = Integer.parseInt(idActivite);
		Connection connection = null;
		try {
			connection = Database.getConnection();
			
			HashMap<Object,Object> list = ActiviteTools.getLettre(idactivite, connection);
			System.out.println(list);
			
			if(list != null) {
				request.setAttribute("activite", list);
			}
		}catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
			
		} finally {

			if(connection!=null)
				try {
					connection.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		request.setAttribute( "state", 1 );
		
	}
	public static void creerLettreInformation(HttpServletRequest request, HttpServletResponse response) {
		String sujet = request.getParameter("sujet");
		String titre = request.getParameter("titre");
		String Stitre1 = request.getParameter("sous-titre1");
		String description1 = request.getParameter("description-1");
		String Stitre2 = request.getParameter("sous-titre2");
		String description2 = request.getParameter("description-2");
		String Stitre3 = request.getParameter("sous-titre3");
		String description3 = request.getParameter("description-3");

		
		if( sujet == null || Stitre1 == null || description1==null
				|| titre == null) {
			System.out.println("missing parameter");
			return ;
		}
		
		Connection connection = null;
		try {
			connection = Database.getConnection();
				
			ActiviteTools.insertLettreInformation(sujet,titre,Stitre1,description1,Stitre2,description2, Stitre3,description3, connection);
			ActiviteTools.sendMailNotification(sujet,titre,java.time.LocalDate.now(),connection);
				
		}catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			return ;
		} finally {
			if(connection!=null)
				try {
					connection.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		request.setAttribute( "state", 1);
	}


	public static void listLettreInformation(HttpServletRequest request, HttpServletResponse response) {

		Connection connection = null;
		try {
			connection = Database.getConnection();
			
			List<HashMap<Object,Object>> list = ActiviteTools.getLettreInformations(connection);
		
			if(!list.isEmpty()) {
				request.setAttribute("list", list);
			}else{
				request.setAttribute( "state", 1 );
			}
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			
		}finally {

			if(connection!=null)
				try {
					connection.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}
		}}}