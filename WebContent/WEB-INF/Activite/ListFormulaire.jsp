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
	<section class="dark-wrapper opaqued parallax" data-parallax="scroll" data-image-src="assets/img/forms.jpg" data-speed="0.7">
            <div class="section-inner pad-top-200">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12 mt30 wow text-center">
                            <h2 class="section-heading">Formulaires Associatifs</h2>
                        </div>
                    </div>
                </div>
            </div>
     </section>
	</div>
	<jsp:include page="/WEB-INF/header.jsp" />
	<br/><br/>
		<table class="table table-striped">
					<tr>
				      <th class="text-center">Titre</th>
				    </tr>
			    <tr>
					<td class="text-center">Appel à cotisation</td>
					<td class="text-center"><a class="stylebouton" href="downloadFormulaire/Appel_a_cotisation.pdf"><b>Télécharger le formulaire</font></b></a></td> 
				  </tr>
				<tr>
					<td class="text-center">Budget prévisionnel</td>
					<td class="text-center"><a class="stylebouton" href="downloadFormulaire/Budget_provisionnel.pdf"><b>Télécharger le formulaire</font></b></a></td> 
				  </tr>
				<tr>
					<td class="text-center">Bulletin d'adhésion</td>
					<td class="text-center"><a class="stylebouton" href="downloadFormulaire/Bulletins_DAdhesion.pdf"><b>Télécharger le formulaire</font></b></a></td> 
				  </tr>
				<tr>
					<td class="text-center">Convocation à l'assemblée générale</td>
					<td class="text-center"><a class="stylebouton" href="downloadFormulaire/Convocation_a_l'assemblee_pouvoir.pdf"><b>Télécharger le formulaire</font></b></a></td> 
				  </tr>

				<tr>
					<td class="text-center">Déclaration des modifications des statuts</td>
					<td class="text-center"><a class="stylebouton" href="downloadFormulaire/Declaration_Modif_Statuts.pdf"><b>Télécharger le formulaire</font></b></a></td> 
				  </tr>
				  
				<tr>
					<td class="text-center">Lettre de demande d'ouverture d'un compte bancaire</td>
					<td class="text-center"><a class="stylebouton" href="downloadFormulaire/Demande_ ouverture_compte_bancaire.pdf"><b>Télécharger le formulaire</font></b></a></td> 
				  </tr>
				  
				<tr>
					<td class="text-center">Lettre de demande d'un devis d'assurance</td>
					<td class="text-center"><a class="stylebouton" href="downloadFormulaire/Demande_de_devis.pdf"><b>Télécharger le formulaire</font></b></a></td> 
				  </tr>
				  
				<tr>
					<td class="text-center">Demande de mise à disposition de locaux municipaux</td>
					<td class="text-center"><a class="stylebouton" href="downloadFormulaire/Demande_de_locaux.pdf"><b>Télécharger le formulaire</font></b></a></td> 
				  </tr>
				
				<tr>
					<td class="text-center">Demande de subvention</td>
					<td class="text-center"><a class="stylebouton" href="downloadFormulaire/Demande_de_subvention.pdf"><b>Télécharger le formulaire</font></b></a></td> 
				  </tr>
				  
				<tr>
					<td class="text-center">Fiche de remboursement de frais</td>
					<td class="text-center"><a class="stylebouton" href="downloadFormulaire/Fiche_Remboursements_frais.pdf"><b>Télécharger le formulaire</font></b></a></td> 
				  </tr>
				  
				<tr>
					<td class="text-center">Procès-verbal d'assemblée générale</td>
					<td class="text-center"><a class="stylebouton" href="downloadFormulaire/Proces_Verbal_AG.pdf"><b>Télécharger le formulaire</font></b></a></td> 
				  </tr>
				  
				<tr>
					<td class="text-center">Reçu dons aux oeuvres</td>
					<td class="text-center"><a class="stylebouton" href="downloadFormulaire/Recu_Dons_Aux_Oeuvres.pdf"><b>Télécharger le formulaire</font></b></a></td> 
				  </tr>
			</table>
		
	
		<br/><br/><br/><br/><br/><br/><br/>
		
		<jsp:include page="/WEB-INF/footer.jsp" />
	</body>
</html>