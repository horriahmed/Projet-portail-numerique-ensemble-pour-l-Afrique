package modele.tools;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.joda.time.DateTime;

import modele.db.Database;



public class ReunionTools {

	public static boolean checkTimesStatut(Timestamp date_debut, Timestamp date_fin, Connection connection) throws SQLException {		
		String select = "SELECT date_debut, date_fin FROM public.reuniondinstance ";
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
			if(list.isEmpty())
				return true;
			return false;

		}finally {
			if(preparedStatement!=null)
				preparedStatement.close();
		}	
	}
	
	// À vérifier 
	public static List<ArrayList<Object>> getReunion(Timestamp date_debut, Timestamp date_fin, Connection connection) throws SQLException {
		String select = "SELECT * FROM public.reuniondinstance WHERE  date_fin > ? OR date_debut <  ?";
		PreparedStatement preparedStatement = null;
		try{
			preparedStatement=	connection.prepareStatement(select);
			preparedStatement.setTimestamp(1, date_debut);
			preparedStatement.setTimestamp(2, date_fin);

			ResultSet res = preparedStatement.executeQuery();

			List<ArrayList<Object>> reunions = new ArrayList<ArrayList<Object>>();
			while (res.next()) {
				ArrayList<Object> reunion = new ArrayList<Object>();
				reunion.add(res.getString("titre"));
				reunion.add(res.getString("typereunion"));
				reunion.add(res.getTimestamp("date_debut"));
				reunion.add(res.getTimestamp("date_fin"));
				reunions.add(reunion);
			}
			return reunions;
		}finally {
			if(preparedStatement!=null)
				preparedStatement.close();
		}	
	}

	public static void insertReunion(String titre, String lieu, String sujet, String description, Integer type_reunion,
			Timestamp date_debut, Timestamp date_fin, Connection connection) throws SQLException {
		
		System.out.println(date_debut);
		System.out.println(date_fin);
		String insert="INSERT INTO public.reuniondinstance(typereunion, lieu, date_debut, date_fin, titre, sujet, description, validation)"
				+ " VALUES (?,?,?,?,?,?,?,?)";

		PreparedStatement preparedStatement = null;
		try{
			preparedStatement=	connection.prepareStatement(insert);

			preparedStatement.setLong(1, type_reunion);
			preparedStatement.setString(2, lieu);
			preparedStatement.setTimestamp(3, date_debut);
			preparedStatement.setTimestamp(4, date_fin);
			preparedStatement.setString(5, titre);
			preparedStatement.setString(6, sujet);
			preparedStatement.setString(7,description);
			preparedStatement.setBoolean(8,false);


			preparedStatement.executeUpdate();
		}

		finally {
			if(preparedStatement!=null)
				preparedStatement.close();
		}
	}

	public static Integer getIdReunion(String titre, String sujet, Integer type_reunion, Timestamp date_debut,
			Timestamp date_fin, Connection connection) throws SQLException {
		String select = "SELECT idreunion FROM reuniondinstance WHERE (titre,sujet,typereunion,date_debut,date_fin) = (?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try{
			preparedStatement=	connection.prepareStatement(select);
			preparedStatement.setString(1, titre);
			preparedStatement.setString(2, sujet);
			preparedStatement.setInt(3, type_reunion);
			preparedStatement.setTimestamp(4, date_debut);
			preparedStatement.setTimestamp(5, date_fin);
			
			ResultSet res = preparedStatement.executeQuery();

			if (res.next()) 
				return res.getInt("idreunion");
			else 
				return -1;

		}finally {
			if(preparedStatement!=null)
				preparedStatement.close();
		}

	}
	
	public static boolean checkValidation(Integer idReunion, Connection connection) throws SQLException {
		String select = "SELECT * FROM reuniondinstance WHERE idreunion = ? AND validation = ?";
		PreparedStatement preparedStatement = null;
		try{
			preparedStatement=	connection.prepareStatement(select);
			preparedStatement.setLong(1, idReunion);
			preparedStatement.setBoolean(2, true);

			ResultSet res = preparedStatement.executeQuery();

			if (res.next()) 
				return true;
			else 
				return false;	

		}finally {
			if(preparedStatement!=null)
				preparedStatement.close();
		}	
	}

	public static void updateReunion(Integer idReunion, boolean validation, Connection connection) throws SQLException {
		String update = "UPDATE public.reuniondinstance"
				+ " SET validation=?"
				+ " WHERE idreunion=?";
		
		PreparedStatement preparedStatement =null;
		
		try{
			preparedStatement =	connection.prepareStatement(update);
			
			preparedStatement.setBoolean(1, validation);
			preparedStatement.setLong(2, idReunion);
			
			preparedStatement.executeUpdate();

		}finally {
			if(preparedStatement!=null)
				preparedStatement.close();
			
		}	
		
	}

	public static void deleteReunion(Integer idReunion, Connection connection) throws SQLException {
		String update = "DELETE FROM public.reuniondinstance"
				+ " WHERE idreunion=?";
		
		PreparedStatement preparedStatement =null;
		
		try{
			preparedStatement =	connection.prepareStatement(update);
		
			preparedStatement.setLong(1, idReunion);
			
			preparedStatement.executeUpdate();

		}finally {
			if(preparedStatement!=null)
				preparedStatement.close();
			
		}	
		
	}

	public static void sendMailToParticipants(Integer idReunion, Connection connection) throws SQLException, AddressException, MessagingException {
		String query = "SELECT utilisateur.email,utilisateur.prenom,utilisateur.nom,"
				+ "reuniondinstance.titre, reuniondinstance.date_debut, reuniondinstance.date_fin,"
				+ " reuniondinstance.description, reuniondinstance.sujet, reuniondinstance.lieu, reuniondinstance.typereunion"
				+ " FROM participation_utilisateur_reunion,utilisateur,reuniondinstance"
				+ " WHERE participation_utilisateur_reunion.idreunion =?"
				+ " AND participation_utilisateur_reunion.idutilisateur = utilisateur.idutilisateur"
				+ " AND participation_utilisateur_reunion.idreunion = reuniondinstance.idreunion";
				
		PreparedStatement preparedStatement = null;
		try{
			preparedStatement=	connection.prepareStatement(query);
			preparedStatement.setLong(1, idReunion);

			ResultSet res = preparedStatement.executeQuery();

			while (res.next()) {
				String subject = null;
				String messageText = null;
				
				switch(res.getInt("typereunion") ) {
					case 1:
						subject = "Convocation à la réunion du conseil d'administration d'Ensemble Pour l'Afrique. ";
						messageText = "Bonjour Mr/Mme "+res.getString("nom") + " "+ res.getString("prenom")+"\n "
				        		+ "Vous êtes convoqué à la réunion du conseil d'administration qui se tiendra le "+res.getTimestamp("date_debut")+" jusqu'au "+res.getTimestamp("date_fin") 
				        		+ "ayant pour sujet : "+res.getString("sujet")+ "et portant sur "+ res.getString("titre")
				        		+". Veuillez vous rendre à l'adresse suivante : " + res.getString("lieu")+"."
				        		+" La réunion est présidé par Mme la présidante de la 'associaton et les personnes convoquées sont les membres du conseil d'administration.\n\n"
				        		+"L'ordre du jour est le suivant : "+ res.getString("description")+ "\n"
				        		+ "Veuillez vous munir de cet e-mail, il vous servira de ticket d'entrée. \n\n"
				        		+ "Cordialement,\n"
				        		+ "L'équipe administrative d'Ensemble Pour l'Afrique.";
						break;
					case 2:
						subject = "Convocation à la réunion des membres du bureau d'Ensemble Pour l'Afrique. ";
						messageText = "Bonjour Mr/Mme "+res.getString("nom") + " "+ res.getString("prenom")+"\n "
				        		+ "Vous êtes convoqué à la réunion des membres du bureau qui se tiendra le "+res.getTimestamp("date_debut")+" jusqu'au "+res.getTimestamp("date_fin") 
				        		+ "ayant pour sujet : "+res.getString("sujet")+ "et portant sur "+ res.getString("titre")
				        		+". Veuillez vous rendre à l'adresse suivante : " + res.getString("lieu")+"."
				        		+" La réunion est présidé par Mme la présidante de la 'associaton et les personnes convoquées sont les membres du bureau administratif sauf ceux siégeant au conseil d'administration.\n\n"
				        		+"L'ordre du jour est le suivant : "+ res.getString("description")+ "\n" 
				        		+ "Veuillez vous munir de cet e-mail, il vous servira de ticket d'entrée. \n\n"
				        		+ "Cordialement,\n" 
				        		+ "L'équipe administrative d'Ensemble Pour l'Afrique.";
						break;
					case 3:
						subject = "Convocation à l'assemblé générable d'Ensemble Pour l'Afrique. ";
						messageText = "Bonjour Mr/Mme "+res.getString("nom") + " "+ res.getString("prenom")+"\n "
				        		+ "Vous êtes convoqué à l'assemblé générale de l'association Ensmeble Pour l'Afrique qui se tiendra le "+res.getTimestamp("date_debut")+" jusqu'au "+res.getTimestamp("date_fin") 
				        		+ "ayant pour sujet : "+res.getString("sujet")+ "et portant sur "+ res.getString("titre")
				        		+". Veuillez vous rendre à l'adresse suivante : " + res.getString("lieu")+"."
				        		+" La réunion est présidé par Mme la présidante de la 'associaton et les personnes convoquées sont les membres du bureau administratif sauf ceux siégeant au conseil d'administration.\n\n"
				        		+"L'ordre du jour est le suivant : "+ res.getString("description")+ "\n\n" 
				        		+ "Veuillez vous munir de cet e-mail, il vous servira de ticket d'entrée. \n\n"
				        		+ "Cordialement,\n"  
				        		+ "L'équipe administrative d'Ensemble Pour l'Afrique.";
						break;
				}
				MailTools.sendMail(res.getString("email"), subject, messageText);
				
			}
		}finally {
			if(preparedStatement!=null)
				preparedStatement.close();
		}	
	}

	public static void insertParticipants(Integer id_reunion, Connection connection) throws SQLException {
		System.out.println("insertParticiapants");
		int typeReunion = ReunionTools.getType(id_reunion, connection);
		String select = null;
		PreparedStatement preparedStatement = null;
		try {
			switch(typeReunion) {
				case 1: // CA (Présidant + Membre CA)
					select = "SELECT DISTINCT utilisateur.idutilisateur FROM role_utilisateur, utilisateur"
							+ " WHERE role_utilisateur.idrole = 4 OR role_utilisateur.idrole = 5"
							+ " AND utilisateur.validation_tresorier = true AND role_utilisateur.idutilisateur = utilisateur.idutilisateur";
					break;
				case 2: // Reunion des membres du bureau (Présidant + Membre du bureau)
					select = "SELECT DISTINCT utilisateur.idutilisateur FROM role_utilisateur, utilisateur"
							+ " WHERE role_utilisateur.idrole = 5 OR role_utilisateur.idrole = 3"
							+ " AND utilisateur.validation_tresorier = true AND role_utilisateur.idutilisateur = utilisateur.idutilisateur";
					break;
				case 3: // Assemblé générale ( ALL - abonné)
					select = "SELECT DISTINCT utilisateur.idutilisateur FROM role_utilisateur, utilisateur"
							+ " WHERE role_utilisateur.idrole <> 1 "
							+ " AND utilisateur.validation_tresorier = true AND role_utilisateur.idutilisateur = utilisateur.idutilisateur ";
					break;
			}
			preparedStatement =	connection.prepareStatement(select);
			ResultSet res = preparedStatement.executeQuery();
			
			String insert ="INSERT INTO public.participation_utilisateur_reunion(idutilisateur,idreunion)"
					+ " VALUES (?,?)";
			
			int i = 0;
			while (res.next()) {
				System.out.println(res.getInt("idutilisateur"));
				System.out.println(i++);
				PreparedStatement preparedStatementInsert = null;
				try{
					preparedStatementInsert =	connection.prepareStatement(insert);

					preparedStatementInsert.setLong(1, res.getLong("idutilisateur"));
					preparedStatementInsert.setLong(2, id_reunion);
	
					preparedStatementInsert.executeUpdate();
				}

				finally {
					if(preparedStatementInsert!=null)
						preparedStatementInsert.close();
				}
			}
		}finally {
			if(preparedStatement!=null)
				preparedStatement.close();
		}	
		
	}

	public static void insertVotants(Integer id_reunion, Connection connection) throws SQLException {
		String select = "SELECT DISTINCT utilisateur.idutilisateur FROM role_utilisateur, utilisateur"
				+ " WHERE idrole<>? "
				+ " AND utilisateur.validation_tresorier = true AND utilisateur.idutilisateur = role_utilisateur.idutilisateur";
		PreparedStatement preparedStatement = null;
		try{
			preparedStatement=	connection.prepareStatement(select);
			preparedStatement.setLong(1, 1);
			
			ResultSet res = preparedStatement.executeQuery();
			
			String insert ="INSERT INTO public.vote_utilisateur_reunion(idutilisateur,idreunion)"
					+ " VALUES (?,?)";

			while (res.next()) {
				PreparedStatement preparedStatementInsert = null;
				try{
					preparedStatementInsert =	connection.prepareStatement(insert);

					preparedStatementInsert.setLong(1, res.getLong("idutilisateur"));
					preparedStatementInsert.setLong(2, id_reunion);
	
					preparedStatementInsert.executeUpdate();
				}

				finally {
					if(preparedStatementInsert!=null)
						preparedStatementInsert.close();
				}
			}
		}finally {
			if(preparedStatement!=null)
				preparedStatement.close();
		}	
	}

	public static int getType(Integer idReunion, Connection connection) throws SQLException {
		String select = "SELECT typereunion FROM reuniondinstance WHERE idreunion = ?";
		
		PreparedStatement preparedStatement = null;
		try{
			preparedStatement=	connection.prepareStatement(select);
			preparedStatement.setLong(1, idReunion);

			ResultSet res = preparedStatement.executeQuery();

			if (res.next()) 
				return res.getInt("typereunion");	
			else 
				return -1;

		}finally {
			if(preparedStatement!=null)
				preparedStatement.close();
		}	
	}

	public static void sendMailToVotants(Integer idReunion, Connection connection) throws SQLException, AddressException, MessagingException {
		String query = "SELECT utilisateur.email,utilisateur.prenom,utilisateur.nom,"
				+ "reuniondinstance.titre, reuniondinstance.date_debut, reuniondinstance.date_fin,"
				+ "reuniondinstance.description, reuniondinstance.sujet, reuniondinstance.lieu"
				+ " FROM vote_utilisateur_reunion,utilisateur,reuniondinstance"
				+ " WHERE vote_utilisateur_reunion.idreunion =?"
				+ " AND vote_utilisateur_reunion.idutilisateur = utilisateur.idutilisateur"
				+ " AND vote_utilisateur_reunion.idreunion = reuniondinstance.idreunion";
		
		PreparedStatement preparedStatement = null;
		try{
			preparedStatement=	connection.prepareStatement(query);
			preparedStatement.setLong(1, idReunion);

			ResultSet res = preparedStatement.executeQuery();

			while (res.next()) {
				String subject = null;
				String messageText = null;

				subject = "Droit de vote à l'assemblé générable d'Ensemble Pour l'Afrique.";
				messageText = "Bonjour Mr/Mme "+res.getString("nom") + " "+ res.getString("prenom")+"\n "
				        		+ "Vous recevez cette e-mail car vous avez le droit et l'obligation d'assister à l'assemblé générale de l'association Ensmeble Pour l'Afrique qui se tiendra le "+res.getTimestamp("date_debut")+" jusqu'au "+res.getTimestamp("date_fin") 
				        		+ "ayant pour sujet : "+res.getString("sujet")+ "et portant sur "+ res.getString("titre")
				        		+". Veuillez vous rendre à l'adresse suivante : " + res.getString("lieu")+"."
				        		+"L'ordre du jour est le suivant : "+ res.getString("description")+ "\n\n" 
				        		+ "Pour accéder à l'évenement avec la qualité de votant, veuillez vous munir de cet e-mail ainsi que de la convocation reçue. \n\n"
				        		+ "Cordialement,\n"  
				        		+ "L'équipe administrative d'Ensemble Pour l'Afrique.";
			
				MailTools.sendMail(res.getString("email"), subject, messageText);
				
			}
		}finally {
			if(preparedStatement!=null)
				preparedStatement.close();
		}	
		
	}
	
	public static void main( String [] args) {
		DateTime datetime = new DateTime(2020,4,6,13,30,0);
        Timestamp date_debut=new Timestamp(datetime.getMillis());  
        
		DateTime dateTimeFin = new DateTime(2020,4,6,14,30,0);
        Timestamp date_fin=new Timestamp(dateTimeFin.getMillis()); 
        
        DateTime dateTimeDebut2eme = new DateTime(2020,4,10,14,0,0);
        Timestamp date_debut_2eme=new Timestamp(dateTimeDebut2eme.getMillis()); 
        
        DateTime dateTimeFin2eme = new DateTime(2020,4,10,14,10,0);
        Timestamp date_fin_2eme=new Timestamp(dateTimeFin2eme.getMillis()); 
		
		Connection connection = null;
		try {
			try {
				connection = Database.getConnection();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println(ReunionTools.checkTimesStatut(date_debut_2eme, date_fin_2eme, connection));
			ReunionTools.insertReunion("Reunion 0", "Paris 5ème", "Global Warming", "Bla Bla Bla", 3, date_debut, date_fin, connection);
			//int idReunion1 = getIdReunion("Reunion 0", "Global Warming", 3, date_debut, date_fin, connection);
			/*
			System.out.println("Insertion");
			
			ReunionTools.insertReunion("Reunion 1", "Paris 5ème", "Global Warming", "Bla Bla Bla", 2, date_debut_2eme, date_fin_2eme, connection);
			
			
			System.out.println("Informations...");
			int idReunion1 = getIdReunion("Reunion 0", "Global Warming", 3, date_debut, date_fin, connection);
			int idReunion2 = getIdReunion("Reunion 1", "Global Warming saison 2", 1, date_debut_2eme, date_fin_2eme, connection);
			
			System.out.println("Cheking...");
			System.out.println("Status 1 : "+ReunionTools.checkValidation(idReunion1, connection));
			System.out.println("Status 2 : "+ReunionTools.checkValidation(idReunion2, connection));
		
			System.out.println("Validation 1");
			ReunionTools.updateReunion(idReunion1, true, connection);
			System.out.println("Status 1 : "+ReunionTools.checkValidation(idReunion1, connection));
			
			System.out.println("Insert Participants");
			if(ReunionTools.checkValidation(idReunion1, connection)) {
				ReunionTools.insertParticipants(idReunion1, connection);
				ReunionTools.sendMailToParticipants(idReunion1, connection);
				
				if(ReunionTools.getType(idReunion1, connection) == 3) {
					ReunionTools.insertVotants(idReunion1, connection);
					ReunionTools.sendMailToVotants(idReunion1, connection);
				}
			}
			if(ReunionTools.checkValidation(idReunion2, connection)) {
				System.out.println("Non rentré dans la préparation 2");
				ReunionTools.insertParticipants(idReunion2, connection);
				ReunionTools.sendMailToParticipants(idReunion2, connection);
				
				if(ReunionTools.getType(idReunion2, connection) == 3) {
					ReunionTools.insertVotants(idReunion1, connection);
					ReunionTools.sendMailToVotants(idReunion1, connection);
				}
			}
			System.out.println("Validation 2");
			ReunionTools.updateReunion(idReunion2, true, connection);
			
			if(ReunionTools.checkValidation(idReunion2, connection)) {
				System.out.println("Rentré dans la préparation 2");
				ReunionTools.insertParticipants(idReunion2, connection);
				ReunionTools.sendMailToParticipants(idReunion2, connection);
				
				if(ReunionTools.getType(idReunion2, connection) == 3) {
					ReunionTools.insertVotants(idReunion1, connection);
					ReunionTools.sendMailToVotants(idReunion1, connection);
				}
			}
			
			
			System.out.println("deleting...");
			
			
			System.out.println("FIN");
			
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		*/
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
		
	}

	public static List<HashMap<Object,Object>> getReunions(boolean type, Connection connection) throws SQLException {
		String select = "SELECT * FROM public.reuniondinstance WHERE  validation = ?";
		PreparedStatement preparedStatement = null;
		try{
			preparedStatement=	connection.prepareStatement(select);
			preparedStatement.setBoolean(1,type);

			ResultSet res = preparedStatement.executeQuery();

			
			List<HashMap<Object, Object>> reunions = new ArrayList<HashMap<Object,Object>>();
			while (res.next()) {
				HashMap<Object,Object> reunion = new HashMap<Object, Object>();

				reunion.put("idReunion",res.getString("idreunion"));
				reunion.put("titre",res.getString("titre"));
				switch(Integer.parseInt(res.getString("typereunion"))) {
					case 1:
						reunion.put("type","Conseil d'administration");
						break;
					case 2:
						reunion.put("type","Réunion des membres du bureau");
						break;
					case 3:
						reunion.put("type","Assemblée générale");
						break;
				}
				reunion.put("sujet",res.getString("sujet"));
				reunion.put("date_debut",res.getTimestamp("date_debut"));
				reunion.put("date_fin",res.getTimestamp("date_fin"));
				reunion.put("lieu",res.getString("lieu"));
				reunion.put("description",res.getString("description"));
				reunion.put("validation",res.getString("validation"));
				reunions.add(reunion);
			}
			return reunions;
		}finally {
			if(preparedStatement!=null)
				preparedStatement.close();
		}	
	}

	public static List<HashMap<Object, Object>> getParticipant(Integer idReu, Connection connection) throws SQLException {
		String select = "SELECT * FROM public.participation_utilisateur_reunion,utilisateur,reuniondinstance"
				+ " WHERE participation_utilisateur_reunion.idreunion = ?"
				+ " AND participation_utilisateur_reunion.idutilisateur = utilisateur.idutilisateur"
				+ " AND participation_utilisateur_reunion.idreunion = reuniondinstance.idreunion";
		PreparedStatement preparedStatement = null;
		try{
			preparedStatement=	connection.prepareStatement(select);
			preparedStatement.setInt(1,idReu);

			ResultSet res = preparedStatement.executeQuery();

			
			List<HashMap<Object, Object>> participants = new ArrayList<HashMap<Object,Object>>();
			while (res.next()) {
				HashMap<Object,Object> participant = new HashMap<Object, Object>();

				System.out.println(res.getString("email"));
				participant.put("idreunion",res.getInt("idreunion"));
				participant.put("titre",res.getString("titre"));
				participant.put("type_liste",1);
				participant.put("idutilisateur",res.getString("idutilisateur"));
				participant.put("typereunion",res.getString("typereunion"));
				participant.put("nom",res.getString("nom"));
				participant.put("prenom",res.getString("prenom"));
				participant.put("email",res.getString("email"));
				participant.put("adresse",res.getString("adresse"));
				participant.put("paysdorigine",res.getString("paysdorigine"));
				participants.add(participant);
			}
			return participants;
		}finally {
			if(preparedStatement!=null)
				preparedStatement.close();
		}	
	}

	public static List<HashMap<Object, Object>> getVotant(Integer idReu, Connection connection) throws SQLException {
		String select = "SELECT * FROM public.vote_utilisateur_reunion,utilisateur,reuniondinstance"
				+ " WHERE vote_utilisateur_reunion.idreunion = ?"
				+ " AND vote_utilisateur_reunion.idutilisateur = utilisateur.idutilisateur"
				+ " AND vote_utilisateur_reunion.idreunion = reuniondinstance.idreunion";
		PreparedStatement preparedStatement = null;
		try{
			preparedStatement=	connection.prepareStatement(select);
			preparedStatement.setInt(1,idReu);

			ResultSet res = preparedStatement.executeQuery();

			
			List<HashMap<Object, Object>> participants = new ArrayList<HashMap<Object,Object>>();
			while (res.next()) {
				HashMap<Object,Object> participant = new HashMap<Object, Object>();

				participant.put("type_liste",2);
				participant.put("idreunion",res.getInt("idreunion"));
				participant.put("titre",res.getString("titre"));
				participant.put("idutilisateur",res.getString("idutilisateur"));
				participant.put("typereunion",res.getString("typereunion"));
				participant.put("nom",res.getString("nom"));
				participant.put("prenom",res.getString("prenom"));
				participant.put("email",res.getString("email"));
				participant.put("adresse",res.getString("adresse"));
				participant.put("paysdorigine",res.getString("paysdorigine"));
				participant.put("idreunion",res.getString("idreunion"));
				participants.add(participant);
			}
			return participants;
		}finally {
			if(preparedStatement!=null)
				preparedStatement.close();
		}	
	}

	public static void deleteVotant(Integer idutilisateur, Integer idReunion, Connection connection) throws SQLException {
		String update = "DELETE FROM public.vote_utilisateur_reunion"
				+ " WHERE idreunion=? AND idutilisateur =?";
		
		PreparedStatement preparedStatement =null;
		
		try{
			preparedStatement =	connection.prepareStatement(update);
		
			preparedStatement.setLong(1, idReunion);
			preparedStatement.setLong(2, idutilisateur);
			
			preparedStatement.executeUpdate();

		}finally {
			if(preparedStatement!=null)
				preparedStatement.close();
			
		}	
		
	}

	public static void sendMailCanceling(Integer idReunion, Connection connection) throws SQLException, AddressException, MessagingException {
		System.out.println("sendMailCanceling");
		String query = "SELECT *"
				+ " FROM public.participation_utilisateur_reunion,utilisateur,reuniondinstance"
				+ " WHERE participation_utilisateur_reunion.idreunion = ?"
				+ " AND participation_utilisateur_reunion.idutilisateur = utilisateur.idutilisateur" 
				+ " AND participation_utilisateur_reunion.idreunion = reuniondinstance.idreunion";
		
		PreparedStatement preparedStatement = null;
		try{
			preparedStatement=	connection.prepareStatement(query);
			preparedStatement.setLong(1, idReunion);

			ResultSet res = preparedStatement.executeQuery();

			while (res.next()) {
				String subject = null;
				String messageText = null;
				if(res.getBoolean("validation")) {
					System.out.println("sendMailCanceling if clause");
					subject = "Annulation de la réunion d'Ensemble Pour l'Afrique.";
					messageText = "Bonjour \n "
					        		+ "Suite à un évènement indépendant de notre volonté, on se voit contraint d’annuler la réunion prévue le "+res.getTimestamp("date_debut")+" jusqu'au "+res.getTimestamp("date_fin") 
					        		+ "ayant pour sujet : "+res.getString("sujet")+ "et portant sur "+ res.getString("titre")+".\n"
					        		+" Nous vous prions de bien vouloir nous en excuser et vous informerons dès qu’une prochaine rencontre sera possible.\n\n"
					        		+ "Cordialement,\n"  
					        		+ "L'équipe administrative d'Ensemble Pour l'Afrique.";
				
					MailTools.sendMail(res.getString("email"), subject, messageText);
				}
			}
		}finally {
			if(preparedStatement!=null)
				preparedStatement.close();
		}	
		
		
	}
}
