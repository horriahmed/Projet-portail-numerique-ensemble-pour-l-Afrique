<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="modele.services.ForumS.Utilisateur"%>
<!DOCTYPE html>
<html>
	<head>
	    <meta charset="utf-8">
	    <!--[if IE]><meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'><![endif]-->
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <meta name="description" content="">
	    <meta name="author" content="">
	    <link rel="shortcut icon" href="assets/img/ico/favicon.ico">
	    <link rel="apple-touch-icon" sizes="144x144" href="WebContent/assets/img/ico/apple-touch-icon-144x144.png">
	    <link rel="apple-touch-icon" sizes="114x114" href="WebContent/assets/img/ico/apple-touch-icon-114x114.png">
	    <link rel="apple-touch-icon" sizes="72x72" href="WebContent/assets/img/ico/apple-touch-icon-72x72.png">
	    <link rel="apple-touch-icon" href="WebContent/assets/img/ico/apple-touch-icon-57x57.png">
	
	    <title>Ensemble pour l'Afrique</title>
	
	    <!-- Bootstrap Core CSS -->
	    <link href="assets/css/bootstrap.min.css" rel="stylesheet">
	    <link href="assets/css/animate.css" rel="stylesheet">
	    <link href="assets/css/plugins.css" rel="stylesheet">
	
	    <!-- Custom CSS -->
	    <link href="assets/css/style.css" rel="stylesheet">
	
	    <!-- Custom Fonts -->
	    <link href="assets/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	    <link href="assets/css/pe-icons.css" rel="stylesheet">
	</head>
	
	
	<body id="page-top" class="index">
	
	
		<%
		HttpSession a = request.getSession();
		Utilisateur user = (Utilisateur) a.getAttribute("user");
		List<Integer> roles = null;
		if(user != null){
			roles = user.getRoles();
		}
	
		%>
	
   	 <div class="master-wrapper">
	        <!-- Navigation -->
	        <nav class="navbar navbar-default navbar-fixed-top fadeInDown" data-wow-delay="0.5s">
	
	            <div class="container">
	                <!-- Brand and toggle get grouped for better mobile display -->
	                <div class="navbar-header page-scroll">
	                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#main-navigation">
	                        <span class="sr-only">Toggle navigation</span>
	                        <span class="icon-bar"></span>
	                        <span class="icon-bar"></span>
	                        <span class="icon-bar"></span>
	                    </button>
	                    <a class="navbar-brand smoothie logo logo-light" href="/EnsemblePourAfrique/home"><img src="assets/img/logo.png" alt="logo" height="50"></a>
	                    <a class="navbar-brand smoothie logo logo-dark" href="/EnsemblePourAfrique/home"><img src="assets/img/logo.png" alt="logo" height="50"></a>

	                </div>
	
	                <!-- Collect the nav links, forms, and other content for toggling -->
	                <div class="collapse navbar-collapse" id="main-navigation">
	                    <ul class="nav navbar-nav navbar-right">
	                    
	                    <!-- Lister les documents -->
	                    <%if(user != null){ %>
		                    <% if(roles.contains(2) || roles.contains(8)){%>
		                    	<li class="dropdown">
		                    		<a href="" class="dropdown-toggle" data-toggle="dropdown">Documents<span class="pe-7s-angle-down"></span></a>
		                    	
		                    			<ul class="dropdown-menu">
		                    			
		                    				<li><a href="/EnsemblePourAfrique/listDocument?role=0"> Consulter les Documents disponibles</a></li>
		                    				 <li class="dropdown-submenu">
				                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Gérer les documents</a>
				                                    <ul class="dropdown-menu">
				                                    
				                                    	<!-- Gestion des documents administratifs (delete) -->
				                                    	<% if(roles.contains(3) 
															|| roles.contains(6)
															|| roles.contains(7)){%>
				                                        	<li><a href="/EnsemblePourAfrique/listDocument?role=2">Gérer les documents administratifs</a></li>
				                                        <%} %>
				                                        
				                                        <!-- Gestion des documents administratifs (delete, archive) -->
				                                        <% if(roles.contains(4)){%>
				                                        <li><a href="/EnsemblePourAfrique/listDocument?role=3">Gérer les documents du Conseil d'administration</a></li>
				                                        <%} %>
				                                        
				                                        <!-- Adminsitrateur technique et Présidante (tous les documents) -->
				                                        <% if( roles.contains(5)
															|| roles.contains(8)){%>
				                                        	<li><a href="/EnsemblePourAfrique/listDocument?role=1">Gérer tous documents disponibles</a></li>
				                                        <%} %>
				                                        
				                                        <!-- Gestion des documents administratifs (déposer) -->
				                                      	<% if(roles.contains(3) 
															|| roles.contains(6)
															|| roles.contains(7)){%>
				                                        	<li><a href="/EnsemblePourAfrique/createDocument?role=2">Déposer des documents administratifs</a></li>
				                                        <%} %>
				                                        
				                                        
				                                        
				                                        <!-- Gestion des documents administratifs (déposer) -->
				                                        <% if(roles.contains(4)){%>
				                                        	<li><a href="/EnsemblePourAfrique/createDocument?role=3">Déposer des documents du Conseil d'administration</a></li>
				                                        <%} %>
				                                        
				                                        <!-- Adminsitrateur technique et Présidante  (déposer tout types de documents) -->
				                                        <% if( roles.contains(5)
															|| roles.contains(8)){%>
				                                        	<li><a href="/EnsemblePourAfrique/createDocument?role=1">Déposer un document</a></li>
				                                        	<li><a href="/EnsemblePourAfrique/listArchive">Gérer les archives</a></li>
				                                        <%} %>
				                                        
				                                       <% if(roles.contains(7)){%>
				                                        	<li><a href="/EnsemblePourAfrique/listArchive">Gérer les archives</a></li>
				                                        <%} %>
				                                        
				                                 </ul>
				                            </li>
				                        </ul>
				                   </li>           
		                    	<%}%>
		                   	
		              
		                   	<li class="dropdown">
	                           <a href="" class="dropdown-toggle" data-toggle="dropdown">Réunion<span class="pe-7s-angle-down"></span></a>
		                    			<ul class="dropdown-menu">
		                    				<li><a href="/EnsemblePourAfrique/listReunionUtilisateur"> Consulter les reunions</a></li>
		          								<% if( roles.contains(5)
		          										|| roles.contains(8)){%>
		                    						<li><a href="/EnsemblePourAfrique/listReunion?type_validation=2"> Valider les reunions en attente de validation</a></li>
		                    					<%} %>
		                    					<% if( roles.contains(7)
		                    							|| roles.contains(8)){%>
		                    						<li><a href="/EnsemblePourAfrique/createReunion">Créer une réunion</a></li>
		                    					<%} %>
		                    			</ul>
	                        </li>
	                        
	                      	<li class="dropdown">
	                           <a href="" class="dropdown-toggle" data-toggle="dropdown">Utilisateur<span class="pe-7s-angle-down"></span></a>
		                    			<ul class="dropdown-menu">
		          								<li class="dropdown-submenu">
				                                    <a href="/EnsemblePourAfrique/listUtilisateur">Consulter la liste des utilisateurs</a>
				                                    <ul class="dropdown-menu">
				                                    	<li><a href="/EnsemblePourAfrique/listUtilisateur?role=0">Adhérant de l'association</a></li>
				                                    	<li><a href="/EnsemblePourAfrique/listUtilisateur?role=1">Abonné de l'association</a></li>
				                               			<li><a href="/EnsemblePourAfrique/listUtilisateur?role=2">Membre de l'association</a></li>
				                               			<li><a href="/EnsemblePourAfrique/listUtilisateur?role=3">Membre du bureau l'association</a></li>
				                               			<li><a href="/EnsemblePourAfrique/listUtilisateur?role=4">Membre du conseil d'administration de l'association</a></li>
				                              		 	<li><a href="/EnsemblePourAfrique/listUtilisateur?role=5">Président de l'association</a></li>
				                              		 	<li><a href="/EnsemblePourAfrique/listUtilisateur?role=6">Trésorier de l'association</a></li>
				                               			<li><a href="/EnsemblePourAfrique/listUtilisateur?role=7">Secrétaire du bureau de l'association</a></li>
				                               			<li><a href="/EnsemblePourAfrique/listUtilisateur?role=8">Administrateur technique</a></li>
				                                    </ul>
				                                 </li>
				                              
		          								<% if( roles.contains(7)){%>
		          								<li class="dropdown-submenu">
				                                    <a href="/EnsemblePourAfrique/listUtilisateurSecretaire">Outils de gestion des utilisateurs</a>
				                                    <ul class="dropdown-menu">
				                                    	<li><a href="/EnsemblePourAfrique/listUtilisateurSecretaire?role=0">Adhérant de l'association</a></li>
				                                    	<li><a href="/EnsemblePourAfrique/listUtilisateurSecretaire?role=1">Abonné de l'association</a></li>
				                               			<li><a href="/EnsemblePourAfrique/listUtilisateurSecretaire?role=2">Membre de l'association</a></li>
				                               			<li><a href="/EnsemblePourAfrique/listUtilisateurSecretaire?role=3">Membre du bureau l'association</a></li>
				                               			<li><a href="/EnsemblePourAfrique/listUtilisateurSecretaire?role=4">Membre du conseil d'administration de l'association</a></li>
				                              		 	<li><a href="/EnsemblePourAfrique/listUtilisateurSecretaire?role=5">Président de l'association</a></li>
				                              		 	<li><a href="/EnsemblePourAfrique/listUtilisateurSecretaire?role=6">Trésorier de l'association</a></li>
				                               			<li><a href="/EnsemblePourAfrique/listUtilisateurSecretaire?role=7">Secrétaire du bureau de l'association</a></li>
				                               			<li><a href="/EnsemblePourAfrique/listUtilisateurSecretaire?role=8">Administrateur technique</a></li>
				                                    </ul>
				                                 </li>
		                    						
		                    					<%} %>
		                    					<% if(roles.contains(6)){%>
		                    						<li><a href="/EnsemblePourAfrique/listUtilisateurTresorier?role=0&validation=1">Valider les inscriptions</a></li>
		                    					<%} %>
		                    					<% if(roles.contains(8)){%>
		                    						<li><a href="/EnsemblePourAfrique/listUtilisateurAdministrateur">Gérer les utilisateurs</a></li>
		                    					<%} %>
		                    			</ul>
	                        </li>
	                        
	                        <li class="dropdown">
	                           <a href="" class="dropdown-toggle" data-toggle="dropdown">Activité<span class="pe-7s-angle-down"></span></a>
		                    			<ul class="dropdown-menu">
		                    					<li><a href="/EnsemblePourAfrique/ListActivite">Liste des activités</a></li>
		                    					<li><a href="/EnsemblePourAfrique/listLettreInformation">Consulter la lettre d'information</a></li>
		                    					<li><a href="/EnsemblePourAfrique/formulaire">Accéder aux formulaires associatifs</a></li>
		                    					<% if(roles.contains(8)){%>
		                    						<li><a href="/EnsemblePourAfrique/creerLettreInformation">Rédiger la lettre d'information</a></li>
		                    						<li><a href="/EnsemblePourAfrique/CreerActivite">Créer du contenu</a></li>
		                    					<%} %>
		                    					<% if(roles.contains(5)){%>
		                    						<li><a href="/EnsemblePourAfrique/creerLettreInformation">Rédiger la lettre d'information</a></li>
		                    					<%} %>
		                    			</ul>
	                        </li>
	                        
	                        <%if(roles.contains(6)){ %>
	                        <li class="dropdown">
	                            <a href="https://www.ciel.com/identification-client.aspx">Outil de trésorie</a>
	                        </li>
	                        <%} %>
	                        
							<%} %>
	                      
						
							<%if(user == null){ %>
	                        <li class="dropdown">
	                            <a href="/EnsemblePourAfrique/ListActivite"> Activités</a>
	                        </li>
							<%} %>
							
	                        <li class="dropdown">
	                            <a href="/EnsemblePourAfrique/EspaceEtudiant" >Espace étudiant </a>
	                        </li>
	                       
	                      	<li class="dropdown">
	                            <a href="/EnsemblePourAfrique/HomeForum" >Forum</a>
	                        </li>
	                       
	
							<% if(user == null){ %>
	                        	<li><a href="/EnsemblePourAfrique/login" class="side-menu-trigger hidden-xs">Se connecter</a></li>
	                        <%}else{ %>
	                        	<li><a href="/EnsemblePourAfrique/deconnexion" class="side-menu-trigger hidden-xs">Se deconnecter</a></li>
	                        <%} %>
	                    </ul>
	                </div>
	                <!-- /.navbar-collapse -->
	            </div>
	            <!-- /.container-fluid -->
	        </nav>
	    </div>

	</body>
</html>