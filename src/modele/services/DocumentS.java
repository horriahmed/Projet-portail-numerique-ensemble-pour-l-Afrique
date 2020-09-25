package modele.services;

import java.sql.Connection;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.db.Database;
import modele.tools.DocumentTools;

public class DocumentS {

	public static void createDocument(HttpServletRequest request, HttpServletResponse response) {
		String role = request.getParameter("role");
		String titre = request.getParameter("titre");
		String theme = request.getParameter("theme");
		String contenu = request.getParameter("contenu");
		
		String type = request.getParameter("type");
		
		if((role == null || ( role != null && role.isEmpty()))
				|| (titre == null || ( titre != null && titre.isEmpty()))
				|| (theme == null || ( theme != null && theme.isEmpty()))
				|| (contenu == null || ( contenu != null && contenu.isEmpty()))) {
			System.out.println("missing parameter");
			return;
		}
		
		int idrole = Integer.parseInt(role);
		Connection connection = null;
		try {
			connection = Database.getConnection();
			
			switch(idrole) {
				case 1: // lettre d'information
					Integer type_document = null;
					if(type != null && !type.isEmpty()) {
						type_document = Integer.parseInt(type);
						DocumentTools.addDocument(titre,theme,type_document, contenu, connection);
						request.setAttribute("state", 1);
						return;
					}
					DocumentTools.addDocument(titre,theme,1, contenu, connection);
					break;
				case 2: // document membre du bureau
					DocumentTools.addDocument(titre,theme,2, contenu, connection);
					break;
				case 3: // document conseil d'administration
					DocumentTools.addDocument(titre,theme,3, contenu, connection);
					break; 
			}
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		}catch (SQLException e) {
			request.setAttribute( "state", -1 );
			e.printStackTrace();			
			return ;
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

	public static void getDocuments(HttpServletRequest request, HttpServletResponse response) {
		String role = request.getParameter("role");
	
		if((role == null || ( role != null && role.isEmpty()))) {
			System.out.println("missing parameter");
			return;
		}
		
		
		Connection connection = null;
		try {
			request.setAttribute("role", Integer.parseInt(role));
			connection = Database.getConnection();
			
			List<HashMap<Object, Object>> list = DocumentTools.getDocument(connection);

		
			if(list != null) {
				if(!list.isEmpty())
					request.setAttribute("list", list);
			}
				
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		}catch (SQLException e) {
			request.setAttribute( "state", -1 );
			e.printStackTrace();			
			return ;
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

	public static void deleteDocument(HttpServletRequest request, HttpServletResponse response) {
		String document = request.getParameter("document");
		
		if((document == null || ( document != null && document.isEmpty()))) {
			System.out.println("missing parameter");
			return;
		}
		
		Connection connection = null;
		try {
			connection = Database.getConnection();
			
			DocumentTools.deleteDocument(Integer.parseInt(document), connection);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		}catch (SQLException e) {
			e.printStackTrace();			
			return ;
		} finally {

			if(connection!=null)
				try {
					connection.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		request.setAttribute( "stateDelete", 1 );
		
	}

	public static void archiveDocument(HttpServletRequest request, HttpServletResponse response) {
		String document = request.getParameter("document");
		
		if((document == null || ( document != null && document.isEmpty()))) {
			System.out.println("missing parameter");
			return;
		}
		
		Connection connection = null;
		try {
			connection = Database.getConnection();
			
			DocumentTools.archiveDocument(Integer.parseInt(document), connection);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		}catch (SQLException e) {
			e.printStackTrace();			
			return ;
		} finally {

			if(connection!=null)
				try {
					connection.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		request.setAttribute( "stateArchive", 1 );
		
	}

	public static void listArchive(HttpServletRequest request, HttpServletResponse response) {
		Connection connection = null;
		try {
			connection = Database.getConnection();
			
			List<HashMap<Object, Object>> list = DocumentTools.getArchive(connection);
			if(list != null) {
				if(!list.isEmpty()) {
					request.setAttribute("list", list);
				}
			}
				
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		}catch (SQLException e) {
			request.setAttribute( "state", -1 );
			e.printStackTrace();			
			return ;
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

	public static void deleteArchive(HttpServletRequest request, HttpServletResponse response) {
		String document = request.getParameter("document");
		
		if((document == null || ( document != null && document.isEmpty()))) {
			System.out.println("missing parameter");
			return;
		}
		
		Connection connection = null;
		try {
			connection = Database.getConnection();
			
			DocumentTools.deleteArchive(Integer.parseInt(document), connection);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		}catch (SQLException e) {
			e.printStackTrace();			
			return ;
		} finally {

			if(connection!=null)
				try {
					connection.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		request.setAttribute( "stateDelete", 1 );
	}

	public static void restoreArchive(HttpServletRequest request, HttpServletResponse response) {
		String document = request.getParameter("document");
		
		if((document == null || ( document != null && document.isEmpty()))) {
			System.out.println("missing parameter");
			return;
		}
		
		Connection connection = null;
		try {
			connection = Database.getConnection();
			
			DocumentTools.restoreArchive(Integer.parseInt(document), connection);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		}catch (SQLException e) {
			e.printStackTrace();			
			return ;
		} finally {

			if(connection!=null)
				try {
					connection.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		request.setAttribute( "stateRestoreArchive", 1 );
		
	}

}
