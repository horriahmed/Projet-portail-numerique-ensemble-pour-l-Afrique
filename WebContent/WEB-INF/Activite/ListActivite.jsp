<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="modele.services.ForumS.Utilisateur"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Liste des Activités</title>
		<link href="assets/css/bootstrap.min.css" rel="stylesheet">
	    <link href="assets/css/animate.css" rel="stylesheet">
	    <link href="assets/css/plugins.css" rel="stylesheet">
	
	    <!-- Custom CSS -->
<!-- 	    <link href="assets/css/listUtilisateurAdministrateur.css" rel="stylesheet"> -->
	
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
	<section class="dark-wrapper opaqued parallax" data-parallax="scroll" data-image-src="assets/img/event.jpg" data-speed="0.7">
            <div class="section-inner pad-top-200">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12 mt30 wow text-center">
                            <h2 class="section-heading">Activités D'Ensemble Pour L'Afrique</h2>
                        </div>
                    </div>
                </div>
            </div>
     </section>
	</div>
	<jsp:include page="/WEB-INF/header.jsp" />
	<br/><br/>
	  	<!-- Si aucune Activte n'est prévue -->
		<% if(request.getAttribute("list") == null 
		&& (Integer)request.getAttribute("state") != null
		&& (Integer)request.getAttribute("state") == 1) {%>
			<p class = "text-center">Il n'y a aucune activité de prévu </p>
			<p class="text-center" ><a href="/EnsemblePourAfrique/home"> <b>Retourner vers l'acceuil </b> </a></p>
		<% }%>
		
		
		<%if(request.getAttribute("list") != null  ){%>
			<p class="lead text-center">Voici les Activites prévues :</p>
			
			<% if(request.getAttribute("state") != null
					&& request.getAttribute("delete") != null
					&& (Integer)request.getAttribute("delete") == 1 ) {%>
						<p class="text-center"> l'activité a été supprimé avec succès de la base documentaire.</p>
			<% } %>
		
		<table class="table table-striped">
					<tr>
				      <th class="text-center">Titre</th>
				      <th class="text-center">Sujet</th>
				      <th class="text-center">Lieu</th>
				      <th class="text-center">Date et horraires de début</th>
				      <th class="text-center">Date et horraires de fin</th>
				    
				    </tr>
			    <c:forEach items="${list}" var="activite">
			    <tr>
					<td class="text-center"><c:out value="${activite.titre}" /></td>
					<td class="text-center"><c:out value="${activite.sujet}" /></td>
					<td class="text-center"><c:out value="${activite.lieu}" /></td>
					<td class="text-center"><c:out value="${activite.date_debut}" /></td>
					<td class="text-center"><c:out value="${activite.date_fin}" /></td>
					<td class="text-center"><a class="stylebouton" href="article?idactivite=${activite.idactivite}"><b>En savoir plus</font></b></a></td> 
					
					<%
					HttpSession a = request.getSession();
					Utilisateur user = (Utilisateur) a.getAttribute("user");
					List<Integer> roles = null;
					if(user != null){
						roles = user.getRoles();
					}
	
					%>
					
					<%if(user != null){ %>
						<%if(roles.contains(8)){ %>
							<td> <a class="stylebouton" href="ListActivite?delete=1&idactivite=${activite.idactivite}"><b><font color="red">Supprimer l'activité</font></b></a></td> 
				  		<%} %>
				  	<%} %>
				  </tr>

				  </c:forEach>
			</table>
			
	
			<%}%>
			<% if(request.getAttribute("state") != null
					&& request.getAttribute("delete") != null
					&& (Integer)request.getAttribute("delete") == 1 ) {%>
						<p class="text-center"> l'activité a été supprimé avec succès de la base documentaire.</p>
			<% } %>
	
		<br/><br/><br/><br/><br/><br/><br/>
		
		<jsp:include page="/WEB-INF/footer.jsp" />
	</body>
</html>