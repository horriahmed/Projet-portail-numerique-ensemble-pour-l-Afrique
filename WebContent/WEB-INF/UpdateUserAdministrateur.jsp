<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modification d'un utilisateur</title>
</head>
	<link href="assets/css/bootstrap.min.css" rel="stylesheet">
	    <link href="assets/css/animate.css" rel="stylesheet">
	    <link href="assets/css/plugins.css" rel="stylesheet">
	
	    <!-- Custom CSS -->
	    <link href="assets/css/updateUserAdmin.css" rel="stylesheet">
	
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

	<form method="get" action="updateUserAdministrateur">
           <fieldset>
                <legend>Modification, vue Administrateur</legend>
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
					<% if(request.getAttribute("addRole") != null
					&& (Integer)request.getAttribute("addRole") == -1){ %>
						<p class="text-center"> Le rôle est déjà attribué à cet utilisateur.</p>
					<%} %>
					<% if(request.getAttribute("addRole") != null
					&& (Integer)request.getAttribute("addRole") == 1){ %>
						<p class="text-center"> le rôle a été ajouté avec succès.</p>
					<%} %>
					<% if(request.getAttribute("deleteRole") != null
					&& (Integer)request.getAttribute("deleteRole") == -1){ %>
						<p class="text-center">Le rôle n'est pas attribué à cet utilisateur pour pouvoir le supprimer.</p>
					<%} %>
					<% if(request.getAttribute("deleteRole") != null
					&& (Integer)request.getAttribute("deleteRole") == 1){ %>
						<p class="text-center"> le rôle a été supprimé avec succès.</p>
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
								  <p><b>Rôles dans l'Association</b></p>
								  <c:forEach items="${personne.roles}" var="role" >
									<p class="title"><c:out value= "${role}" /><br/><p>
								 </c:forEach>
							</div>
							</c:forEach>
							</div>
						<%} %>
						
					<div class="col-sm-6">
             	
             		<br/>
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
	                
	                <label for="role_delete">Supprimer un rôle<span class="requis">*</span></label>
	                <select name="role_delete" id="role_delete">
	               		<option value="">Aucun</option>
			   		 	<option value="0">Adhérant de l'association</option>
			   		 	<option value="1">Abonné au forum</option>
			   		 	<option value="2">Membre de l'association</option>
			   		 	<option value="3">Membre du bureau l'association</option>
			   		 	<option value="4">Membre du conseil d'administration de l'association</option>
			   		 	<option value="5">Présidant de l'association</option>
			   		 	<option value="6">Trésorier de l'association</option>
			   		 	<option value="7">Secrétaire du bureau de l'association</option>
			   		 	<option value="8">Administrateur technique</option>
			  		</select>
			  		
			  		<br />
			  		
			  		<label for="role_add">Ajouter un rôle<span class="requis">*</span></label>
	                <select name="role_add" id="role_add">
	                	<option value="">Aucun</option>
			   		 	<option value="0">Adhérant de l'association</option>
			   		 	<option value="1">Abonné au forum</option>
			   		 	<option value="2">Membre de l'association</option>
			   		 	<option value="3">Membre du bureau l'association</option>
			   		 	<option value="4">Membre du conseil d'administration de l'association</option>
			   		 	<option value="5">Présidant de l'association</option>
			   		 	<option value="6">Trésorier de l'association</option>
			   		 	<option value="7">Secrétaire du bureau de l'association</option>
			   		 	<option value="8">Administrateur technique</option>
			  		</select>
			  		
			  		<br />
			  		<br />
					<br />
					
	
	  			
	  				<input type="submit" value="Modifier utilisateur" class="sansLabel" />
	  				<a class= "stylebouton" href = "listUtilisateurAdministrateur">Retourner vers la liste.</a>
	                <br />

					</div>
					
				</div>
	            </fieldset>
	    </form>
	    	        
	   	<br/><br/><br/><br/><br/><br/><br/>
		
		<jsp:include page="/WEB-INF/footer.jsp" />
	    
</body>
</html>