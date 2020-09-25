package modele.tools.activite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import COM.rsa.jsafe.ac;
import modele.tools.MailTools;




public class ActiviteTools {

	public static void insertActivite(String titre, String lieu, String sujet,Integer idtype, Integer idlien, String stitre1
			,String stitre2,String stitre3,String description1, String description2, String description3,Timestamp date_debut, Timestamp date_fin,String image_link, Connection connection) throws SQLException {
		
//		// insertion  des liens
//		lienTools.insertLien(lien1, connection);
//		lienTools.insertLien(lien2, connection);
//		lienTools.insertLien(lien3, connection);
//		
//		// insertion du type
//		TypeTools.insertType(type_activite, connection);
		
		
	
		String insert="INSERT INTO public.activite(titre,idtype,idlien,stitre1,stitre2,stitre3,description1,description2,description3,sujet,date_debut,date_fin,lieu,image_link)"
				+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		PreparedStatement preparedStatement = null;
		try{
			preparedStatement=	connection.prepareStatement(insert);

			preparedStatement.setString(1, titre);
			preparedStatement.setLong(2, idtype);
			if(idlien == null) {
				preparedStatement.setObject(3, null);
			}else {
				preparedStatement.setLong(3, idlien);
			}
			
			preparedStatement.setString(4, stitre1);
			preparedStatement.setString(5, stitre2);
			preparedStatement.setString(6, stitre3);
			preparedStatement.setString(7, description1);
			preparedStatement.setString(8, description2);
			preparedStatement.setString(9, description3);
			preparedStatement.setString(10, sujet);
			preparedStatement.setTimestamp(11, date_debut);
			preparedStatement.setTimestamp(12, date_fin);
			preparedStatement.setString(13, lieu);
			preparedStatement.setString(14, image_link);
			
			preparedStatement.executeUpdate();
		}

		finally {
			if(preparedStatement!=null)
				preparedStatement.close();
		}
	}
	
	
	public static boolean checkTimesStatut(Timestamp date_debut, Timestamp date_fin, Connection connection) throws SQLException {		
		String select = "SELECT date_debut, date_fin FROM public.activite ";
		PreparedStatement preparedStatement = null;
		try{
			preparedStatement=	connection.prepareStatement(select);
			ResultSet res = preparedStatement.executeQuery();
			
			List<Boolean> list = new ArrayList<>();
			while (res.next()) {
				Timestamp date_debut_base = res.getTimestamp("date_debut");
				Timestamp date_fin_base = res.getTimestamp("date_fin");
				
				if(date_debut.after(date_debut_base) && date_debut.before(date_fin_base)) {
					list.add(false);
				}else {
					if(date_fin.after(date_debut_base) && date_fin.before(date_fin_base)) {
						list.add(false);
					}else {
						if(date_fin.after(date_fin_base) && date_debut.after(date_fin_base)) {
							list.add(true);
						}
						if(date_fin.before(date_debut_base) && date_debut.before(date_debut_base)) {
							list.add(true);
						}
					}
				}
			}
			if(list.contains(true))
				return true;
			return false;

		}finally {
			if(preparedStatement!=null)
				preparedStatement.close();
		}	
	}
	
	public static List<HashMap<Object,Object>> getActivites( Connection connection) throws SQLException {
		String select = "SELECT * FROM public.activite"
				+ " WHERE idtype != 11";
		PreparedStatement preparedStatement = null;
		try{
			preparedStatement=	connection.prepareStatement(select);
			

			ResultSet res = preparedStatement.executeQuery();

			
			List<HashMap<Object, Object>> activites = new ArrayList<HashMap<Object,Object>>();
			while (res.next()) {
				HashMap<Object,Object> activite = new HashMap<Object, Object>();

				activite.put("idactivite",res.getInt("idactivite"));
				activite.put("titre",res.getString("titre"));
				activite.put("idtype",res.getInt("idtype"));
				activite.put("idline",res.getInt("idlien"));
				activite.put("stitre1",res.getString("stitre1"));
				activite.put("stitre2",res.getString("stitre2"));
				activite.put("stitre3",res.getString("stitre3"));
				activite.put("description1",res.getString("description1"));
				activite.put("description2",res.getString("description2"));
				activite.put("description3",res.getString("description3"));
				activite.put("sujet",res.getString("sujet"));
				activite.put("lieu",res.getString("lieu"));
				activite.put("date_debut", res.getTimestamp("date_debut"));
				activite.put("date_fin", res.getTimestamp("date_fin"));
				
				activite.put("image_link", res.getString("image_link"));
				activites.add(activite);
			}
			return activites;
		}finally {
			if(preparedStatement!=null)
				preparedStatement.close();
		}	
	}
	
	public static void deleteActivite(Integer idactivite, Connection connection) throws SQLException {
		String delete = "DELETE FROM public.activite"
				+ " WHERE idactivite=?";
		
		PreparedStatement preparedStatement =null;
		
		try{
			preparedStatement =	connection.prepareStatement(delete);
		
			preparedStatement.setLong(1, idactivite);
			
			preparedStatement.executeUpdate();

		}finally {
			if(preparedStatement!=null)
				preparedStatement.close();
			
		}	
		
	}


	public static boolean checkLien(Integer idActivite, Connection connection) throws SQLException {
		String select = "SELECT * FROM public.activite,lien,type_activite"
				+ " WHERE idactivite = ?"
				+ " AND lien.idlien = activite.idlien "
				+ " AND activite.idtype = type_activite.idtype";
		
		PreparedStatement preparedStatement = null;
		try{
			preparedStatement=	connection.prepareStatement(select);
			preparedStatement.setLong(1, idActivite);

			ResultSet res = preparedStatement.executeQuery();

			
			if (res.next()) {
				if(res.getObject("idlien") != null) {
					return true;
				}
			}
			return false;
		}finally {
			if(preparedStatement!=null)
				preparedStatement.close();
		}	
		
	}
	public static HashMap<Object, Object> getActivite(Integer idActivite, Connection connection) throws SQLException {
		
		String select = null;
		
		if(checkLien(idActivite, connection)) {
			select = "SELECT * FROM public.activite,lien,type_activite"
					+ " WHERE idactivite = ?"
					+ " AND lien.idlien = activite.idlien "
					+ " AND activite.idtype = type_activite.idtype";
		}else {
			select = "SELECT * FROM public.activite,type_activite"
					+ " WHERE idactivite = ?"
					+ " AND activite.idtype = type_activite.idtype";
		}
		
		

		PreparedStatement preparedStatement = null;
		try{
			preparedStatement=	connection.prepareStatement(select);
			preparedStatement.setLong(1, idActivite);

			ResultSet res = preparedStatement.executeQuery();

			
			HashMap<Object, Object> activite = new HashMap<Object,Object>();
			if (res.next()) {
				activite.put("idactivite",res.getInt("idactivite"));
				activite.put("titre",res.getString("titre"));
				activite.put("type",res.getString("type"));
				if(checkLien(idActivite, connection)) {
					activite.put("url",res.getString("url"));
				}
				activite.put("stitre1",res.getString("stitre1"));
				activite.put("stitre2",res.getString("stitre2"));
				activite.put("stitre3",res.getString("stitre3"));
				activite.put("description1",res.getString("description1"));
				activite.put("description2",res.getString("description2"));
				activite.put("description3",res.getString("description3"));
				activite.put("sujet",res.getString("sujet"));
				activite.put("lieu",res.getString("lieu"));
				activite.put("date_debut", res.getTimestamp("date_debut"));
				activite.put("date_fin", res.getTimestamp("date_fin"));
				activite.put("image_link", res.getString("image_link"));
			}
			return activite;
		}finally {
			if(preparedStatement!=null)
				preparedStatement.close();
		}	
		
	}

	public static HashMap<Object, Object> getLettre(Integer idActivite, Connection connection) throws SQLException {
		System.out.println(idActivite);
		String select = "SELECT * FROM public.activite"
				+ " WHERE idactivite = ?";
		PreparedStatement preparedStatement = null;
		try{
			preparedStatement=	connection.prepareStatement(select);
			preparedStatement.setLong(1, idActivite);

			ResultSet res = preparedStatement.executeQuery();

			
			HashMap<Object, Object> activite = new HashMap<Object,Object>();
			
			if (res.next()) {
				activite.put("idactivite",res.getInt("idactivite"));
				activite.put("titre",res.getString("titre"));
				activite.put("stitre1",res.getString("stitre1"));
				activite.put("stitre2",res.getString("stitre2"));
				activite.put("stitre3",res.getString("stitre3"));
				activite.put("description1",res.getString("description1"));
				activite.put("description2",res.getString("description2"));
				activite.put("description3",res.getString("description3"));
				activite.put("sujet",res.getString("sujet"));
				activite.put("date_insertion", res.getObject("date_insertion"));
	
			}
			return activite;
		}finally {
			if(preparedStatement!=null)
				preparedStatement.close();
		}	
		
	}

	public static void insertLettreInformation(String sujet, String titre, String stitre1, String description1,
			String stitre2, String description2, String stitre3, String description3, Connection connection) throws SQLException {
		String insert="INSERT INTO public.activite(titre,idtype,stitre1,stitre2,stitre3,description1,description2,description3,sujet,date_insertion)"
				+ " VALUES (?,?,?,?,?,?,?,?,?,?)";
		
		PreparedStatement preparedStatement = null;
		try{
			preparedStatement=	connection.prepareStatement(insert);

			preparedStatement.setString(1, titre);
			preparedStatement.setInt(2, 11);
			preparedStatement.setString(3, stitre1);
			preparedStatement.setString(4, stitre2);
			preparedStatement.setString(5, stitre3);
			preparedStatement.setString(6, description1);
			preparedStatement.setString(7, description2);
			preparedStatement.setString(8, description3);
			preparedStatement.setString(9, sujet);
			preparedStatement.setObject(10, java.time.LocalDate.now());

			preparedStatement.executeUpdate();
		}

		finally {
			if(preparedStatement!=null)
				preparedStatement.close();
		}
	}


	public static void sendMailNotification(String sujet,String titre, LocalDate now, Connection connection) throws SQLException, AddressException, MessagingException {
		String query = "SELECT * "
				+ " FROM utilisateur"
				+ " WHERE validation_tresorier =?";
		
		PreparedStatement preparedStatement = null;
		try{
			preparedStatement=	connection.prepareStatement(query);
			preparedStatement.setBoolean(1, true);

			ResultSet res = preparedStatement.executeQuery();
			
			while (res.next()) {
				System.out.println(res.getString("email"));
				String subject = "Notification Lettre d'Information «"+ titre +"» Ensemble Pour L'Afrique";
				String messageText = "Bonjour Mr/Mme "+res.getString("nom")+ " "+ res.getString("prenom")+",\n"
				        		+ "Nous vous informons qu'une nouvelle lettre d'information a été mis à votre disposition par la présidante le " + now.toString() 
				        		+" portant sur "+ sujet +"."
				        		+ " Nous vous invitons à la consulter sur votre espace Ensemble Pour L'Afrique en cliquant sur la rubrique \"Activités\" puis dans \"Consulter la lettre d'information\". \n\n"
				        		+ "Ceci est un message automatique. Merci de ne pas y répondre.\n\n" 
				        		+ " Cordialement,\n"
				        		+ "L'équipe administrative d'Ensemble Pour l'Afrique.";
			
				MailTools.sendMail(res.getString("email"), subject, messageText);
				
			}
		}finally {
			if(preparedStatement!=null)
				preparedStatement.close();
		}	
		
	}


	public static List<HashMap<Object, Object>> getLettreInformations(Connection connection) throws SQLException {
		String select = "SELECT * FROM public.activite"
				+ " WHERE idtype = ?"
				+ " ORDER BY date_insertion";
		PreparedStatement preparedStatement = null;
		try{
			preparedStatement=	connection.prepareStatement(select);
			
			preparedStatement.setLong(1, 11);

			ResultSet res = preparedStatement.executeQuery();

			
			List<HashMap<Object, Object>> activites = new ArrayList<HashMap<Object,Object>>();
			while (res.next()) {
				HashMap<Object,Object> activite = new HashMap<Object, Object>();

				activite.put("idactivite",res.getString("idactivite"));
				activite.put("titre",res.getString("titre"));
				activite.put("stitre1",res.getString("stitre1"));
				activite.put("stitre2",res.getString("stitre2"));
				activite.put("stitre3",res.getString("stitre3"));
				activite.put("description1",res.getString("description1"));
				activite.put("description2",res.getString("description2"));
				activite.put("description3",res.getString("description3"));
				activite.put("sujet",res.getString("sujet"));
				activite.put("date",res.getObject("date_insertion"));
				activites.add(activite);
			}
			return activites;
		}finally {
			if(preparedStatement!=null)
				preparedStatement.close();
		}	
	}
	

}
