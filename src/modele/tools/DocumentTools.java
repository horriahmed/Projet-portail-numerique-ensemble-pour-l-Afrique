package modele.tools;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import modele.beans.Fichier;
import java.util.List;

public class DocumentTools {

	public static void addDocument(String titre, String theme, int i, String contenu, Connection connection) throws SQLException {

		
	}


	public static void insertDocument(Fichier fichier, String roadtofile, Connection connection) throws SQLException {
		String insert="INSERT INTO public.document(titre,description,type,emplacement,nomfichier)"
				+ " VALUES (?,?,?,?,?)";

		PreparedStatement preparedStatement = null;
		try{
			preparedStatement=	connection.prepareStatement(insert);

			preparedStatement.setString(1, fichier.getTitre());
			preparedStatement.setString(2, fichier.getDescription());
			preparedStatement.setLong(3, fichier.getType());
			preparedStatement.setString(4, roadtofile);
			preparedStatement.setString(5, fichier.getNom());

			preparedStatement.executeUpdate();
		}

		finally {
			if(preparedStatement!=null)
				preparedStatement.close();
		}
		
	}


	public static  List<HashMap<Object,Object>> getDocument(Connection connection) throws SQLException {
		String select = "SELECT * FROM public.document";
		
		PreparedStatement preparedStatement = null;
		try{
			preparedStatement =	connection.prepareStatement(select);
			ResultSet res = preparedStatement.executeQuery();

		
			List<HashMap<Object, Object>> documents = new ArrayList<HashMap<Object,Object>>();
			while (res.next()) {
				
				HashMap<Object,Object> document = new HashMap<Object, Object>();
				
				document.put("titre",res.getString("titre"));
				document.put("nom",res.getString("nomfichier"));
				document.put("description",res.getString("description"));
				document.put("emplacement",res.getString("emplacement"));
				document.put("type",res.getInt("type"));
				document.put("iddocument",res.getInt("iddocument"));
				
				switch(res.getInt("type")) {
					case 1:
						document.put("type_texte","Lettre d'information");
					break;
					case 2:
						document.put("type_texte","Document d'un Membre du Bureau");
					break;
					case 3:
						document.put("type_texte","Document du conseil d'administration");
					break;
				}
				
				documents.add(document);
			}
			return documents;
		}finally {
			if(preparedStatement!=null)
				preparedStatement.close();
		}	
	}

	public static void deleteDocument(Integer idDocument, Connection connection) throws SQLException {
		String insert= "DELETE FROM public.document"
				+ " WHERE iddocument=?";
				

		PreparedStatement preparedStatement = null;
		try{
			preparedStatement =	connection.prepareStatement(insert);
			preparedStatement.setLong(1, idDocument);

			preparedStatement.executeUpdate();
		}

		finally {
			if(preparedStatement!=null)
				preparedStatement.close();
		}
		
	}


	public static void archiveDocument(int idDocument, Connection connection) throws SQLException {
		String select = "SELECT *"
				+ " FROM public.document"
				+ " WHERE iddocument=?";
		PreparedStatement preparedStatement = null;
		try{
			preparedStatement=	connection.prepareStatement(select);
			preparedStatement.setLong(1, idDocument);
			
			ResultSet res = preparedStatement.executeQuery();
			
			String insert ="INSERT INTO public.archive(iddocument,titre,type,description,emplacement,nomfichier,date_archive)"
					+ " VALUES (?,?,?,?,?,?,?)";

			while (res.next()) {
				PreparedStatement preparedStatementInsert = null;
				try{
					preparedStatementInsert =	connection.prepareStatement(insert);

					preparedStatementInsert.setLong(1, res.getLong("iddocument"));
					preparedStatementInsert.setString(2, res.getString("titre"));
					preparedStatementInsert.setLong(3, res.getLong("type"));
					preparedStatementInsert.setString(4, res.getString("description"));
					preparedStatementInsert.setString(5, res.getString("emplacement"));
					preparedStatementInsert.setString(6, res.getString("nomfichier"));
					preparedStatementInsert.setObject(7, java.time.LocalDate.now());
	
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


	public static List<HashMap<Object, Object>> getArchive(Connection connection) throws SQLException {
		String select = "SELECT * FROM public.archive";
		
		PreparedStatement preparedStatement = null;
		try{
			preparedStatement =	connection.prepareStatement(select);
			ResultSet res = preparedStatement.executeQuery();

		
			List<HashMap<Object, Object>> documents = new ArrayList<HashMap<Object,Object>>();
			while (res.next()) {
				
				HashMap<Object,Object> document = new HashMap<Object, Object>();
				
				document.put("titre",res.getString("titre"));
				document.put("nom",res.getString("nomfichier"));
				document.put("description",res.getString("description"));
				document.put("emplacement",res.getString("emplacement"));
				document.put("type",res.getInt("type"));
				document.put("iddocument",res.getInt("iddocument"));
				document.put("date_archivage",res.getObject("date_archive"));
				
				switch(res.getInt("type")) {
					case 1:
						document.put("type_texte","Lettre d'information");
					break;
					case 2:
						document.put("type_texte","Document d'un Membre du Bureau");
					break;
					case 3:
						document.put("type_texte","Document du conseil d'administration");
					break;
				}
				
				documents.add(document);
			}
			return documents;
		}finally {
			if(preparedStatement!=null)
				preparedStatement.close();
		}	
	}


	public static void deleteArchive(int idArchive, Connection connection) throws SQLException {
		String insert= "DELETE FROM public.archive"
				+ " WHERE iddocument=?";
				

		PreparedStatement preparedStatement = null;
		try{
			preparedStatement =	connection.prepareStatement(insert);
			preparedStatement.setLong(1, idArchive);

			preparedStatement.executeUpdate();
		}

		finally {
			if(preparedStatement!=null)
				preparedStatement.close();
		}
		
	}


	public static void restoreArchive(int idDocument, Connection connection) throws SQLException {
		String select = "SELECT *"
				+ " FROM public.archive"
				+ " WHERE iddocument=?";
		PreparedStatement preparedStatement = null;
		try{
			preparedStatement=	connection.prepareStatement(select);
			preparedStatement.setLong(1, idDocument);
			
			ResultSet res = preparedStatement.executeQuery();
			
			String insert ="INSERT INTO public.document(iddocument,titre,type,description,emplacement,nomfichier)"
					+ " VALUES (?,?,?,?,?,?)";

			while (res.next()) {
				PreparedStatement preparedStatementInsert = null;
				try{
					preparedStatementInsert =	connection.prepareStatement(insert);

					preparedStatementInsert.setLong(1, res.getLong("iddocument"));
					preparedStatementInsert.setString(2, res.getString("titre"));
					preparedStatementInsert.setLong(3, res.getLong("type"));
					preparedStatementInsert.setString(4, res.getString("description"));
					preparedStatementInsert.setString(5, res.getString("emplacement"));
					preparedStatementInsert.setString(6, res.getString("nomfichier"));
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
}

