package modele.tools;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import modele.db.Database;


public class UserTools {
	
	public static void addUser(String nom, String prenom, String email, String password,String adresse,String paysdorigine, String centredinterets, String path,Connection connection) throws SQLException {
	
		String insert="INSERT INTO public.utilisateur(email, paysdorigine, motdepasse, nom, prenom, adresse,validation_tresorier, centredinterets, date_inscription,avatar)"

				+ " VALUES (?,?, crypt(?,gen_salt('md5')),?,?,?,?,?,?,?)";

		PreparedStatement preparedStatement = null;
		try{
			preparedStatement=	connection.prepareStatement(insert);

			preparedStatement.setString(1, email);
			preparedStatement.setString(2, paysdorigine);
			preparedStatement.setString(3, password);
			preparedStatement.setString(4, nom);
			preparedStatement.setString(5, prenom);		
			preparedStatement.setString(6, adresse);		
			preparedStatement.setBoolean(7, false);
			preparedStatement.setString(8, centredinterets);
			preparedStatement.setObject(9, java.time.LocalDate.now());
			preparedStatement.setString(10, path);



			preparedStatement.executeUpdate();
		}

		finally {
			if(preparedStatement!=null)
				preparedStatement.close();
		}
	}
	
public static int getIdUtilisateur(String email,Connection connection ) throws SQLException{
		
		String select = "SELECT  idutilisateur FROM utilisateur "
				+ "WHERE utilisateur.email=?";
		
		PreparedStatement preparedStatement = null;
		try{
			preparedStatement =	connection.prepareStatement(select);
			preparedStatement.setString(1, email);
	
			ResultSet res = preparedStatement.executeQuery();

			if (res.next()) 
				return res.getInt("idutilisateur");	
			else 
				return -1;
			
		} finally {
			if(preparedStatement!=null)
				preparedStatement.close();
		}
}

public static void addRole(String email, int role, Connection connection) throws SQLException {
	
		int idutlisateur = UserTools.getIdUtilisateur(email, connection);
		
		String insert= "INSERT INTO public.role_utilisateur(idutilisateur, idrole)"
				+   "	VALUES (?, ?)";
				
	
		PreparedStatement preparedStatement = null;
		try{
			preparedStatement =	connection.prepareStatement(insert);
			preparedStatement.setLong(1, idutlisateur);
			preparedStatement.setLong(2, role);

			preparedStatement.executeUpdate();
		}

		finally {
			if(preparedStatement!=null)
				preparedStatement.close();
		}
	}

public static List<HashMap<Object, Object>> listUserbyRole(Integer idrole, Connection connection) throws SQLException {
	String select = "SELECT * FROM public.utilisateur,role_utilisateur"
			+ " WHERE utilisateur.idutilisateur = role_utilisateur.idutilisateur"
			+ " AND role_utilisateur.idrole = ?"
			+ " AND utilisateur.validation_tresorier = true";
	PreparedStatement preparedStatement = null;
	try{
		preparedStatement =	connection.prepareStatement(select);

		preparedStatement.setLong(1, idrole);
		ResultSet res = preparedStatement.executeQuery();

	
		List<HashMap<Object, Object>> utilisateurs = new ArrayList<HashMap<Object,Object>>();
		while (res.next()) {
			
			HashMap<Object,Object> utilisateur = new HashMap<Object, Object>();
			
			utilisateur.put("idutilisateur",res.getInt("idutilisateur"));
			utilisateur.put("nom",res.getString("nom"));
			utilisateur.put("prenom",res.getString("prenom"));
			utilisateur.put("email",res.getString("email"));
			utilisateur.put("adresse",res.getString("adresse"));
			utilisateur.put("role_type",res.getInt("idrole"));
			utilisateur.put("paysdorigine",res.getString("paysdorigine"));
			utilisateur.put("validation_date",res.getDate("validation_date"));
			
			switch(res.getInt("idrole")) {
				case 0:
					utilisateur.put("role","Adhérant de l'association");
				break;
				case 1:
					utilisateur.put("role","Abonné au forum");
				break;
				case 2:
					utilisateur.put("role","Membre de l'association");
				break;
				case 3:
					utilisateur.put("role","Membre du bureau l'association");
				break;
				case 4:
					utilisateur.put("role","Membre du conseil d'administration de l'association");
				break;
				case 5:
					utilisateur.put("role","Présidant de l'association");
				break;
				case 6:
					utilisateur.put("role","Trésorier de l'association");
				break;
				case 7:
					utilisateur.put("role","Secrétaire du bureau de l'association");
				break;
				case 8:
					utilisateur.put("role","Administrateur technique");
				break;
			}
			utilisateurs.add(utilisateur);
		}
		return utilisateurs;
	}finally {
		if(preparedStatement!=null)
			preparedStatement.close();
	}	
}

public static List<HashMap<Object, Object>> listUserbyValidation(Integer idRole, Boolean validation, Connection connection) throws SQLException {
	String select = "SELECT utilisateur.idutilisateur, utilisateur.nom, utilisateur.prenom,"
			+ "utilisateur.email, utilisateur.adresse, utilisateur.paysdorigine, utilisateur.validation_tresorier,"
			+ "utilisateur.validation_date,utilisateur.date_inscription, role_utilisateur.idrole"
			+ " FROM public.utilisateur, role_utilisateur"
			+ " WHERE utilisateur.validation_tresorier = ?"
			+ " AND utilisateur.idutilisateur = role_utilisateur.idutilisateur"
			+ " AND role_utilisateur.idrole = ?";
	PreparedStatement preparedStatement = null;
	try{
		preparedStatement =	connection.prepareStatement(select);

		System.out.println(validation);
		preparedStatement.setBoolean(1, validation);
		preparedStatement.setLong(2, idRole);
		
		ResultSet res = preparedStatement.executeQuery();

	
		List<HashMap<Object, Object>> utilisateurs = new ArrayList<HashMap<Object,Object>>();
		while (res.next()) {
			
			HashMap<Object,Object> utilisateur = new HashMap<Object, Object>();
			
			utilisateur.put("idutilisateur",res.getInt("idutilisateur"));
			utilisateur.put("nom",res.getString("nom"));
			utilisateur.put("prenom",res.getString("prenom"));
			utilisateur.put("email",res.getString("email"));
			utilisateur.put("adresse",res.getString("adresse"));
			utilisateur.put("paysdorigine",res.getString("paysdorigine"));
			utilisateur.put("validation",res.getBoolean("validation_tresorier"));
			utilisateur.put("validation_date",res.getDate("validation_date"));
			utilisateur.put("inscription_date",res.getDate("date_inscription"));
			
			switch(res.getInt("idrole")) {
				case 0:
					utilisateur.put("role","Adhérant de l'association");
				break;
				case 1:
					utilisateur.put("role","Abonné au forum");
				break;
				case 2:
					utilisateur.put("role","Membre de l'association");
				break;
				case 3:
					utilisateur.put("role","Membre du bureau l'association");
				break;
				case 4:
					utilisateur.put("role","Membre du conseil d'administration de l'association");
				break;
				case 5:
					utilisateur.put("role","Présidant de l'association");
				break;
				case 6:
					utilisateur.put("role","Trésorier de l'association");
				break;
				case 7:
					utilisateur.put("role","Secrétaire du bureau de l'association");
				break;
				case 8:
					utilisateur.put("role","Administrateur technique");
				break;
			}
			utilisateurs.add(utilisateur);
		}
		return utilisateurs;
	}finally {
		if(preparedStatement!=null)
			preparedStatement.close();
	}	
}


public static void main(String [] args) {
	Connection connection = null;
	try {
		connection = Database.getConnection();
		System.out.println(UserTools.listUserbyRole(0,connection));
	} catch (SQLException e) {
		e.printStackTrace();
		
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
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

public static String getUserbyEmail(int idutilisateur, Connection connection) throws SQLException {
	String select = "SELECT  email FROM utilisateur "
			+ "WHERE utilisateur.idutilisateur=?";
	
	PreparedStatement preparedStatement = null;
	try{
		preparedStatement =	connection.prepareStatement(select);
		preparedStatement.setInt(1,idutilisateur );

		ResultSet res = preparedStatement.executeQuery();

		if (res.next()) 
			return res.getString("email");	
		else 
			return null;
		
	} finally {
		if(preparedStatement!=null)
			preparedStatement.close();
	}
}


public static void sendMailRelance(String idutilisateur, Connection connection) throws SQLException, AddressException, MessagingException {
	String query = "SELECT * "
			+ " FROM utilisateur"
			+ " WHERE idutilisateur =?";
	
	PreparedStatement preparedStatement = null;
	try{
		preparedStatement=	connection.prepareStatement(query);
		preparedStatement.setLong(1, Integer.parseInt(idutilisateur));

		ResultSet res = preparedStatement.executeQuery();
		
		while (res.next()) {
			String subject = null;
			String messageText = null;

			subject = "Rappel de cotisation a payé pour la compte de Ensemble Pour l'Afrique";
			messageText = "Bonjour Mr/Mme "+res.getString("nom") + " "+ res.getString("prenom")+"\n"
			        		+ "Ceci est un mail de relance de paiement de cotisation pour le compte de l'association Ensemble Pour l'Afrique. \n\n" 
			        		+ "La date du règlement, la fréquence du versement et les modalités de détermination du montant de la cotisation sont fixées dans les statuts de l'association."
			        		+ " dont vous avez pris connaissance lors de votre adhésion à l'association.\n\n"
			        		+ "Pour rappel, la date de votre dernier paiement est le "+ res.getDate("validation_date")+ "."
			        		+ " Veuillez vous rappocher de l'administration de l'association.\nNous nous excusons de ne pas pouvoir offir un moyen de paiement dématerialisé. \n\n"
			        		+ " Cordialement,\n"
			        		+ "L'équipe administrative d'Ensemble Pour l'Afrique.";
		
			MailTools.sendMail(res.getString("email"), subject, messageText);
			
		}
	}finally {
		if(preparedStatement!=null)
			preparedStatement.close();
	}	
	
}

public static List<HashMap<Object, Object>> getUserInformations(int idutilisateur, Connection connection) throws SQLException {
	String select = "SELECT * FROM public.utilisateur"
			+ " WHERE utilisateur.idutilisateur = ?";
	PreparedStatement preparedStatement = null;
	try{
		preparedStatement =	connection.prepareStatement(select);
		preparedStatement.setLong(1, idutilisateur);
		ResultSet res = preparedStatement.executeQuery();
		System.out.println("début de traitement");
	
		List<HashMap<Object, Object>> utilisateurs = new ArrayList<HashMap<Object,Object>>();
		while (res.next()) {
			
			HashMap<Object,Object> utilisateur = new HashMap<Object, Object>();
			
			utilisateur.put("idutilisateur",res.getInt("idutilisateur"));
			utilisateur.put("nom",res.getString("nom"));
			utilisateur.put("prenom",res.getString("prenom"));
			utilisateur.put("email",res.getString("email"));
			utilisateur.put("adresse",res.getString("adresse"));
			utilisateur.put("paysdorigine",res.getString("paysdorigine"));
			utilisateur.put("validation",res.getBoolean("validation_tresorier"));
			utilisateur.put("validation_date",res.getDate("validation_date"));
			
				// Select rôles
				String selectRole = "SELECT * FROM role_utilisateur"
						+ " WHERE role_utilisateur.idutilisateur = ?";
				PreparedStatement preparedStatementRole = connection.prepareStatement(selectRole);
				preparedStatementRole.setLong(1, idutilisateur);
				
				ResultSet res2 = preparedStatementRole.executeQuery();
				
				ArrayList<Object> roles = new ArrayList<>();
				
				while(res2.next()) {
					switch(res2.getInt("idrole")) {
						case 0:
							roles.add("Adhérant de l'association");
						break;
						case 1:
							roles.add("Abonné au forum");
						break;
						case 2:
							roles.add("Membre de l'association");
						break;
						case 3:
							roles.add("Membre du bureau l'association");
						break;
						case 4:
							roles.add("Membre du conseil d'administration de l'association");
						break;
						case 5:
							roles.add("Présidant de l'association");
						break;
						case 6:
							roles.add("Trésorier de l'association");
						break;
						case 7:
							roles.add("Secrétaire du bureau de l'association");
						break;
						case 8:
							roles.add("Administrateur technique");
						break;
					}
				}
			utilisateur.put("roles", roles);
			System.out.println("Renvoie de données");
			utilisateurs.add(utilisateur);
			}
			System.out.println("FIN de traitement");
			return utilisateurs;
		
	}finally {
		if(preparedStatement!=null)
			preparedStatement.close();
	}	
	}

public static void updateUserName(int idutilisateur, String nom, Connection connection) throws SQLException {

	String update = "UPDATE public.utilisateur"
			+ " SET nom =?"
			+ " WHERE idutilisateur=?";
	
	PreparedStatement preparedStatement =null;
	try{
		preparedStatement=	connection.prepareStatement(update);
		
		preparedStatement.setString(1, nom);
		preparedStatement.setInt(2, idutilisateur);
		
		preparedStatement.executeUpdate();

	}finally {
		if(preparedStatement!=null)
			preparedStatement.close();
		
	}	
}

public static void updateUserSurName(int idutilisateur, String prenom, Connection connection) throws SQLException {
	String update = "UPDATE public.utilisateur"
			+ " SET prenom =?"
			+ " WHERE idutilisateur=?";
	
	PreparedStatement preparedStatement =null;
	try{
		preparedStatement=	connection.prepareStatement(update);
		
		preparedStatement.setString(1, prenom);
		preparedStatement.setInt(2, idutilisateur);
		
		preparedStatement.executeUpdate();

	}finally {
		if(preparedStatement!=null)
			preparedStatement.close();
		
	}	
	
}

public static void updateUserAdresse(int idutilisateur, String adresse, Connection connection) throws SQLException {
	String update = "UPDATE public.utilisateur"
			+ " SET adresse =?"
			+ " WHERE idutilisateur=?";
	
	PreparedStatement preparedStatement =null;
	try{
		preparedStatement=	connection.prepareStatement(update);
		
		preparedStatement.setString(1, adresse);
		preparedStatement.setInt(2, idutilisateur);
		
		preparedStatement.executeUpdate();

	}finally {
		if(preparedStatement!=null)
			preparedStatement.close();
		
	}	
	
}

public static void updateUserContry(int idutilisateur, String paysdorigine, Connection connection) throws SQLException {
	String update = "UPDATE public.utilisateur"
			+ " SET paysdorigine=?"
			+ " WHERE idutilisateur=?";
	
	PreparedStatement preparedStatement =null;
	try{
		preparedStatement=	connection.prepareStatement(update);
		
		preparedStatement.setString(1, paysdorigine);
		preparedStatement.setInt(2, idutilisateur);
		
		preparedStatement.executeUpdate();

	}finally {
		if(preparedStatement!=null)
			preparedStatement.close();
		
	}	
	
}

public static void updateUserEmail(int idutilisateur, String email, Connection connection) throws SQLException {
	String update = "UPDATE public.utilisateur"
			+ " SET email =?"
			+ " WHERE idutilisateur=?";
	
	PreparedStatement preparedStatement =null;
	try{
		preparedStatement=	connection.prepareStatement(update);
		
		preparedStatement.setString(1, email);
		preparedStatement.setInt(2, idutilisateur);
		
		preparedStatement.executeUpdate();

	}finally {
		if(preparedStatement!=null)
			preparedStatement.close();
		
	}	
	
}

public static void deleteRole(int idutilisateur, int idrole, Connection connection) throws SQLException {
	String insert= "DELETE FROM role_utilisateur"
			+ " WHERE idutilisateur=?"
			+ " AND idrole=?";
			

	PreparedStatement preparedStatement = null;
	try{
		preparedStatement =	connection.prepareStatement(insert);
		preparedStatement.setLong(1, idutilisateur);
		preparedStatement.setLong(2, idrole);

		preparedStatement.executeUpdate();
	}

	finally {
		if(preparedStatement!=null)
			preparedStatement.close();
	}
	
}

public static String getAvatar(String email,Connection connection ) throws SQLException{
	String select = "SELECT  avatar FROM utilisateur "
			+ "WHERE utilisateur.email=?";
	
	PreparedStatement preparedStatement = null;
	try{
		preparedStatement =	connection.prepareStatement(select);
		preparedStatement.setString(1, email);

		ResultSet res = preparedStatement.executeQuery();

		if (res.next()) 
			return res.getString("avatar");	
		else 
			return "defaultAvatar.png" ;
		
	} finally {
		if(preparedStatement!=null)
			preparedStatement.close();
	}
	
}

}
