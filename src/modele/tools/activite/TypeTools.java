package modele.tools.activite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TypeTools {
	
	public static void insertType(String type,Connection connection)throws SQLException {
		
		
		
		String insertlien="INSERT INTO public.type_activite(type)"
				+ " VALUES (?)";
		
		PreparedStatement preparedStatement = null;
		try{
			preparedStatement=	connection.prepareStatement(insertlien);

			preparedStatement.setString(1, type);
			
			preparedStatement.executeUpdate();
		}

		finally {
			if(preparedStatement!=null)
				preparedStatement.close();
		}
	
	}
	
public static Integer getIdType(String type, Connection connection) throws SQLException {
		
		
		String select = "SELECT idtype FROM public.type_activite WHERE (type) = (?)";
		PreparedStatement preparedStatement = null;
		try{
			preparedStatement=	connection.prepareStatement(select);
			preparedStatement.setString(1, type);
			
			ResultSet res = preparedStatement.executeQuery();

			if (res.next()) 
				return res.getInt("idtype");
			else 
				return -1;

		}finally {
			if(preparedStatement!=null)
				preparedStatement.close();
		}

	}


}