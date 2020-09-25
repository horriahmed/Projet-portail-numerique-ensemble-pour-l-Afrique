<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste Utilisateurs</title>

<link href="assets/css/bootstrap.min.css" rel="stylesheet">
	    <link href="assets/css/animate.css" rel="stylesheet">
	    <link href="assets/css/plugins.css" rel="stylesheet">
	
	    <!-- Custom CSS -->
	    <link href="assets/css/listUtilisateur.css" rel="stylesheet">
	
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
                            <h2 class="section-heading">Liste des utilisateurs</h2>
                        </div>
                    </div>
                </div>
            </div>
     </section>
	</div>
	<jsp:include page="/WEB-INF/header.jsp" />
	
	<form method="get" action="listUtilisateur">
				<select name="role" id="role">
					<option value="">Aucun</option>
		   		 	<option value="0">Adhérent de l'association</option>
		   		 	<option value="1">Abonné au forum</option>
		   		 	<option value="2">Membre de l'association</option>
		   		 	<option value="3">Membre du bureau l'association</option>
		   		 	<option value="4">Membre du conseil d'administration de l'association</option>
		   		 	<option value="5">Président de l'association</option>
		   		 	<option value="6">Trésorier de l'association</option>
		   		 	<option value="7">Secrétaire du bureau de l'association</option>
		   		 	<option value="8">Administrateur technique</option>
		  		</select>
		  		 		
		  		<button type="submit" value="listUtilisateur" class="btn btn-black">Valider</button>
	  	</form>
	  	
	  	<br/>
	  	<% if(request.getAttribute("list") != null ){ %>
			<table class="table table-striped">
					<tr>
				      <th class="text-center">Nom</th>
				      <th class="text-center">Prénom</th>
				      <th class="text-center">E-mail</th>
				      <th class="text-center">Adresse</th>
				      <th class="text-center">Pays</th>
				      <th class="text-center">Fonction</th>
				    </tr>
			    <c:forEach items="${list}" var="personne">
				    <tr>
						<td><c:out value="${personne.nom}" /></td>
						<td><c:out value="${personne.prenom}" /></td>
						<td><c:out value="${personne.email}" /></td>
						<td><c:out value="${personne.adresse}" /></td>
						<td><c:out value="${personne.paysdorigine}" /></td>
						<td><c:out value="${personne.role}" /></td>
				     </tr>
				 </c:forEach>
			</table>
		<%} %>
		
		<% if(request.getAttribute("state") != null
		&& request.getAttribute("list") == null
		&& (Integer)request.getAttribute("state") == 1 ) {%>
			<p> <center>Aucun élement n'est présent dans la base de données.</center></p>
			<p><center><a class= "stylebouton" href = "listUtilisateur">Retourner vers la liste</a></center></p>
		<% } %>
		<br/><br/><br/><br/><br/><br/><br/>
		
		<jsp:include page="/WEB-INF/footer.jsp" />

		
</body>
</html>