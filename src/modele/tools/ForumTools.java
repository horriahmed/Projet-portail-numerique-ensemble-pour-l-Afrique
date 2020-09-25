package modele.tools;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;

import modele.db.Database;
import modele.services.ForumS.Commentaire;
import modele.services.ForumS.Sujet;
import modele.services.ForumS.Theme;

public class ForumTools {

	public static ArrayList<Theme> listTheme(Connection connection, int id) throws SQLException {

		String select = "SELECT * FROM public.themeforum u LEFT JOIN public.utilisateur_themeforum v on u.idtheme=v.idtheme and v.idutilisateur = ? ;  ";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(select);
			preparedStatement.setLong(1, id);

			ResultSet res = preparedStatement.executeQuery();
			ArrayList<Theme> retour = new ArrayList<Theme>();
			while (res.next()) {
				boolean isActive = res.getInt("idutilisateur") == id;
				Theme monTheme = new Theme(res.getInt("idtheme"), res.getString("nomtheme"), isActive,
						res.getString("image_link"));
				retour.add(monTheme);
			}
			return retour;
		} finally {
			if (preparedStatement != null)
				preparedStatement.close();
		}

	}

	public static void unsubscribeTheme(int idUser, int idTheme, Connection connection) throws SQLException {
		String select = "select * from  public.utilisateur_themeforum where (idutilisateur,idtheme) = (?,?);";
		String insert = "insert into public.utilisateur_themeforum (idutilisateur,idtheme) VALUES (?,?) ; ";
		String delete = "delete from public.utilisateur_themeforum where idutilisateur = ? and idtheme = ? ;";

		PreparedStatement preparedStatement = null;

		preparedStatement = connection.prepareStatement(select);
		preparedStatement.setLong(1, idUser);
		preparedStatement.setLong(2, idTheme);
		ResultSet res = preparedStatement.executeQuery();

		if (!res.next()) {
			preparedStatement = connection.prepareStatement(insert);
			preparedStatement.setLong(1, idUser);
			preparedStatement.setLong(2, idTheme);
			preparedStatement.executeUpdate();

		} else {
			preparedStatement = connection.prepareStatement(delete);
			preparedStatement.setLong(1, idUser);
			preparedStatement.setLong(2, idTheme);
			preparedStatement.executeUpdate();

		}
		if (preparedStatement != null)
			preparedStatement.close();

	}

	public static ArrayList<Sujet> listSujet(int idTheme, Connection connection) throws SQLException {
		String select = "SELECT * FROM public.sujet NATURAL JOIN themeforum where idtheme= ? order by date DESC ;  ";
		PreparedStatement preparedStatement = null;
		String select2 = "select count(*) from commentaire natural join sujet where idsujet=? and idtheme=?; ";
		PreparedStatement preparedStatement2 = null;

		try {
			preparedStatement = connection.prepareStatement(select);
			preparedStatement.setLong(1, idTheme);

			ResultSet res = preparedStatement.executeQuery();
			ArrayList<Sujet> retour = new ArrayList<Sujet>();
			while (res.next()) {
				Sujet monSujet = new Sujet();
				monSujet.setContenu(res.getString("contenu"));
				monSujet.setLink(res.getString("link"));
				monSujet.setM_idSujet(res.getInt("idsujet"));
				monSujet.setM_idTheme(res.getInt("idtheme"));
				preparedStatement2 = connection.prepareStatement(select2);

				preparedStatement2.setLong(1, monSujet.getM_idSujet());
				preparedStatement2.setLong(2, monSujet.getM_idTheme());
				ResultSet res2 = preparedStatement2.executeQuery();
				res2.next();
				monSujet.setNbCommentaire(res2.getInt(1));
				monSujet.setM_nomSujet(res.getString("nomsujet"));
				monSujet.setNbLike(res.getInt("like"));
				monSujet.setNomTheme(res.getString("nomtheme"));
				monSujet.setMaDate(res.getTimestamp("date"));
				monSujet.setLinkTheme(res.getString("image_link"));
				retour.add(monSujet);
			}
			return retour;
		} finally {
			if (preparedStatement != null)
				preparedStatement.close();
		}

	}

	public static ArrayList<Commentaire> listCommentaire(int idSujet, int idTheme, Connection connection)
			throws SQLException {
		String select = "select commentaire,datee,pseudo,avatar,idcommentaire from public.commentaire inner join public.utilisateur on idauteur=idutilisateur where idsujet=? and idtheme=? order by datee ; ";
		String select2 = "select * from public.commentaire_fichier where idcommentaire = ? ;  ";
		PreparedStatement preparedStatement2 = null;

		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(select);
			preparedStatement.setLong(2, idSujet);
			preparedStatement.setLong(1, idTheme);

			ResultSet res = preparedStatement.executeQuery();
			ArrayList<Commentaire> retour = new ArrayList<Commentaire>();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			while (res.next()) {

				Commentaire monCommentaire = new Commentaire(res.getString("pseudo"), res.getString("commentaire"),
						res.getTimestamp("datee"));
				monCommentaire.setImage(res.getString("avatar"));
				preparedStatement2 = connection.prepareStatement(select2);
				preparedStatement2.setLong(1, res.getLong("idcommentaire"));

				ResultSet res2 = preparedStatement2.executeQuery();
				ArrayList<String> joint = new ArrayList<String>();
				while (res2.next()) {
					joint.add(res2.getString("nom_fichier"));
				}
				monCommentaire.setFichier(joint);
				retour.add(monCommentaire);
			}
			return retour;
		} finally {
			if (preparedStatement != null)
				preparedStatement.close();
		}

	}

	public static void insertNewComments(int idAuteur, int idSujet, String commentaire, int idtheme,
			Connection connection, ArrayList<String> pcs) throws SQLException {
		String request = "insert into public.commentaire  VALUES (DEFAULT,?,?,?,?,?) ; ";
		String insert = "insert into public.commentaire_fichier (idcommentaire,nom_fichier,idfichier) values ((SELECT MAX(idcommentaire) FROM public.commentaire),?,DEFAULT) ; ";
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement2 = null;

		try {
			preparedStatement = connection.prepareStatement(request);
			preparedStatement.setLong(1, idAuteur);
			preparedStatement.setLong(5, idSujet);
			preparedStatement.setString(3, commentaire);
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());

			preparedStatement.setTimestamp(4, timestamp);
			preparedStatement.setLong(2, idtheme);
			preparedStatement.executeUpdate();
			for (String e : pcs) {
				preparedStatement2 = connection.prepareStatement(insert);
				preparedStatement2.setString(1, e);
				preparedStatement2.executeUpdate();
			}

		} finally {
			if (preparedStatement != null)
				preparedStatement.close();
		}
	}

	public static void insertTheme(String link, String nom, Connection connection) throws SQLException {
		String request = "insert into public.themeforum (nomtheme,image_link)  VALUES (?,?) ; ";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(request);
			preparedStatement.setString(1, nom);
			preparedStatement.setString(2, link);
			ResultSet res = preparedStatement.executeQuery();
		} finally {
			if (preparedStatement != null)
				preparedStatement.close();
		}

	}

	public static void deleteTheme(int id, Connection connection) throws SQLException {
		String request = "delete from public.themeforum where idtheme= ? ; ";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(request);
			preparedStatement.setLong(1, id);
			preparedStatement.executeUpdate();
		} finally {
			if (preparedStatement != null)
				preparedStatement.close();
		}

	}

	
	public static void deleteSujet(int idSujet , int idTheme , Connection connection) throws SQLException {
		String request = "delete from sujet where idsujet=? and idtheme=? ;  ";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(request);
			preparedStatement.setLong(1, idSujet);
			preparedStatement.setLong(2, idTheme);

			preparedStatement.executeUpdate() ; 
		} finally {
			if (preparedStatement != null)
				preparedStatement.close();
		}
		
	}
	
	
	
	public static void setBanner(int idSujet , int idTheme , Connection connection) throws SQLException {
		String request = "delete from public.frontsujet  ;  ";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(request);
			preparedStatement.executeUpdate() ; 
			request = "insert into public.frontsujet values (?,?) ;" ;
			preparedStatement = null;
			preparedStatement = connection.prepareStatement(request);
			preparedStatement.setLong(1, idSujet);
			preparedStatement.setLong(2, idTheme);
			preparedStatement.executeUpdate() ; 

		} finally {
			if (preparedStatement != null)
				preparedStatement.close();
		}
		
		
	}
	
	
	
	
	public static Sujet getBanner(Connection co) throws SQLException {
		String select = "select * from public.frontsujet natural join public.sujet natural join public.themeforum ; ";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = co.prepareStatement(select);

			ResultSet res = preparedStatement.executeQuery();
			while (res.next()) {
				Sujet monSujet = new Sujet();
				monSujet.setContenu(res.getString("contenu"));
				monSujet.setLink(res.getString("link"));
				monSujet.setM_idSujet(res.getInt("idsujet"));
				monSujet.setM_idTheme(res.getInt("idtheme"));
				monSujet.setM_nomSujet(res.getString("nomsujet"));
				monSujet.setNbLike(res.getInt("like"));
				monSujet.setLinkTheme(res.getString("image_link"));

				monSujet.setNomTheme(res.getString("nomtheme"));
				monSujet.setMaDate(res.getTimestamp("date"));

				return monSujet;
			}

		} finally {
			if (preparedStatement != null)
				preparedStatement.close();
		}
		return null;

	}

	public static ArrayList<Sujet> listSujetById(Connection connection, int id) throws SQLException {
		String select = "SELECT * FROM public.sujet u NATURAL JOIN public.utilisateur_themeforum v natural join themeforum z where v.idutilisateur = ? order by date DESC ;";
		String select2 = "select count(*) from commentaire natural join sujet where idsujet=? and idtheme=?; ";
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement2 = null;

		try {
			preparedStatement = connection.prepareStatement(select);
			preparedStatement.setLong(1, id);

			ResultSet res = preparedStatement.executeQuery();
			ArrayList<Sujet> retour = new ArrayList<Sujet>();
			while (res.next()) {
				Sujet monSujet = new Sujet();
				monSujet.setContenu(res.getString("contenu"));
				monSujet.setLink(res.getString("link"));
				monSujet.setM_idSujet(res.getInt("idsujet"));
				monSujet.setM_idTheme(res.getInt("idtheme"));
				preparedStatement2 = connection.prepareStatement(select2);

				preparedStatement2.setLong(1, monSujet.getM_idSujet());
				preparedStatement2.setLong(2, monSujet.getM_idTheme());
				ResultSet res2 = preparedStatement2.executeQuery();
				res2.next();
				monSujet.setNbCommentaire(res2.getInt(1));
				monSujet.setM_nomSujet(res.getString("nomsujet"));
				monSujet.setNbLike(res.getInt("like"));
				monSujet.setNomTheme(res.getString("nomtheme"));
				monSujet.setMaDate(res.getTimestamp("date"));
				monSujet.setLinkTheme(res.getString("image_link"));

				retour.add(monSujet);
			}
			return retour;
		} finally {
			if (preparedStatement != null)
				preparedStatement.close();
		}

	}

	public static ArrayList<Theme> listThemeOnlyUser(Connection connection, int id) throws SQLException {

		String select = "SELECT * FROM public.themeforum u LEFT JOIN public.utilisateur_themeforum v on u.idtheme=v.idtheme and v.idutilisateur = ? ;  ";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(select);
			preparedStatement.setLong(1, id);

			ResultSet res = preparedStatement.executeQuery();
			ArrayList<Theme> retour = new ArrayList<Theme>();
			while (res.next()) {
				boolean isActive = res.getInt("idutilisateur") == id;
				System.out.println(isActive);
				if (isActive) {
					Theme monTheme = new Theme(res.getInt("idtheme"), res.getString("nomtheme"), isActive,
							res.getString("image_link"));
					retour.add(monTheme);

				}

			}
			return retour;
		} finally {
			if (preparedStatement != null)
				preparedStatement.close();
		}

	}

	public static Sujet getSujet(Connection connection, int idSujet, int idTheme) throws SQLException {
		String select = "select * from public.sujet natural join public.themeforum where idsujet=? and idtheme=? order by date DESC; ";
		String select2 = "select count(*) from commentaire natural join sujet where idsujet=? and idtheme=?; ";
		PreparedStatement preparedStatement2 = null;

		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(select);

			preparedStatement.setLong(1, idSujet);
			preparedStatement.setLong(2, idTheme);

			ResultSet res = preparedStatement.executeQuery();
			while (res.next()) {
				Sujet monSujet = new Sujet();
				monSujet.setContenu(res.getString("contenu"));
				monSujet.setLink(res.getString("link"));
				monSujet.setM_idSujet(res.getInt("idsujet"));
				monSujet.setM_idTheme(res.getInt("idtheme"));
				preparedStatement2 = connection.prepareStatement(select2);
				preparedStatement2.setLong(1, monSujet.getM_idSujet());
				preparedStatement2.setLong(2, monSujet.getM_idTheme());
				ResultSet res2 = preparedStatement2.executeQuery();
				res2.next();
				monSujet.setNbCommentaire(res2.getInt(1));

				monSujet.setM_nomSujet(res.getString("nomsujet"));
				monSujet.setNbLike(res.getInt("like"));
				monSujet.setNomTheme(res.getString("nomtheme"));
				monSujet.setMaDate(res.getTimestamp("date"));
				monSujet.setLinkTheme(res.getString("image_link"));

				return monSujet;
			}

		} finally {
			if (preparedStatement != null)
				preparedStatement.close();
		}
		return null;
	}

	public static void updateForumProfile(Connection connex, String file, String pseudo, int id) throws SQLException {
		String select = "UPDATE utilisateur SET pseudo = ? WHERE idutilisateur= ? ;  ";
		String select2 = "UPDATE utilisateur SET avatar = ? WHERE idutilisateur= ? ; ";
		PreparedStatement preparedStatement2 = null;
		PreparedStatement preparedStatement = null;
		preparedStatement = connex.prepareStatement(select);
		preparedStatement2 = connex.prepareStatement(select2);
		preparedStatement.setString(1, pseudo);
		preparedStatement.setLong(2, id);
		preparedStatement2.setString(1, file);
		preparedStatement2.setLong(2, id);
		if (!(file == null)) {
			preparedStatement2.executeUpdate();

		}
		if (!(pseudo.isBlank())) {
			System.out.println(pseudo);

			preparedStatement.executeUpdate();

		}

	}

	public static void insertSujet(String link, String nom, String contenu, int id,Connection connection) throws SQLException {
		String request = "insert into public.sujet (link,nomsujet,idtheme,contenu,date)  VALUES (?,?,?,?,?) ; ";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(request);
			preparedStatement.setString(1, link);
			preparedStatement.setString(2, nom);
			preparedStatement.setLong(3, id);
			preparedStatement.setString(4,contenu);
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());

			preparedStatement.setTimestamp(5, timestamp);

			

			preparedStatement.executeQuery();
		} finally {
			if (preparedStatement != null)
				preparedStatement.close();
		}

	}

	public static void main(String[] arg) throws ClassNotFoundException, SQLException {
		Connection maco = Database.getConnection();
		ForumTools.listTheme(maco, 80);
		ForumTools.listCommentaire(2, 2, maco);

	}

}
