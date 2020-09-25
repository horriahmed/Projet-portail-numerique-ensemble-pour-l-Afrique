package modele.services;

import java.sql.Connection;
import java.util.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import modele.db.Database;
import modele.tools.ReunionTools;

public class ReunionS {
	public static void createReunion(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException {
		String sujet = request.getParameter("sujet");
		String titre = request.getParameter("titre");
		String description = request.getParameter("description");
		String type = request.getParameter("type");
		String lieu = request.getParameter("lieu");
		
		// Heure et Date Début
		String date_debut_req = request.getParameter("date_debut");
		String heure_debut_req = request.getParameter("heure_debut");
		
		
		// Heure et Date Fin
		String date_fin_req= request.getParameter("date_fin");
		String heure_fin_req = request.getParameter("heure_fin");
		
		System.out.println( "Date début : "+date_debut_req + " Date fin : "+ date_fin_req + " Heure Début "+ heure_debut_req + " Heure Fin "+ heure_fin_req);
		System.out.println(sujet +" " + titre +" " + description +" " + type+" " +lieu+" " );
		
		if( sujet == null || description == null
				|| type == null || titre == null || lieu == null
				|| date_debut_req == null || heure_debut_req == null
				|| date_fin_req == null || heure_fin_req == null) {
			System.out.println("missing parameter");
			return ;
		}
		
		LocalDate date_start = LocalDate.parse(date_debut_req);
		LocalTime time_start = LocalTime.parse(heure_debut_req);
		
		LocalDate date_end = LocalDate.parse(date_fin_req);
		LocalTime time_end = LocalTime.parse(heure_fin_req);
		
		// Vars
		Integer type_reunion = Integer.parseInt(type);
       
        DateTime date_Debut_Metier = new DateTime(date_start.getYear(),date_start.getMonthOfYear(),date_start.getDayOfMonth(),
        		time_start.getHourOfDay(), time_start.getMinuteOfHour(),0,0);
		
		DateTime date_Fin_Metier = new DateTime(date_end.getYear(),date_end.getMonthOfYear(),date_end.getDayOfMonth(),
        		time_end.getHourOfDay(), time_end.getMinuteOfHour(),0,0);
		
		
		Timestamp date_debut= new Timestamp(date_Debut_Metier.getMillis()); 
		Timestamp date_fin = new Timestamp(date_Fin_Metier.getMillis()); 
		
		System.out.println(date_debut.toString());
		System.out.println(date_fin.toString());
		
		if(date_debut.after(date_fin)) {
			System.out.println("Date début > Date fin");
			request.setAttribute( "state", -1); // Erreur lors de la saisi
			return ;
		}
		if(type_reunion != 1 
				&& type_reunion != 2
				&& type_reunion != 3) {
			System.out.println("Mauvais type");
			request.setAttribute( "state", -3); // Erreur lors de la saisi
			return ;
		}
		
		Connection connection = null;
		try {
			connection = Database.getConnection();
			
			if(ReunionTools.checkTimesStatut(date_debut, date_fin, connection)) {
				ReunionTools.insertReunion(titre,lieu,sujet,description,type_reunion,date_debut,date_fin, connection);
				Integer id_reunion = ReunionTools.getIdReunion(titre, sujet, type_reunion, date_debut, date_fin, connection);
				System.out.println("je suis passé ");
				ReunionTools.insertParticipants(id_reunion,connection);
				
				if(type_reunion == 3) {
					ReunionTools.insertVotants(id_reunion, connection);
				}
			}else {
				System.out.println("Date non valide");
				request.setAttribute( "state", -2);
				return;
			}
		
		} catch (SQLException e) {
			System.out.println("Date non valide Exception SQL duplicate Date");
			request.setAttribute( "state", -2);
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

		request.setAttribute( "state", 1 );
		
	}


	public static void validateReunion(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException {
		String idReu = request.getParameter("idReunion");
		if(idReu.isEmpty()) {
			System.out.println("missing parameter");
			return ;
		}
		
		// Vars
		System.out.println("Id Renuion = "+idReu);
		System.out.println(idReu.isEmpty());
		Integer idReunion = Integer.parseInt(idReu);
		
		Connection connection = null;
		try {
			connection = Database.getConnection();
			
			if(ReunionTools.checkValidation(idReunion, connection)) {
				System.out.println("Réunion validé");
				request.setAttribute( "state", -2);
				return;
			}else {
				ReunionTools.updateReunion(idReunion,true,connection);
				ReunionTools.sendMailToParticipants(idReunion, connection);
				
				if(ReunionTools.getType(idReunion, connection) == 3) {
					ReunionTools.sendMailToVotants(idReunion, connection);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		}catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if(connection!=null)
				try {
					connection.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		request.setAttribute( "validate", 1 );
		
	}
	
	public static void deleteReunion(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException {
		String idReu = request.getParameter("idReunion");
		if( idReu.isEmpty()) {
			System.out.println("missing parameter");
			return ;
		}
		
		// Vars
		Integer idReunion = Integer.parseInt(idReu);
		Connection connection = null;
		try {
			connection = Database.getConnection();
			ReunionTools.sendMailCanceling(idReunion,connection);
			ReunionTools.deleteReunion(idReunion, connection);
		
		}  catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
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

		request.setAttribute( "delete", 1 );
		
	}

	public static void listReunion(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException {
		String type_validation = request.getParameter("type_validation");
		System.out.println(type_validation);
		if( type_validation == null ) {
			System.out.println("missing parameter");
			return ;
		}
		
		// Vars
		Integer validation = Integer.parseInt(type_validation);
		
		boolean type;
		if(validation == 1) {
			type = true;
			request.setAttribute("validation", true);
		}else {
			type = false;
			request.setAttribute("validation", false);
		}
		Connection connection = null;
		try {
			connection = Database.getConnection();
			
			List<HashMap<Object,Object>> list = ReunionTools.getReunions(type, connection);
		
			if(!list.isEmpty()) {
				request.setAttribute("list", list);
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

		request.setAttribute( "state", 1 );
		
	}
	
	public static void listReunionUtilisateur(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException {
		Connection connection = null;
		try {
			connection = Database.getConnection();
			
			List<HashMap<Object,Object>> list = ReunionTools.getReunions(true, connection);
		
			if(!list.isEmpty()) {
				request.setAttribute("list", list);
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

		request.setAttribute( "state", 1 );
		
	}
	public static void listPersonnes(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException {
		String type_personne = request.getParameter("personne");
		String idReu = request.getParameter("idReunion");
		if( idReu.isEmpty()    || type_personne.isEmpty()) {
			System.out.println("missing parameter");
			return ;
		}
		
		// Vars
		Integer idReunion = Integer.parseInt(idReu);
		Integer personne = Integer.parseInt(type_personne);

		Connection connection = null;
		try {
			connection = Database.getConnection();
			List<HashMap<Object,Object>> listPersonne = null;
			if(personne == 1) {
				listPersonne = ReunionTools.getParticipant(idReunion,connection);
			}
			
			if(personne == 2){
				listPersonne = ReunionTools.getVotant(idReunion,connection);
			}
		
			request.setAttribute("listPersonne", listPersonne);
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

		request.setAttribute( "stateListing", 1 );
		
	}


	public static void deleteVotant(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException {
		String idReu = request.getParameter("idReunion");
		String idutili = request.getParameter("idutilisateur");
		if( idReu.isEmpty()  || idutili.isEmpty()) {
			System.out.println("missing parameter");
			return ;
		}
		
		// Vars
		Integer idReunion = Integer.parseInt(idReu);
		Integer idutilisateur = Integer.parseInt(idutili);
		
		Connection connection = null;
		try {
			connection = Database.getConnection();
			ReunionTools.deleteVotant(idutilisateur,idReunion, connection);
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {

			if(connection!=null)
				try {
					connection.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		request.setAttribute( "deleteVotant", 1 );
		
	}
	
	public static void main(String []args) {
		

		
	}
}
