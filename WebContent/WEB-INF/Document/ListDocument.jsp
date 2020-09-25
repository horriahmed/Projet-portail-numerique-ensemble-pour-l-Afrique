<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste Documents</title>
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
	<section class="dark-wrapper opaqued parallax" data-parallax="scroll" data-image-src="assets/img/document.jpg" data-speed="0.7">
            <div class="section-inner pad-top-200">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12 mt30 wow text-center">
                            <h2 class="section-heading">Base Documentaire</h2>
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
			<p class="text-center"> Aucun Document n'est présent dans la base de données.</p>
	<% } %>

		
	<% if(request.getAttribute("list") != null ){ %>
			<table class="table table-striped">
				<legend>Liste des documents présents dans la base de données.</legend>
				<br/> <br/>
				<% if(request.getAttribute("state") != null
					&& request.getAttribute("stateDelete") != null
					&& (Integer)request.getAttribute("stateDelete") == 1 ) {%>
						<p class="text-center"> le document a été supprimé avec succès de la base documentaire.</p>
					<% } %>
					
					<% if(request.getAttribute("state") != null
				&& request.getAttribute("stateArchive") != null
					&& (Integer)request.getAttribute("stateArchive") == 1 ) {%>
						<p class="text-center"> le document a été archivé avec succès, l'administrateur technique et la présidente peuvent accèder aux archives.</p>
					<% } %>
					<tr>
				      <th class="text-center">Titre</th>
					  <th class="text-center">Description</th>
					  <th class="text-center">Type</th>
					  <th class="text-center">Nom</th>
				    </tr>
			    <c:forEach items="${list}" var="document">
				    <tr>
						<td><c:out value="${document.titre}" /></td>
						<td><c:out value="${document.description}" /></td>
						<td><c:out value="${document.type_texte}" /></td>
						<td><c:out value="${document.nom}" /></td>
						<td><a href="downloadDocument/${document.nom}"><b><font color="green">Télécharger le fichier</font></b></td>
						
						
						<c:if test="${role == 1}">
							<c:if test="${ document.type != 1}">
								<td><a href="listDocument?delete=1&role=${role}&document=${document.iddocument}"><b><font color="red">Supprimer le document</font></b></a></td>
								<td><a href="listDocument?archive=1&role=${role}&document=${document.iddocument}"><b>Archiver le document</b></a></td>
							</c:if>
						</c:if>
						<c:if test="${role == document.type}">
							<td><a href="listDocument?delete=1&role=${role}&document=${document.iddocument}"><b><font color="red">Supprimer le document</font></b></a></td>
							
							<c:if test="${role == 3}">
								 <td><a href="listDocument?archive=1&role=${role}&document=${document.iddocument}"><b>Archiver le document</b></a></td>
							</c:if>
							
							<c:if test="${role == 1}">
								 <td><a href="listDocument?archive=1&role=${role}&document=${document.iddocument}"><b>Archiver le document</b></a></td>
							</c:if>
						</c:if>
				     </tr>
				 </c:forEach>
			</table>
		<%} %>
		<center><a href="/EnsemblePourAfrique/home"> <b>Retourner vers l'acceuil  </b> </a></center>
		
	 	
		
	
		
		<br/><br/><br/><br/><br/><br/><br/>
		
		<jsp:include page="/WEB-INF/footer.jsp" />
</body>
</html>