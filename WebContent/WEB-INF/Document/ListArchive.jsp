<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste Archives</title>

	<link href="assets/css/bootstrap.min.css" rel="stylesheet">
	    <link href="assets/css/animate.css" rel="stylesheet">
	    <link href="assets/css/plugins.css" rel="stylesheet">
	
	    <!-- Custom CSS -->
	    <link href="assets/css/listArchive.css" rel="stylesheet">
	
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
	<section class="dark-wrapper opaqued parallax" data-parallax="scroll" data-image-src="assets/img/archive.jpg" data-speed="0.7">
            <div class="section-inner pad-top-200">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12 mt30 wow text-center">
                            <h2 class="section-heading">Archive de l'association</h2>
                        </div>
                    </div>
                </div>
            </div>
     </section>
	</div>
	<jsp:include page="/WEB-INF/header.jsp" />
	<br/><br/>
	
	<% if(request.getAttribute("state") != null
		&& request.getAttribute("list") == null  
		&& (Integer)request.getAttribute("state") == 1 ) {%>
			<p class="text-center"> Aucune Archive n'est présente dans la base de données.</p>
	<% } %>
		

	<% if(request.getAttribute("list") != null ){ %>
			<table class="table table-striped">
				<legend>Liste des documents archivés</legend>
				
				<% if(request.getAttribute("state") != null
				&& request.getAttribute("stateDelete") != null
				&& (Integer)request.getAttribute("stateDelete") == 1 ) {%>
					<p class="text-center"> l'archive a été supprimé avec succès de l'ensemble des archives.</p>
				<% } %>
				<% if(request.getAttribute("state") != null
				&& request.getAttribute("stateRestoreArchive") != null
				&& (Integer)request.getAttribute("stateRestoreArchive") == 1 ) {%>
					<p class="text-center"> l'archive a été restauré avec succés.</p>
				<% } %>
					<tr>
				      <th class="text-center">Titre</th>
				      <th class="text-center">Nom</th>
				      <th class="text-center">Type</th>
					  <th class="text-center">Description</th>
					  <th class="text-center">Date d'archivage</th>
				    </tr>
			    <c:forEach items="${list}" var="document">
				    <tr>
						<td><c:out value="${document.titre}" /></td>
						<td><c:out value="${document.nom}" /></td>
						<td><c:out value="${document.type_texte}" /></td>
						<td><c:out value="${document.description}" /></td>
						<td><c:out value="${document.date_archivage}" /></td>
						<td><a href="downloadDocument/${document.nom}"><b><font color="blue">Télécharger l'archive</font></b> </a></td>
						<td><a href="listArchive?document=${document.iddocument}&restore=1"><b><font color="green">Restaurer l'archive dans la Base Documentaire</font></b></a></td>
						<td><a href="listArchive?document=${document.iddocument}&delete=1"><b><font color="red">Supprimer définitivement l'archive</font></b></a></td>
						
				 </c:forEach>
			</table>
		<%} %>
		<center><a href="/EnsemblePourAfrique/home"> <b>Retourner vers l'acceuil  </b> </a></center>
		
				<br/><br/><br/><br/><br/><br/><br/>
		
		<jsp:include page="/WEB-INF/footer.jsp" />
		
</body>
</html>