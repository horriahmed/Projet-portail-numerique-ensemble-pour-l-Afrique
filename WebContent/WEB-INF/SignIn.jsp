<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SignIn</title>
	<!-- Bootstrap Core CSS -->
	    <link href="assets/css/bootstrap.min.css" rel="stylesheet">
	    <link href="assets/css/animate.css" rel="stylesheet">
	    <link href="assets/css/plugins.css" rel="stylesheet">
	
	    <!-- Custom CSS -->
	    <link href="assets/css/signIn.css" rel="stylesheet">
	
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
                            <h2 class="section-heading">Inscrivez-vous sur notre Platforme</h2>
                        </div>
                    </div>
                </div>
            </div>
     </section>
	</div>
	<jsp:include page="/WEB-INF/header.jsp" />
	
<!-- Formulaire -->
	<form method="get" action="signIn">
           <fieldset>
                <legend>Veuillez remplir les champs ci-dessous</legend>
                
                <label for="prenom">Prénom<span class="requis">*</span></label>
                <input type="text" id="prenom" name="prenom" value="" size="100" maxlength="100" required/>
                <br />
                
                <label for="nom">Nom<span class="requis">*</span></label>
                <input type="text" id="nom" name="nom" value="" size="100" maxlength="100" required/>
                <br />
                
               	<label for="email">Adresse email <span class="requis">*</span></label>
                <input type="text" id="email" name="email" value="" size="20" maxlength="60" required/>
               	<br />
               	
               	<%if((Integer)request.getAttribute("state")!= null 
					&& (Integer)request.getAttribute("state")== -1) {%>
				<p class ="message">Cette adresse est dèjà utilisée</p>
				<%} %>

                <label for="password">Mot de passe<span class="requis">*</span></label>
                <input type="password" id="password" name="password" value="" size="20" maxlength="20" required/>
                <br />
                
                <label for="adresse">Adresse<span class="requis">*</span></label>
                <input type="text" id="adresse" name="adresse" value="" size="100" maxlength="100" required/>
                <br />
                
                <label for="paysdorigine">Pays d'origine<span class="requis">*</span></label>
                <input type="text" id="paysdorigine" name="paysdorigine" value="" size="100" maxlength="100" required/>
                <br />
  				
  				<label for="centredinterets">Centre d'interêts</label>
                <input type="text" id="centredinterets" name="centredinterets" value="" size="100" maxlength="100" />
                <br />
                
  				<input type="submit" value="Inscription" class="sansLabel" />
  				<br/>
  				<% if(request.getAttribute("state") != null 
					&& (Integer)request.getAttribute("state") == 1) {%>
						<p class ="message"> Votre inscription à bien été prise en compte. Vous allez recevoir un e-mail de confirmation. </p>
				<% }%>
				
				<%if((Integer)request.getAttribute("state")!= null 
					&& (Integer)request.getAttribute("state")== -2) {%>
				<p class ="message">Erreur interne au serveur.</p>
				<%} %>
                <br />
            </fieldset>
    </form>
    
    <br/><br/><br/><br/><br/><br/><br/>
		
	<jsp:include page="/WEB-INF/footer.jsp" />
</body>
</html>