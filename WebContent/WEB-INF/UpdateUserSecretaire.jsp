<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modification d'un utilisateur</title>
	<link href="assets/css/bootstrap.min.css" rel="stylesheet">
	    <link href="assets/css/animate.css" rel="stylesheet">
	    <link href="assets/css/plugins.css" rel="stylesheet">
	
	    <!-- Custom CSS -->
	    <link href="assets/css/updateUserSecretaire.css" rel="stylesheet">
	
	    <!-- Custom Fonts -->
	    <link href="assets/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	    <link href="assets/css/pe-icons.css" rel="stylesheet">
	    
	   <style>
	    .card {
			  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
			  max-width: 300px;
			  margin: auto;
			  text-align: center;
			}
	    </style>
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
		
<!-- Update -->

	<form method="get" action="updateUserSecretaire">
           <fieldset>          

				<legend>Modification, vue Secrétaire</legend>
				
					<% if(request.getAttribute("updatePrenom") != null
					&& (Integer)request.getAttribute("updatePrenom") == 1){ %>
						<p class="text-center"> Le prénom a été modifié avec succès.</p>
					<%} %>
					<% if(request.getAttribute("updateName") != null
					&& (Integer)request.getAttribute("updateName") == 1){ %>
						<p class="text-center"> Le nom a été modifié avec succès.</p>
					<%} %>
					 <% if(request.getAttribute("updateEmail") != null
					&& (Integer)request.getAttribute("updateEmail") == 1){ %>
						<p class="text-center"> L'Email a été modifié avec succès.</p>
					<%} %>
					 <% if(request.getAttribute("updateAdresse") != null
					&& (Integer)request.getAttribute("updateAdresse") == 1){ %>
						<p class="text-center"> L'adresse a été modifié avec succès.</p>
					<%} %>
					 <% if(request.getAttribute("updateContry") != null
					&& (Integer)request.getAttribute("updateContry") == 1){ %>
						<p class="text-center"> Le pays d'origine a été modifié avec succès.</p>
					<%} %>
					
					<!-- Affichage avec paramètres -->
					<% if(request.getAttribute("list") != null ){ %>
					
					<div class="row">
  						<div class="col-sm-6">
						<c:forEach items="${list}" var="personne">
							 <div class="card">
								  <h1><c:out value="${personne.nom}" /> <c:out value="${personne.prenom}" /></h1>
								  <p class="title"><c:out value="${personne.email}" /></p>
								  <p class="title"> <c:out value="${personne.adresse}" /></p>
								  <p class="title"> <c:out value="${personne.paysdorigine}" /></p>
								  <p class="title">Date du dernier paiement : <c:out value="${personne.validation_date}" />  </p>
								  <br>
								  
							</div>
						</c:forEach>
						</div>
					<%} %>

					<div class="col-sm-6">
		                <label for="nom">Nom<span class="requis">*</span></label>
		                <input type="text" id="nom" name="nom" value="" size="100" maxlength="100" />
		                <br />
		                	                
		                <label for="prenom">Prénom<span class="requis">*</span></label>
		                <input type="text" id="prenom" name="prenom" value="" size="100" maxlength="100" />
						
		                <br />
		                
		               	<label for="email">Adresse email <span class="requis">*</span></label>
		                <input type="text" id="email" name="email" value="" size="20" maxlength="60" />
		               
		               	<br />
		
		                <label for="adresse">Adresse<span class="requis">*</span></label>
		                <input type="text" id="adresse" name="adresse" value="" size="100" maxlength="100" />
			            
		                <br />
		                
		                <label for="paysdorigine">Pays d'origine<span class="requis">*</span></label>
		                <input type="text" id="paysdorigine" name="paysdorigine" value="" size="100" maxlength="100" />
			           
		                <br />
						<br />
	  					<input type="submit" value="Modifier utilisateur" class="sansLabel" />
	  					<a class= "stylebouton" href = "listUtilisateurSecretaire">Retourner vers la liste.</a>
	                	<br />
					</div>
				</div>
            </fieldset>
    </form>
    
	   	<br/><br/><br/><br/><br/><br/><br/>
		
		<jsp:include page="/WEB-INF/footer.jsp" />
</body>
</html>