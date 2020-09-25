<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Créer Reunion</title>
	 <title>Login</title>
	    <meta charset="UTF-8">
	    <link href="assets/css/bootstrap.min.css" rel="stylesheet">
	    <link href="assets/css/animate.css" rel="stylesheet">
	    <link href="assets/css/plugins.css" rel="stylesheet">
	
	    <!-- Custom CSS -->
	    <link href="assets/css/creerReunion.css" rel="stylesheet">
	
	    <!-- Custom Fonts -->
	    <link href="assets/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	    <link href="assets/css/pe-icons.css" rel="stylesheet">
	   
</head>
<body>
	<div id="menu">	 
		<section class="dark-wrapper opaqued parallax" data-parallax="scroll" data-image-src="assets/img/reunion.jpg" data-speed="0.7">
            <div class="section-inner pad-top-200">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12 mt30 wow text-center">
                            <h2 class="section-heading">Création d'une rénuion</h2>
                        </div>
                    </div>
                </div>
            </div>
     </section>
	</div>
	<jsp:include page="/WEB-INF/header.jsp" />
	
<!-- Formulaire -->
	<form  class="form-row" method="get" action="createReunion">
           <fieldset>
                <legend>Veuillez remplir les champs ci-dessous</legend>
                <br/>
                <%if((Integer)request.getAttribute("state")!= null 
					&& (Integer)request.getAttribute("state")== -1) {%>
					<p class ="message">Erreur date : La date de fin est avant celle de début.</p>
				<%} %>
				<%if((Integer)request.getAttribute("state")!= null 
					&& (Integer)request.getAttribute("state")== -2) {%>
					<p class ="message">Il y a déjà une réunion prévue lors de ces horaires.</p>
					
				<%} %>
				<%if((Integer)request.getAttribute("state") != null 
				&& (Integer)request.getAttribute("state")== 1) {%>
					<p class ="message">La réunion a été insérée dans la base de données, il faut attendre la validation de la présidente.</p>
				<%} %>
				
                <label for="titre">Titre de la réunion<span class="requis">*</span></label>
                <input  type="text"  id="titre" name="titre" value="" size="100" maxlength="100" required/>
                <br />
                
                <label for="sujet">Sujet de la réunion<span class="requis">*</span></label>
                <input type="text" id="sujet" name="sujet" value="" size="100" maxlength="100" required/>
                <br />
                
               	<label for="description">Description <span class="requis">*</span></label>
                <input type="text" id="description" name="description" value="" size="20" maxlength="500" required/>
               	<br />

               	<label for="lieu">Lieu<span class="requis">*</span></label>
                <input type="text" id="lieu" name="lieu" value="" size="20" maxlength="60" required/>
               	<br />
               	
               	<label for="date_debut">Date de début<span class="requis">*</span></label>
               	<input type="date" id="date_debut" name="date_debut" size="20" required>
				<br/>
                
                <label for="heure_debut">Heure de début<span class="requis">*</span></label>
                <input type="time" id="heure_debut" name="heure_debut" size="20" required>
               	<br/> 
               
                <label for="date_fin">Date de Fin<span class="requis">*</span></label>
               	<input type="date" id="date_fin" name="date_fin" size="20" required>
				<br/>
                
                <label for="heure_fin">Heure de Fin<span class="requis">*</span></label>
                <input type="time" id="heure_fin" name="heure_fin" size="20" required>
               	<br/> 
               
                              
			    <label for="type">Précisez le type de réunion <span class="requis">*</span></label>   
               	<select   name="type" id="type" required>
					<option value="">Aucun</option>
		   		 	<option value="1">Réunion des membres du Conseil d'administration</option>
		   		 	<option value="2">Réunion des membres du Bureau</option>
		   		 	<option value="3">Assemblée Générale</option>
		  		</select>      
               	<br />
              	<br />
  				<input type="submit" value="Créer Réunion" class="sansLabel" />
                <br />

            </fieldset>
            
    </form>

	
		<br/><br/><br/><br/><br/><br/><br/>
		
		<jsp:include page="/WEB-INF/footer.jsp" />
</body>
</html>