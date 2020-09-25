<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste des Réunions</title>
 <link href="assets/css/bootstrap.min.css" rel="stylesheet">
	    <link href="assets/css/animate.css" rel="stylesheet">
	    <link href="assets/css/plugins.css" rel="stylesheet">
	
	    <!-- Custom CSS -->
	    <link href="assets/css/listReunion.css" rel="stylesheet">
	
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
	<section class="dark-wrapper opaqued parallax" data-parallax="scroll" data-image-src="assets/img/reunion.jpg" data-speed="0.7">
            <div class="section-inner pad-top-200">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12 mt30 wow text-center">
                            <h2 class="section-heading">Outil de Gestions des Réunions</h2>
                        </div>
                    </div>
                </div>
            </div>
     </section>
	</div>
	<jsp:include page="/WEB-INF/header.jsp" />
	
		<!-- Affichage sans paramètres -->
		<form method="get" action="listReunion">
			<select name="type_validation" id="validation">
	   		 	<option value="1">Réunion prévues</option>
	   		 	<option value="2">Réunion en attente de validation</option>
	  		</select>
	  		<button type="submit" value="listReunion" class="btn btn-black">Valider</button>
  		</form>
		
		<!-- Résultat suppression Réunion -->
		<%if((Integer)request.getAttribute("delete") != null){
				if( (Integer)request.getAttribute("delete") == 1){%>
				<p class="text-center" >La réunion a été supprimé avec succès.</p>
				<%}else{%>
				<p class="text-center">Il y'a eu un problème lors de la suppression.</p>
				<%} %>
		<%} %>
		
		<!-- Résultat suppression Votant -->
		<%if((Integer)request.getAttribute("deleteVotant") != null){
				if( (Integer)request.getAttribute("deleteVotant") == 1){%>
				<p class="text-center">Le droit de vote a été retiré avec succès.</p>
				<%}else{%>
				<p class="text-center">Il y'a eu un problème lors de la suppression.</p>
				<%} %>
		<%} %>
		
		<!-- Résultat Validation Réunion -->
		<%if((Integer)request.getAttribute("validate") != null){
				if( (Integer)request.getAttribute("validate") == 1){%>
				<p class="text-center">La réunion a été validé avec succès, vous pouvez la retrouver dans l'onglet réunion validée. </p>
				<%}else{%>
				<p class="text-center">Il y'a eu un problème lors de la validation, Veuillez reessayer.</p>
				<%} %>
		<%} %>
		
		<!-- Si aucune réunion n'est prévue ou en attente de validation. -->
		<% if(request.getAttribute("list") == null 
		&& (Integer)request.getAttribute("state") != null
		&& (Integer)request.getAttribute("state") == 1) {%>
			<p class="text-center">Il n'y a aucune réunion de prévu </p>
		<% }%>
		
		<% if(request.getAttribute("listPersonne") != null ){ %>
		
		
			<p class="text-center"> Voici les utilisateurs, vous trouverez la liste des Réunions à la fin du tableau ci-dessous : </p>
			<table class="table table-striped">
					<tr>
						<th class="text-center">Titre de la Réunion</th>
				      <th class="text-center">Nom</th>
				      <th class="text-center">Prenom</th>
				      <th class="text-center">email</th>
				      <th class="text-center">Adresse</th>
				      <th class="text-center">Pays</th>
				    </tr>
			    <c:forEach items="${listPersonne}" var="personne">
			    <tr>
			    	<td><c:out value="${personne.titre}" /></td>
					<td><c:out value="${personne.nom}" /></td>
					<td><c:out value="${personne.prenom}" /></td>
					<td><c:out value="${personne.email}" /></td>
					<td><c:out value="${personne.adresse}" /></td>
					<td><c:out value="${personne.paysdorigine}" /></td>
					
					<c:if test="${personne.typereunion == 3}">
						<c:if test="${personne.type_liste == 2}">
							<td> <a class="stylebouton" href="listReunion?deletePersonne=1&type_validation=2&personne=2&idutilisateur=${personne.idutilisateur}&idReunion=${personne.idreunion}"><b><font color="red">Enlever le droit de vote</font></b></a></td>
			     		</c:if>
			     	</c:if>
			     </tr>
				 </c:forEach>
			</table>
		<%} %>
		
		<!-- Affichage avec paramètres -->
		<%if(request.getAttribute("list") != null  ){%>
		<%if((boolean)request.getAttribute("validation") == false  ){%>
			<p class="text-center">Voici ci-dessous les réunions en attente de validation :</p>
		<%}else{ %>
			<p class="text-center">Voici ci-dessous les réunions prévues :</p>
		<%}%>
		<table class="table table-striped">
					<tr>
				      <th class="text-center">Titre</th>
				      <th class="text-center">Type de réunion </th>
				      <th class="text-center">Sujet</th>
				      <th class="text-center">Date et horraires de début</th>
				      <th class="text-center">Date et horraires de fin</th>
				      <th class="text-center">Adresse</th>
				      <th class="text-center">Description</th>
				    </tr>
			    <c:forEach items="${list}" var="reunion">
			    <tr>
					<td><c:out value="${reunion.titre}" /></td>
					<td><c:out value="${reunion.type}" /></td>
					<td><c:out value="${reunion.sujet}" /></td>
					<td><c:out value="${reunion.date_debut}" /></td>
					<td><c:out value="${reunion.date_fin}" /></td>
					<td><c:out value="${reunion.lieu}" /></td>
					<td><c:out value="${reunion.description}" /></td>
				    <%if((boolean)request.getAttribute("validation") == false  ){%>
				    <!-- Boutton validé qui est dans la partie non validé, il faut faire une alerte pour dire que la réunion sera validé et que des mails vont être envoyé à tout le monde -->
						<form method="get" action="listReunion">
							<td> <a class="stylebouton" href="listReunion?validate=1&type_validation=2&idReunion=${reunion.idReunion}"><b><font color="green">Valider la réunion</font></b></a></td>	
							<td> <a class="stylebouton" href="listReunion?personne=1&type_validation=2&idReunion=${reunion.idReunion}"><b><font color="blue">Voir la liste des participants</font></b></a></td>	
							 <c:if test="${reunion.type == 'Assemblée générale'}">
								 <td> <a class="stylebouton" href="listReunion?personne=2&type_validation=2&idReunion=${reunion.idReunion}"><b><font color="black">Voir la liste des votants</font></b></a></td>	
							</c:if>
					     </form>
			       <%} %>
			       <!-- Boutton supprimé qui est dans la partie validé et non validé, il faut faire une alerte pour dire que la réunion sera supprimé -->
			       <%if((boolean)request.getAttribute("validation") == false  ){%>
			      	 	<td> <a class="stylebouton" href="listReunion?delete=1&type_validation=2&idReunion=${reunion.idReunion}"><b><font color="red">Supprimer la réunion</font></b></a></td>
					<%}else{ %>		
						<td> <a class="stylebouton" href="listReunion?delete=1&type_validation=1&idReunion=${reunion.idReunion}"><b><font color="red">Supprimer la réunion</font></b></a></td>
					<%} %>		  
				  </tr>

				  </c:forEach>
			</table>
			
		<%}%>
		
		
		
		<br/><br/><br/><br/><br/><br/><br/>
		
		<jsp:include page="/WEB-INF/footer.jsp" />
</body>
</html>