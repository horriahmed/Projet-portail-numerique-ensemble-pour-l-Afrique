package modele.tools.activite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import modele.classe.activite.Lien;

public class lienTools {
	
	public static void insertLien(Lien lien,Connection connection)throws SQLException {
		String titre=lien.getTitre();
		String url=lien.getUrl();
		
		
		String insertlien="INSERT INTO public.lien(titre,url)"
				+ " VALUES (?,?)";
		
		PreparedStatement preparedStatement = null;
		try{
			preparedStatement=	connection.prepareStatement(insertlien);

			
			preparedStatement.setString(1, titre);
			preparedStatement.setString(2, url);
	
			preparedStatement.executeUpdate();
		}

		finally {
			if(preparedStatement!=null)
				preparedStatement.close();
		}
	
	}
	
	public static Integer getIdLien(Lien lien, Connection connection) throws SQLException {
		
		String titre=lien.getTitre();
		String url=lien.getUrl();
		String select = "SELECT idlien FROM public.lien WHERE (titre,url) = (?,?)";
		PreparedStatement preparedStatement = null;
		try{
			preparedStatement=	connection.prepareStatement(select);
			preparedStatement.setString(1, titre);
			preparedStatement.setString(2, url);
			
			ResultSet res = preparedStatement.executeQuery();

			if (res.next()) 
				return res.getInt("idlien");
			else 
				return -1;

		}finally {
			if(preparedStatement!=null)
				preparedStatement.close();
		}

	}

}