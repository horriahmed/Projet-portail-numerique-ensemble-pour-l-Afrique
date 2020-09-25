package modele.tools;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modele.db.Database;

public class SessionTools {

	public static boolean userExists(String email, Connection connection) throws SQLException {
		String select = "SELECT * FROM utilisateur where email = ?";
		System.out.println(email);
		PreparedStatement preparedStatement = null;
		try{
			preparedStatement=	connection.prepareStatement(select);
			preparedStatement.setString(1, email);

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

	public static boolean checkPassword(String email, String password, Connection connection) throws SQLException {
		String query = "SELECT email,motdepasse FROM utilisateur where (email,motdepasse) = (?,crypt(?,motdepasse))";

		PreparedStatement preparedStatement =null;
		try{
			preparedStatement=	connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);


			ResultSet res=preparedStatement.executeQuery();

			if (res.next()) 
				return true;
			else
				return false;

		}finally {
			if(preparedStatement!=null)
				preparedStatement.close();
		}	

	}

	
	public static List<Integer> getUserRole(String email, Connection connection) throws SQLException {
		
		int idutilisateur = UserTools.getIdUtilisateur(email, connection);
		
		String select = "SELECT  idrole FROM role_utilisateur "
				+ "WHERE idutilisateur = ? ";

		List<Integer> roles = new ArrayList<>();
		PreparedStatement preparedStatement = null;
		try{
			preparedStatement=	connection.prepareStatement(select);
			preparedStatement.setLong(1, idutilisateur);

			ResultSet res = preparedStatement.executeQuery();

			while (res.next()) {
				roles.add(res.getInt("idrole"));
			}
			

			return roles;
		}finally {
			if(preparedStatement!=null)
				preparedStatement.close();
		}	
	}
	
	public static boolean checkValidite(String email, Connection connection) throws SQLException {
		String query = "SELECT * FROM utilisateur where (email,validation_tresorier) = (?,?)";

		PreparedStatement preparedStatement =null;
		try{
			preparedStatement=	connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			preparedStatement.setBoolean(2, true);

			ResultSet res=preparedStatement.executeQuery();

			if (res.next()) 
				return true;
			else
				return false;

		}finally {
			if(preparedStatement!=null)
				preparedStatement.close();
		}	
	}
	
	
	public static void deleteUser(String email, Connection connection) throws SQLException {
		
		String update = "DELETE FROM public.utilisateur"
				+ " WHERE email=?";
		
		PreparedStatement preparedStatement =null;
		
		try{
			preparedStatement=	connection.prepareStatement(update);
			
			preparedStatement.setString(1, email);
			
			preparedStatement.executeUpdate();

		}finally {
			if(preparedStatement!=null)
				preparedStatement.close();
			
		}
	}
	
	public static void putValidite(String email, Connection connection) throws SQLException {
		int idutilisateur = UserTools.getIdUtilisateur(email, connection);
		System.out.println(idutilisateur);
		
		
		String update = "UPDATE public.utilisateur"
				+ " SET validation_tresorier=?,validation_date = ?"
				+ " WHERE idutilisateur=?";
		
		PreparedStatement preparedStatement =null;
		try{
			preparedStatement=	connection.prepareStatement(update);
			
			preparedStatement.setBoolean(1, true);
			preparedStatement.setObject(2, java.time.LocalDate.now());
			preparedStatement.setInt(3, idutilisateur);
			
			preparedStatement.executeUpdate();

		}finally {
			if(preparedStatement!=null)
				preparedStatement.close();
			
		}	
	}
	
	public static void main(String []args ) {
		Connection connection = null;
		try {
			try {
				connection = Database.getConnection();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//System.out.println(SessionTools.checkValidite("ahmed_haouili@yahoo.fr", connection));
			//SessionTools.putValidite("hengyi.guo@dauphine.eu", connection);
			//System.out.println(SessionTools.checkValidite("ahmed_haouili@yahoo.fr", connection));
			
			System.out.println(SessionTools.getUserRole("bilal.khaldi@dauphine.eu", connection));
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
	}

	public static String getUserNom(String email, Connection connection) throws SQLException {
		String query = "SELECT * FROM utilisateur where email= ?";

		PreparedStatement preparedStatement =null;
		try{
			preparedStatement=	connection.prepareStatement(query);
			preparedStatement.setString(1, email);

			ResultSet res=preparedStatement.executeQuery();

			if (res.next()) 
				return res.getString("nom");

			return null;
		}finally {
			if(preparedStatement!=null)
				preparedStatement.close();
		}	
	}

	public static String getUserPrenom(String email, Connection connection) throws SQLException {
		String query = "SELECT * FROM utilisateur where email= ?";

		PreparedStatement preparedStatement =null;
		try{
			preparedStatement=	connection.prepareStatement(query);
			preparedStatement.setString(1, email);

			ResultSet res=preparedStatement.executeQuery();

			if (res.next()) 
				return res.getString("prenom");

			return null;
		}finally {
			if(preparedStatement!=null)
				preparedStatement.close();
		}		
	}

}
