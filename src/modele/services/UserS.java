package modele.services;

import java.io.File;
import java.sql.Connection;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Parameter;

import modele.db.Database;
import modele.services.ForumS.Utilisateur;
import modele.servlets.Forum.RefreshCategory;
import modele.tools.MailTools;
import modele.tools.SessionTools;
import modele.tools.UserTools;

public class UserS {
	private static Utilisateur userToUpdate = new Utilisateur(null, -1);
	
	// login
	public static void login(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException  {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		if( email == null || password == null ) {
			System.out.println("missing parameter");
			return ;
		}
		
		Connection connection = null;
		try {
			Class.forName("org.postgresql.Driver");

			connection = Database.getConnection();
	
			if(!SessionTools.userExists(email,connection)) {
				request.setAttribute( "state", -1);
				System.out.println("User don't exist");
				return ; 
			}
			if(!SessionTools.checkValidite(email, connection)) {
				request.setAttribute( "state", -2 );
				System.out.println("Not valide");
				return;
			}

			if(!SessionTools.checkPassword(email,password,connection)) {
				request.setAttribute( "state", -1 );
				System.out.println("WP");
				return ;
			}

			
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
		try {
			connection = Database.getConnection();
			int id = UserTools.getIdUtilisateur(email, connection) ; 
			request.setAttribute("id", String.valueOf(id));
			
			
			connection.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute( "state", 1 );
	}
	
	public static List<Integer> getUserRole(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException  {
		String email = request.getParameter("email");
		
		
		Connection connection=null;
		List<Integer> userRole = null;
		try {
			connection = Database.getConnection();
			userRole = SessionTools.getUserRole(email,connection);

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

		return userRole;
	}
	
	// Inscription
	public static void SignIn(HttpServletRequest request, HttpServletResponse response,boolean check) throws ClassNotFoundException  {
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String adresse = request.getParameter("adresse");
		String paysdorigine = request.getParameter("paysdorigine");
		String centredinterets = request.getParameter("centredinterets");
		String path = (String) request.getAttribute("path") ; 
		
		
		// Mail
        String subject = "Création d'un Compte Ensemble Pour l'Afrique.";
		String messageText = "Bonjour Mr/Mme "+nom +" "+ prenom +",\n"
        		+ "Nous vous remercions de l’intérêt que vous nous portez et nous vous confirmons que votre inscription a bien été reçue par l'administration. \n\n" 
        		+ "Dès la réception du reçu de paiement de la cotisation, nous activerons votre compte et vous receverez un mail d'alerte." 
        		+" Ainsi, vous pourrez accéder à tous les contenus du site.\n\n" +
        		"N'hésitez pas à vous abonner au forum.\n\n" + 
        		"Cordialement,\n" + 
        		"L'équipe administrative d'Ensemble Pour l'Afrique.";
		
		String messageForum = "Bonjour Mr/Mme "+nom +" "+ prenom +",\n"
        		+ "Nous vous souhaitons la bienvenue sur notre forum. \n\n"
				+ 
        		"Cordialement,\n" + 
        		"L'équipe administrative d'Ensemble Pour l'Afrique.";
		
		if( nom == null || prenom == null 
				|| email == null || password == null 
				|| paysdorigine  == null ) {
			System.out.println("missing parameter");
			return ;
		}
		
		Connection connection = null;
		try {
			Class.forName("org.postgresql.Driver");
			connection = Database.getConnection();
	
			if(!SessionTools.userExists(email,connection)) {

				UserTools.addUser(nom, prenom, email, password, adresse, paysdorigine, centredinterets, path,connection);

				if (check) {
					UserTools.addRole(email, 1, connection);
					MailTools.sendMail(email,subject,messageForum);

				} else {
					UserTools.addRole(email, 0, connection);
					MailTools.sendMail(email,subject,messageText);
				}
				
			}else {
				request.setAttribute( "state", -1);
				System.out.println("User exists");
				return ; 
			}
			
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			request.setAttribute( "state", -2);
			e.printStackTrace();
			
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
	
	// put validity
	public static void putValidity(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException  {
		String idutilisateur = request.getParameter("idutilisateur");
		
		// Mail
        String subject = "Activation de votre compte Ensemble Pour l'Afrique.";
		String messageText = "Bonjour et Félicitations,\n"
        		+ "Nous vous informons que votre compte vient d'être activé pour une année de cotisation. \n\n" 
        		+ "Il vous suffira ensuite de vous connecter avec votre identifiant et votre mot de passe pour accéder au site.\n\n" 
        		+ "Gardez cet e-mail, il vous sera utile si vous oubliez vos identifiant et mot de passe.\n\n" + 
        		"Cordialement\n" + 
        		"L'équipe administrative d'Ensemble Pour l'Afrique.";
		
		if( idutilisateur == null  ) {
			System.out.println("missing parameter");
			return ;
		}
		
		
		Connection connection = null;
		try {
			connection = Database.getConnection();

			Integer iduser = Integer.parseInt(idutilisateur);
			String email = UserTools.getUserbyEmail(iduser, connection);
			if(email != null) {
					SessionTools.putValidite(email, connection);
					MailTools.sendMail(email, subject, messageText);
			}
		}  catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

		request.setAttribute( "stateValidity", 1 );
	}

	public static void radiateUser(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException {
		String idutilisateur = request.getParameter("idutilisateur");
		
		// Mail
        String subject = null;
        String messageText = null;
		
		
		if( idutilisateur == null  ) {
			System.out.println("missing parameter");
			return ;
		}
		
		Connection connection = null;
		try {
			connection = Database.getConnection();
	
			Integer iduser = Integer.parseInt(idutilisateur);
			String email = UserTools.getUserbyEmail(iduser, connection);
			if(email != null) {
				if(SessionTools.userExists(email,connection)) {
					
					
					if(SessionTools.checkValidite(email, connection)) {
						subject = "Suppression de votre compte Ensemble Pour l'Afrique.";
						messageText = "Bonjour,\n "
				        		+ "Nous vous informons que votre compte Ensemble pour l'Afrique vient d'être supprimé." 
								+ " Pour plus d'informations vous pouvez contacter la secrétaire de l'association. \n" 
				        		+ "Vous pouvez toutefois accéder aux forums dans lesquels vous êtes abonné. \n\n" 
				        		+ "Au plaisir de vous revoir.\n\n" + 
				        		"Cordialement\n" + 
				        		"L'équipe administrative d'Ensemble Pour l'Afrique.";
						MailTools.sendMail(email, subject, messageText);
					}else {
						subject = "Non activation de votre compte Ensemble Pour l'Afrique.";
						messageText = "Bonjour,\n"
				        		+ "Nous vous informons que votre inscription au site internet Ensemble pour l'Afrique vient d'être refusé par l'administation." 
								+ " Pour plus d'informations vous pouvez contacter la secrétaire de l'association. \n" 
				        		+ "Vous pouvez toutefois accéder aux forums dans lesquels vous êtes abonné. \n\n" 
				        		+ "Au plaisir de vous revoir.\n\n" + 
				        		"Cordialement\n" + 
				        		"L'équipe administrative d'Ensemble Pour l'Afrique.";
						MailTools.sendMail(email, subject, messageText);
					}
					SessionTools.deleteUser(email, connection);
				}else {
					request.setAttribute( "state", -1);
					System.out.println("User don't exists");
					return ; 
				}
			}
			
		} catch (AddressException e) {
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

		request.setAttribute( "stateRadiation", 1 );
	}

	public static void userContact(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException {
		String email = request.getParameter("email");
		String subject = null;
		String messageText = null;
		if( email == null  ) {
			System.out.println("missing parameter");
			return ;
		}
		
		Connection connection = null;
		try {
			connection = Database.getConnection();
			
			if(SessionTools.userExists(email,connection)) { // Cas ou l'utilisateur existe
				if(SessionTools.checkValidite(email, connection)) { // Cas ou il existe et qu'il est valide
					request.setAttribute( "state", -1);
					System.out.println("User valide");
					subject = "Acivation d'un nouveau mot de passe.";
					messageText = "Bonjour,\n "
				        		+ "Vous recevez cet e-mail car vous (ou quelqu’un se faisant passer pour vous)" 
				        		+ " avez demandé qu’un nouveau mot de passe vous soit envoyé pour" 
				        		+ " votre compte sur « Ensemble Pour l'afrique ». \n\n" 
				        		+ "Si vous n’avez pas demandé cette modification, veuillez alors ignorer cet e-mail.\n\n" 
				        		+ "Pour utiliser le nouveau mot de passe, vous avez besoin de l’activer."
				        		+ " Veuillez cliquer sur le lien ci-dessous :\n"
				        		+ "http://localhost:8080/EnsemblePourAfrique/home \n\n" + 
				        		"Cordialement\n" + 
				        		"L'équipe administrative d'Ensemble Pour l'Afrique.";
					MailTools.sendMail(email, subject, messageText);
					return ; 
				}else {
					subject = "Compte rendu de votre activité sur Ensemble Pour l'Afrique";
					messageText = "Bonjour,\n " 
							+ "Nous vous confirmons que votre inscription a bien été reçue par l'administration. \n\n" 
			        		+ "Toutefois, sans la réception du reçu de paiement de la cotisation, votre compte restera inactif."
			        		+ " Nous nous efforçons à réduire les délais de traitement.\n"
			        		+ "Merci pour votre comprehension.\n\n"
			        		+ "N'hésitez pas à vous abonner au forum.\n\n" 
			        		+ "Cordialement\n" 
			        		+ "L'équipe administrative d'Ensemble Pour l'Afrique.";
					MailTools.sendMail(email, subject, messageText);
					
				}
			}else {
				request.setAttribute( "state", -2);
				subject = "Compte rendu de votre activité sur Ensemble Pour l'Afrique";
				messageText = "Bonjour,\n "
			        		+ "Vous recevez cette e-mail car vous avez établi un contact sur le site Ensemble Pour l'Afrique.\n" 
			        		+ "Malheuresement, votre adresse e-mail ne figure pas parmi celles enregistrées."
			        		+ " Si vous voulez accèder aux fonctionnnalités du site vous devez créer un compte "
			        		+ "en cliquant sur le lien ci-dessous :\n"
			        		+ "http://localhost:8080/EnsemblePourAfrique/signIn \n\n"
			        		+ "Cordialement\n" 
			        		+ "L'équipe administrative d'Ensemble Pour l'Afrique.";
				System.out.println("User n'est pas présent dans la base"); // Cas ou l'utilisateur ne s'est pas inscrit
				MailTools.sendMail(email, subject, messageText);
				return ; 
			}
					 
		
		} catch (MessagingException e) {
			e.printStackTrace();
			
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
		request.setAttribute( "state", 1 ); // Cas ou le mail est envoyé au trésorier 
		
	}

	public static void listUser(HttpServletRequest request, HttpServletResponse response) {
		String role = request.getParameter("role");
		String valid = request.getParameter("validation");
		System.out.println(valid);
		if( role == null || role.isEmpty()) {
			System.out.println("missing parameter");
			return ;
		}
		
		Connection connection = null;
		try {
			connection = Database.getConnection();
			List<HashMap<Object,Object>> list = null;
			
			int idrole = Integer.parseInt(role);
			// Secretaire et tout le monde 
			if(role != null ) { 
				if(valid == null) {
					list = UserTools.listUserbyRole(idrole, connection);  // mettre la validation à vrai
					if(!list.isEmpty())
						request.setAttribute("list", list);
				}
				if(valid != null && valid.isEmpty()) {
					list = UserTools.listUserbyRole(idrole, connection);  // mettre la validation à vrai
					if(!list.isEmpty())
						request.setAttribute("list", list);
				}

			}
			
			if(valid != null && !valid.isEmpty()) {	// trésorière
				Boolean validation = null;
				if(Integer.parseInt(valid) == 0){
					validation = true;
				}else {
					validation = false;
				}
				list = UserTools.listUserbyValidation(idrole, validation, connection);
				if(!list.isEmpty())
					request.setAttribute("list", list);
			}

			
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

		request.setAttribute( "state", 1 );
	}

	public static void relanceUser(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException {
		String idutilisateur = request.getParameter("idutilisateur");
        
		if( idutilisateur == null  ) {
			System.out.println("missing parameter");
			return ;
		}
		
		
		Connection connection = null;
		try {
			connection = Database.getConnection();
			UserTools.sendMailRelance(idutilisateur, connection);
			
		}  catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

		request.setAttribute( "relance", 1 );
	}

	public static void updateUser(HttpServletRequest request, HttpServletResponse response) {
		String idutil = request.getParameter("idutilisateur");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String adresse = request.getParameter("adresse");
		String paysdorigine = request.getParameter("paysdorigine");
		String email = request.getParameter("email");
		String role_delete = request.getParameter("role_delete");
		String role_add = request.getParameter("role_add");
				
	
		Connection connection = null;
		try {
			connection = Database.getConnection();
			
			if( (idutil == null || (idutil != null && idutil.isEmpty()))
					&& (request.getParameter("time") != null)) {
				System.out.println("missing parameter");
				if(connection!=null)
					try {
						connection.close();

					} catch (SQLException e) {
						e.printStackTrace();
					}
				return;
			}else {
				
				if(idutil != null && idutil.isEmpty()
						|| (request.getParameter("time") != null)) {
					userToUpdate = new Utilisateur(null, -1);
				}
				if(userToUpdate.getMail() == null ) {
						userToUpdate = new Utilisateur(UserTools.getUserbyEmail(Integer.parseInt(idutil),connection), Integer.parseInt(idutil));
						
				}
				
			}
			
			List<HashMap<Object,Object>> list = null;
			
			Integer idutilisateur  = null;
			idutilisateur = userToUpdate.getId();
			
			
			if(nom != null && !nom.isEmpty()) {
				UserTools.updateUserName(idutilisateur, nom, connection);
				request.setAttribute("updateName", 1);
				
			}
			if(prenom != null && !prenom.isEmpty()) {
				UserTools.updateUserSurName(idutilisateur, prenom, connection);
				request.setAttribute("updatePrenom", 1);
				
			}
			
			if(adresse != null && !adresse.isEmpty()) {
				UserTools.updateUserAdresse(idutilisateur, adresse, connection);
				request.setAttribute("updateAdresse", 1);
				
			}
			
			if(paysdorigine != null && !paysdorigine.isEmpty()) {
				UserTools.updateUserContry(idutilisateur, paysdorigine, connection);
				request.setAttribute("updateContry", 1);
				
			}
			
			if(email != null && !email.isEmpty()) {
				UserTools.updateUserEmail(idutilisateur, email, connection);
				request.setAttribute("updateEmail", 1);
				
			}
			if(role_add != null && !role_add.isEmpty()) {
				String email_Role = UserTools.getUserbyEmail(idutilisateur, connection);
				List<Integer> roles = SessionTools.getUserRole(email_Role, connection);
				Integer roleToAdd = Integer.parseInt(role_add);
				
				if(roles.contains(roleToAdd)) {
					request.setAttribute( "addRole", -1 );
					
				}else {
					UserTools.addRole(email_Role, roleToAdd, connection);
					request.setAttribute( "addRole", 1 );
					
				}
				
			}
			if(role_delete !=null && !role_delete.isEmpty()) {
				String email_Role = UserTools.getUserbyEmail(idutilisateur, connection);
				List<Integer> roles =SessionTools.getUserRole(email_Role, connection);
				
				Integer roleToDelete = Integer.parseInt(role_delete);
				
				if(!roles.contains(roleToDelete)) {
					request.setAttribute( "deleteRole", -1 );
					
				}else {
					UserTools.deleteRole(idutilisateur, roleToDelete, connection);
					request.setAttribute( "deleteRole", 1 );
					
				}
				
			}
			list = UserTools.getUserInformations(idutilisateur, connection);
			request.setAttribute("list", list);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
		request.setAttribute( "stateUpdate", 1 );
	}

	public static String getNom(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException {
		String email = request.getParameter("email");
		
		String nom = null;
		Connection connection=null;
		try {
			connection = Database.getConnection();
			nom = SessionTools.getUserNom(email,connection);

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

		return nom;
	}

	public static String getPrenom(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException {
		String email = request.getParameter("email");
		
		
		Connection connection=null;
		String prenom = null;
		try {
			connection = Database.getConnection();
			prenom = SessionTools.getUserPrenom(email,connection);

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

		return prenom;
	}
		
}
