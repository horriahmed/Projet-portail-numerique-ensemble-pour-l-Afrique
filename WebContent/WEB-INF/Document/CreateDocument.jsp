<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
        <meta charset="utf-8" />
        <title>Envoi de fichier</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/form.css"/>" />
        <!-- Bootstrap Core CSS -->
	    <link href="assets/css/bootstrap.min.css" rel="stylesheet">
	    <link href="assets/css/animate.css" rel="stylesheet">
	    <link href="assets/css/plugins.css" rel="stylesheet">
	
	    <!-- Custom CSS -->
	    <link href="assets/css/createDocument.css" rel="stylesheet">
	
	    <!-- Custom Fonts -->
	    <link href="assets/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	    <link href="assets/css/pe-icons.css" rel="stylesheet">
    </head>
    <body>
	<div id="menu">	 
		<section class="dark-wrapper opaqued parallax" data-parallax="scroll" data-image-src="assets/img/event.jpg" data-speed="0.7">
	            <div class="section-inner pad-top-200">
	                <div class="container">
	                    <div class="row">
	                        <div class="col-lg-12 mt30 wow text-center">
	                            <h2 class="section-heading">Déposer un Document </h2>
	                        </div>
	                    </div>
	                </div>
	            </div>
	     </section>
		</div>
		<jsp:include page="/WEB-INF/header.jsp" />
		<br/><br/>
        <form action="<c:url value="/createDocument" />" method="post" enctype="multipart/form-data">
            <fieldset>
                <legend>Envoi de fichier</legend>
				
				
				<c:if test="${role == 1}">
		            <label for="type"> Type du contenu
		               	 <br />2- Document des membres du Bureau
		               	 <br />3- Document des membres du CA
		            <span class="requis">*</span></label>
			        <input type="text" id="type" name="type" value="<c:out value="${fichier.type}"/>" required/>
	
	                <br/>
                </c:if>
                
                <label for="titre">Titre</label>
                <input type="text" id="titre" name="titre" value="<c:out value="${fichier.titre}"/>" required/>
                <br />
                
                <label for="description">Description du fichier</label>
                <input type="text" id="description" name="description" value="<c:out value="${fichier.description}"/>" required/>
                <span class="erreur">${form.erreurs['description']}</span>
                <br />
                

                <label for="fichier">Emplacement du fichier <span class="requis">*</span></label>
                <input type="file" id="fichier" name="fichier" value="<c:out value="${fichier.nom}"/>" required/>
                <br/>
                
               	<a href="/EnsemblePourAfrique/home"><b>Annuler</b></a>
                <input type="submit" value="Envoyer" class="sansLabel" />
                <br />               
                
                <span class="erreur">${form.erreurs['fichier']}</span> 
                <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>        
            </fieldset>
        </form>
         <%if(request.getAttribute("SQL") != null
                && (Integer)request.getAttribute("SQL") == -1){ %>
                	<p>Ce document existe dèjà dans la base de données.</p>
         <%} %>
         <br />
           
         
         
    <br/><br/><br/><br/><br/><br/><br/>
		
	<jsp:include page="/WEB-INF/footer.jsp" />   
    </body>
</html>