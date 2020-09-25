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
	<section class="dark-wrapper opaqued parallax" data-parallax="scroll" data-image-src="assets/img/lettre.jpg" data-speed="0.7">
            <div class="section-inner pad-top-200">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12 mt30 wow text-center">
                            <h2 class="section-heading">Lettre De La Présidente</h2>
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
			<p class = "text-center">Il n'y a aucune lettre d'informaiton publiée</p>
			<p class="text-center" ><a href="/EnsemblePourAfrique/home"> <b>Retourner vers l'acceuil</b> </a></p>
		<% }%>
		
		
		<%if(request.getAttribute("list") != null  ){%>
			<p class="lead text-center">Voici les lettres d'information selon l'ordre de parution :</p>
			
		
			<table class="table table-striped">
					<tr>
				      <th class="text-center">Titre</th>
				      <th class="text-center">Sujet</th>
				      <th class="text-center">Auteur</th>
				      <th class="text-center">Date de publication</th>
				    
				    </tr>
			    <c:forEach items="${list}" var="lettre">
			    <tr>
					<td class="text-center"><c:out value="${lettre.titre}" /></td>
					<td class="text-center"><c:out value="${lettre.sujet}" /></td>
					<td class="text-center">La présidente de l'association</td>
					<td class="text-center"><c:out value="${lettre.date}" /></td>
					<td class="text-center"><a class="stylebouton" href="lettre?idactivite=${lettre.idactivite}"><b>Lire la lettre</font></b></a></td> 

				  </tr>

				  </c:forEach>
			</table>
			
	
			<%}%>
	
		<br/><br/><br/><br/><br/><br/><br/>
		
		<jsp:include page="/WEB-INF/footer.jsp" />
	</body>
</html>