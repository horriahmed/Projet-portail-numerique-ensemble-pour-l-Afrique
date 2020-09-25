package modele.test;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modele.db.Database;
import modele.tools.SessionTools;



public class DataBasetest {
	public static boolean mysql_pooling=false;
	public static String mysql_username="postgres";
	public static String mysql_host="93.22.122.96:5432";
	public static String mysql_password="projetsia";
	public static String mysql_db="all4africa";
	
	

    public static void main(String[] args) {

		// https://docs.oracle.com/javase/8/docs/api/java/sql/package-summary.html#package.description
        // auto java.sql.Driver discovery -- no longer need to load a java.sql.Driver class via Class.forName

        // register JDBC driver, optional, since java 1.6
        /*try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
		
        /*
    	List<List<String>> result = new ArrayList<>();

        String SQL_SELECT = "Select * from Utilisateur";
        
        try (Connection conn =  DriverManager.getConnection(
        		"jdbc:postgresql://"+ mysql_host +"/"+mysql_db, mysql_username, mysql_password)){

            if (conn != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }
            
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT); 
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
            	
            	List<String> users = new ArrayList<>();
            	String eamil = (String)resultSet.getString("email");
            	String pays = (String)resultSet.getString("paysDorigine");
            	String mdp = (String)resultSet.getString("motDePasse");
            	String id = (String)resultSet.getString("idUtilisateur");
            	String nom = (String)resultSet.getString("nom");
            	String prenom = (String)resultSet.getString("prenom");

            	users.add(eamil);
            	users.add(pays);
            	users.add(mdp);
            	users.add(id);
            	users.add(nom);
            	users.add(prenom);
            	
            	
            	result.add(users);
            }
            result.forEach(x -> System.out.println(x));
                
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
         */
    	
    	
    	Connection conn = null;
		try {
			conn = DriverManager.getConnection(
					"jdbc:postgresql://"+ mysql_host +"/"+mysql_db, mysql_username, mysql_password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

            if (conn != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }
    	
        /*try {
			Connection conn = Database.getConnection();
					
			if(!SessionTools.userExists("ahmedx_haouili@yahoo.fr",conn)) {
				System.out.println("User don't exist");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
    }
}
