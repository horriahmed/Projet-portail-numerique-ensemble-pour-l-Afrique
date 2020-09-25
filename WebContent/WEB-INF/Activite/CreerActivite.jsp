<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
		<title>Créer une Activité</title>
		<link href="assets/css/bootstrap.min.css" rel="stylesheet">
	    <link href="assets/css/animate.css" rel="stylesheet">
	    <link href="assets/css/plugins.css" rel="stylesheet">
	
	    <!-- Custom CSS -->
<!-- 	    <link href="assets/css/listUtilisateurAdministrateur.css" rel="stylesheet"> -->
	
	    <!-- Custom Fonts -->
	    <link href="assets/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	    <link href="assets/css/pe-icons.css" rel="stylesheet">
	    
	    <!-- Custom CSS -->
	    <link href="assets/css/creerContenu.css" rel="stylesheet">
	    

</head>
<body>
	<div id="menu">	 
	<section class="dark-wrapper opaqued parallax" data-parallax="scroll" data-image-src="assets/img/event.jpg" data-speed="0.7">
            <div class="section-inner pad-top-200">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12 mt30 wow text-center">
                            <h2 class="section-heading">Création de contenu automatisé</h2>
                        </div>
                    </div>
                </div>
            </div>
     </section>
	</div>
	<jsp:include page="/WEB-INF/header.jsp" />
	<br/><br/>


	<iframe width="0" height="0" border="0" name="dummyframe"
		id="dummyframe"></iframe>

<!-- Formulaire -->
	<form method="post" action="RefreshActivite" enctype="multipart/form-data" target="dummyframe" >
	
		
           <fieldset>
                <legend>Veuillez remplir les champs ci-dessous</legend>
                
                <%if((Integer)request.getAttribute("state")!= null 
					&& (Integer)request.getAttribute("state")== 1) {%> 
					<p> l'activité a été inséré dans la base de données. Vous pouvez la consulter dans la rubrique Liste des Activités ou sur la page principale. </p>
				<%} %>
				
                <label for="sujet">Sujet du contenu <span class="requis">*</span></label>
                <input type="text" id="sujet" name="sujet" value="" size="100" maxlength="100" required/>
                <br /><br />
                
                <label for="type">Type d'activité <span class="requis">*</span></label>
               	<input type="text" id="type" name="type" value="" size="20" maxlength="60" required/>
               	<br /><br />
               	
               	<label for="lieu">Lieu <span class="requis">*</span></label>
                <input type="text" id="lieu" name="lieu" value="" size="20" maxlength="60" required/>
               	<br /><br />
                
                <label for="titre">Titre Principale<span class="requis">*</span></label>
                <input type="text" id="titre" name="titre" value="" size="100" maxlength="100" required/>
                <br /><br />
                
                <label for="sous-titre1">Sous titre 1 <span class="requis">*</span></label>
                <input type="text" id="sous-titre1" name="sous-titre1" value="" size="70" maxlength="70" required/>
                <br />
                
                <label for="description-1">Paragraphe 1 <span class="requis">*</span><textarea id="description-1" name="description-1" rows="20" cols="60" required></textarea></label>
               	<br /> <br />
                
                <label for="sous-titre2">Sous titre 2 </label>
                <input type="text" id="sous-titre2" name="sous-titre2" value="" size="70" maxlength="70" />
                <br /> 
                
                <label for="description-2">Paragraphe 2 <textarea id="description-2" name="description-2" rows="20" cols="60"></textarea> </label>
				<br /><br />
                
                <label for="sous-titre3">Sous titre 3:</label>
                <input type="text" id="sous-titre3" name="sous-titre3" value="" size="70" maxlength="70" />
                <br />
                
                <label for="description-3">Paragraphe 3 <textarea id="description-3" name="description-3" rows="20" cols="60"></textarea></label>
               	<br /><br />
                
              
	           	<fieldset>
		                <legend>Lien :</legend>
		                <label for="titre_lien">Titre:<span class="requis">*</span></label>
		                <input type="text" id="titre_lien" name="titre_lien" value="" size="100" maxlength="100" />
		                <br />
		                <label for="url">URL:<span class="requis">*</span></label>
		                <input type="text" id="url" name="url" value="" size="100" maxlength="100" />
		                <br /><br />
	             </fieldset>
                
                
                <fieldset>
				    <legend>Date de l'évenement :</legend>
					 
					<label for="date_debut">Date de début</label>
	               	<input type="date" id="date_debut" name="date_debut" size="20">
					
	                
	                <label for="heure_debut">Heure de début</label>
	                <input type="time" id="heure_debut" name="heure_debut" size="20">
	               	
               	              
	                <label for="date_fin">Date de Fin</label>
	               	<input type="date" id="date_fin" name="date_fin" size="20">
					
	                
	                <label for="heure_fin">Heure de Fin</label>
	                <input type="time" id="heure_fin" name="heure_fin" size="20">
	               	
                </fieldset>
                
				<br/>
				
				<fieldset>
					<legend>Insertion d'image :</legend>
					
					<label for="image">Insérer image :</label>
					<input type="file" name="image" accept="image/*" size="20" />
			   </fieldset>	
				<br />
			

  				<input type="submit" value="créer Activité" class="sansLabel" />
                <br />
            	

            </fieldset>
            
    </form>
    
    <br/><br/><br/><br/><br/><br/><br/>
		
	<jsp:include page="/WEB-INF/footer.jsp" />
</body>
</html>