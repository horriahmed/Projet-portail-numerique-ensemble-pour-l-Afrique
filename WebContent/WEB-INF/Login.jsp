<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
	    <title>Login</title>
	    <meta charset="UTF-8">
	    <link href="assets/css/bootstrap.min.css" rel="stylesheet">
	    <link href="assets/css/animate.css" rel="stylesheet">
	    <link href="assets/css/plugins.css" rel="stylesheet">
	
	    <!-- Custom CSS -->
	    <link href="assets/css/login.css" rel="stylesheet">
	
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
                            <h2 class="section-heading">Accéder à votre Environnement</h2>
                        </div>
                    </div>
                </div>
            </div>
     </section>
	</div>
	<jsp:include page="/WEB-INF/header.jsp" />
				<div>
					<!-- Je récupère la valeur de l'état -->
		               <form method="post" action="login">
			                <fieldset>
			               		   <legend>Connexion</legend>			                 
			                     <label>E-mail</label>
			                     <input type="text" id="email" name="email" placeholder="exemple : louis.dupont@gmail.eu" size="100" maxlength="60" required>
			                     <br/>      
			                     <label>Mot de Passe</label>
			                     <input type="password" id="password" name="password" placeholder="mot de passe" size="100" maxlength="60" required>
			                  	<br/>
			                  	<input type="button" class="btn btn-black" onclick="window.location.href = '/EnsemblePourAfrique/signIn';" value="s'inscrire"/>
			                  	<button type="submit" value="login" class="btn btn-black">Se connecter</button>
			                 
			                   <p class=message> Si vous rencontrez des problèmes pour vous connecter,  <a href="/EnsemblePourAfrique/contact"><b>vous pouvez contacter le service technique.</b></a> </p>
			              	
					        <%if((Integer)request.getAttribute("state")!= null  
							&& (Integer)request.getAttribute("state") == -1) {%>
							<p class="message">E-mail ou Mot de passe incorrect.</p>
							<%} %>
							
							<%if((Integer)request.getAttribute("state")!= null 
									&& (Integer)request.getAttribute("state")== -2) { %>
							<p class="message">Vous devez attendre la validation de votre compte !</p>
							<%} %>
			              	</fieldset>
		               </form>
		        </div>       

</body>
</html>