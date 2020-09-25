<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Liste Utilisateurs Administrateur</title>

	<link href="assets/css/bootstrap.min.css" rel="stylesheet">
	    <link href="assets/css/animate.css" rel="stylesheet">
	    <link href="assets/css/plugins.css" rel="stylesheet">
	
	    <!-- Custom CSS -->
	    <link href="assets/css/listUtilisateurAdministrateur.css" rel="stylesheet">
	
	    <!-- Custom Fonts -->
	    <link href="assets/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	    <link href="assets/css/pe-icons.css" rel="stylesheet">
	    <script>
	    	function confirmDelete(){
	    		var r = confirm("Vous êtes sur le point de supprimer un utilisateur ?\n Confirmez votre choix");
	    		return r
	    	}
	    </script>
	    <script src="assets/js/jquery.js"></script>
    	<script src="assets/js/bootstrap.min.js"></script>
    	<script src="assets/js/plugins.js"></script>
    	<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
    	
</head>
<body>

	<div id="menu">	 
	<section class="dark-wrapper opaqued parallax" data-parallax="scroll" data-image-src="assets/img/forum.jpg" data-speed="0.7">
            <div class="section-inner pad-top-200">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12 mt30 wow text-center">
                            <h2 class="section-heading">Outil de Gestion des utilisateurs</h2>
                        </div>
                    </div>
                </div>
            </div>
     </section>
	</div>
	<jsp:include page="/WEB-INF/header.jsp" />
	

			<form method="get" action="listUtilisateurAdministrateur">
				<legend>Liste des utilisateurs, vue Administrateur</legend>
				<p> Recherche par filtre </p>
				<select name="role" id="role">
					<option value="">Aucun</option>
		   		 	<option value="0">Adhérant de l'association</option>
		   		 	<option value="1">Abonné au forum</option>
		   		 	<option value="2">Membre de l'association</option>
		   		 	<option value="3">Membre du bureau l'association</option>
		   		 	<option value="4">Membre du conseil d'administration de l'association</option>
		   		 	<option value="5">Président de l'association</option>
		   		 	<option value="6">Trésorier de l'association</option>
		   		 	<option value="7">Secrétaire du bureau de l'association</option>
		   		 	<option value="8">Administrateur technique</option>
		  		</select>
		  		
  				<select name="validation" id="validation">
  					<option value="">Aucun</option>
		   		 	<option value="0">Utilisateur validé</option>
		   		 	<option value="1">Utilisateur non validé</option>
		  		</select>
	
		  		<button type="submit" value="listUtilisateurAdministrateur" class="btn btn-black">Valider</button>
	  		</form>
	  		<br/><br/>
	  		
  		<!-- Affichage avec paramètres -->
		<% if(request.getAttribute("list") != null ){ %>
			<table class="table table-striped">
					<tr>
				      <th class="text-center">Nom</th>
				      <th class="text-center">Prénom</th>
				      <th class="text-center">E-mail</th>
				      <th class="text-center">Adresse</th>
				      <th class="text-center">Pays</th>
				      <th class="text-center">Fonction</th>
				      <%System.out.println(request.getParameter("validation") != null); System.out.println(request.getParameter("validation").isEmpty());System.out.println(true && false); %>
				      <%if(request.getParameter("validation") == null 
				      || (request.getParameter("validation") != null &&!request.getParameter("validation").isEmpty())){%>
				      	<% if(Integer.parseInt(request.getParameter("validation")) == 0){ %>
				      	<th class="text-center">Date du dernier paiement</th>
				      <%} %>
				     <%} %>
				    </tr>
			    <c:forEach items="${list}" var="personne">
			    <tr>
					<td><c:out value="${personne.nom}" /></td>
					<td><c:out value="${personne.prenom}" /></td>
					<td><c:out value="${personne.email}" /></td>
					<td><c:out value="${personne.adresse}" /></td>
					<td><c:out value="${personne.paysdorigine}" /></td>
					<td><c:out value="${personne.role}" /></td>
					<%if(request.getParameter("validation") == null 
				      || (request.getParameter("validation") != null &&!request.getParameter("validation").isEmpty())){%>
				      	<% if(Integer.parseInt(request.getParameter("validation")) == 0){ %>
				      	<td><c:out value="${personne.validation_date}" /></td>
				      <%} %>
				     <%} %>
					<!-- Utilisateur non validé -->
					<c:if test="${personne.validation =='0'}" >
						<td> <a class="stylebouton" href="listUtilisateurAdministrateur?validate=1&idutilisateur=${personne.idutilisateur}"><b><font color="green">Valider l'inscription</font></b></a></td>
						<td> <a class="stylebouton" href="listUtilisateurAdministrateur?delete=1&idutilisateur=${personne.idutilisateur}"><b><font color="red">Supprimer l'inscription</font></b></a></td>
					</c:if>
					
					<!-- Utilisateur validé -->
					<c:if test="${personne.validation !='0'}" >
						<td> <a id="modifBouton" class="stylebouton" href="updateUserAdministrateur?time=1&idutilisateur=${personne.idutilisateur}"><b><font color="blue">Modifier l'utilisateur</font></b></a></td>
						<td> <a id="relanceBouton" class="stylebouton" href="listUtilisateurAdministrateur?relance=1&idutilisateur=${personne.idutilisateur}"><b>Relancer l'utilisateur</b></a></td>
						<td> <a id="supprimeBouton" class="stylebouton" href="listUtilisateurAdministrateur?delete=1&idutilisateur=${personne.idutilisateur}"><b><font color="red">Supprimer l'utilisateur</font></b></a></td>
					</c:if>
			     </tr>
				 </c:forEach>
			</table>
		<%} %>
		
		<!-- Pas de résultat de requête  -->
		<% if(request.getAttribute("state") != null
		&& request.getAttribute("list") == null
		&& (Integer)request.getAttribute("state") == 1 ) {%>
			<p> <center>Aucun élement n'est présent dans la base de données.</center></p>
			<p><center><a class= "stylebouton" href = "listUtilisateurAdmnistrateur">Retourner vers la liste</a></center></p>
		<% } %>
		
		<!-- Résultat du delete -->
		<% if(request.getAttribute("stateRadiation") != null
		&& (Integer)request.getAttribute("stateRadiation") == 1){ %>
			<p><center> L'utilisateur a été supprimé avec succès.</center></p>
			<p><center><a class= "stylebouton" href = "listUtilisateurAdmnistrateur">Retourner vers la liste.</a></center></p>
		<%} %>
  		
  		<!--  Résultat du validate  -->
  		<% if(request.getAttribute("stateValidity") != null
		&& (Integer)request.getAttribute("stateValidity") == 1){ %>
			<p><center>L'utilisateur a été validé avec succès, il a reçu un mail d'activation.</center></p>
			<p><center><a class= "stylebouton" href = "listUtilisateurAdministrateur">Retourner vers la liste.</a></center></p>
		<%} %>
		
		<!--  Résultat de l'update -->
		<% if(request.getAttribute("update") != null
		&& (Integer)request.getAttribute("update") == 1){ %>
			<p><center> L'utilisateur a été modifié avec succès.</center></p>
			<p><center><a class= "stylebouton" href = "listUtilisateurAdmnistrateur">Retourner vers la liste.</a></center></p>
		<%} %>
  		
  		<!-- Résultat de la relance -->
  		<% if(request.getAttribute("relance") != null
		&& (Integer)request.getAttribute("relance") == 1){ %>
			<p> <center>L'utilisateur a été relancé avec succès, il va recevoir un mail. </center></p>
			<p><center><a class= "stylebouton" href = "listUtilisateurAdministrateur">Retourner vers la liste.</a></center></p>
		<%} %>
		
		<br/><br/><br/><br/><br/><br/><br/>
		
		<jsp:include page="/WEB-INF/footer.jsp" />
</body>
</html>