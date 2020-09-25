<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Liste des Utilisateurs Trésorier</title>
	<link href="assets/css/bootstrap.min.css" rel="stylesheet">
	    <link href="assets/css/animate.css" rel="stylesheet">
	    <link href="assets/css/plugins.css" rel="stylesheet">
	
	    <!-- Custom CSS -->
	    <link href="assets/css/listUtilisateurTresorier.css" rel="stylesheet">
	
	    <!-- Custom Fonts -->
	    <link href="assets/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	    <link href="assets/css/pe-icons.css" rel="stylesheet">
	    
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
		
			
			<form method="get" action="listUtilisateurTresorier">
				 <legend>Liste des utilisateurs, vue Trésorier</legend>
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
		  		<button type="submit" value="listUtilisateurTresorier" class="btn btn-black">Validez</button>
	  		</form>
	  		
	  		<br/><br/>

				
				<!-- Résultat du delete -->
				<% if(request.getAttribute("stateRadiation") != null
				&& (Integer)request.getAttribute("stateRadiation") == 1){ %>
					<p><center>L'utilisateur a été supprimé de la base de données avec succès.</center></p>
				<%} %>
		  		
		  		<!-- Résultat de validate -->
		  		<% if(request.getAttribute("stateValidity") != null
				&& (Integer)request.getAttribute("stateValidity") == 1){ %>
					<p> <center>L'utilisateur a été validé avec succès, il va recevoir un mail. </center></p>
				<%} %>
				<!-- Pas de résultat de requête  -->
				<% if(request.getAttribute("state") != null
					&& request.getAttribute("list") == null
					&& (Integer)request.getAttribute("state") == 1 ) {%>
						<p> <center>Aucun élement n'est présent dans la base de données.</center></p>
				<% } %>
		
  		<!-- Affichage avec paramètres -->
		<% if(request.getAttribute("list") != null ){ %>

			<table class="table table-striped">
					<tr>
				      <th class="text-center">Nom</th>
				      <th class="text-center">Prenom</th>
				      <th class="text-center">email</th>
				      <th class="text-center">Adresse</th>
				      <th class="text-center">Pays</th>
				      <th class="text-center">Fonction</th>
				      
				      <c:if test="${validation == '0'}" >
				      	<th class="text-center">Date d'inscription</th>
				      </c:if>
				    </tr>
			    <c:forEach items="${list}" var="personne">
			    <tr>
					<td><c:out value="${personne.nom}" /></td>
					<td><c:out value="${personne.prenom}" /></td>
					<td><c:out value="${personne.email}" /></td>
					<td><c:out value="${personne.adresse}" /></td>
					<td><c:out value="${personne.paysdorigine}" /></td>
					<td><c:out value="${personne.role}" /></td>
					
					<c:if test="${personne.validation =='0'}" >
						<td> Inscription <c:out value="${personne.inscription_date}" /></td>
						<td> <a class="stylebouton" href="listUtilisateurTresorier?role=0&validation=1&validate=1&idutilisateur=${personne.idutilisateur}"><b><font color="green">Valider l'inscription</font></b></a></td>
						<td> <a class="stylebouton" href="listUtilisateurTresorier?role=0&validation=1&delete=1&idutilisateur=${personne.idutilisateur}"><b><font color="red">Supprimer l'inscription</font></b></a></td>
					</c:if>
					
			    	
			     </tr>
				 </c:forEach>
			</table>
		<%} %>
		

		<br/><br/><br/><br/><br/><br/><br/>
		<jsp:include page="/WEB-INF/footer.jsp" />
</body>
</html>