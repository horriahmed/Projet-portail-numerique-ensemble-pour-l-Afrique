<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	    <meta charset="utf-8">
	    <!--[if IE]><meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'><![endif]-->
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <meta name="description" content="">
	    <meta name="author" content="">
	    <link rel="shortcut icon" href="assets/img/ico/favicon.ico">
	    <link rel="apple-touch-icon" sizes="144x144" href="WebContent/assets/img/ico/apple-touch-icon-144x144.png">
	    <link rel="apple-touch-icon" sizes="114x114" href="WebContent/assets/img/ico/apple-touch-icon-114x114.png">
	    <link rel="apple-touch-icon" sizes="72x72" href="WebContent/assets/img/ico/apple-touch-icon-72x72.png">
	    <link rel="apple-touch-icon" href="WebContent/assets/img/ico/apple-touch-icon-57x57.png">
	
	    <title>Espace Étudiant</title>
	
	    <!-- Bootstrap Core CSS -->
	    <link href="assets/css/bootstrap.min.css" rel="stylesheet">
	    <link href="assets/css/animate.css" rel="stylesheet">
	    <link href="assets/css/plugins.css" rel="stylesheet">
	
	    <!-- Custom CSS -->
	    <link href="assets/css/style.css" rel="stylesheet">
	
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
			<section class="dark-wrapper opaqued parallax" data-parallax="scroll" data-image-src="assets/img/student.jpg" data-speed="0.7">
	            <div class="section-inner pad-top-200">
	                <div class="container">
	                    <div class="row">
	                        <div class="col-lg-12 mt30 wow text-center">
	                            <h2 class="section-heading">ESPACE D'AIDE AUX ÉTUDIANTS</h2>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </section>
        </div>
		<jsp:include page="/WEB-INF/header.jsp" />
		
        <section id="welcome">
            <div class="section-inner nopaddingbottom">

                <div class="container">
                    <div class="row">
                        <div class="col-md-6">   
                        	<br/>                     	
                            <p class="lead">Nous avons la volonté de donner les références les plus utiles aux étudiants africains dans le cadre de leur accueil en région parisienne.  </p>
							<br/>                           
                            <p> Site de la Cité Universitaire Universitaire de Paris : <a href="https://access.ciup.fr"> <strong>https://access.ciup.fr</strong></a> </p> 
                        	<br/><br/>
                        	<p> Site de la Maison des Associations : <a href="https://www.maisonsdesassociations.fr/"> <strong>https://www.maisonsdesassociations.fr/</strong></a> </p> 
                         	<br/><br/>
                        	<p> Site de L'Observatoire national de la vie étudiante : <a href="http://www.ove-national.education.fr/"> <strong>http://www.ove-national.education.fr/</strong></a> </p> 
                         
                         </div>
                         
                         

                        <div class="col-md-6">
                            <img src="assets/img/paris1.jpg" class="img-responsive alignright wow fadeIn" data-wow-delay="0.5s" alt="">
                        </div>
                    </div>
                    <br/><br/>
                     <div class="row">
                     <div class="col-md-6">
                            <img src="assets/img/Arc-de-Triomphe.jpg" class="img-responsive alignright wow fadeIn" data-wow-delay="0.5s" alt="">
                        </div>
                     	
                        <div class="col-md-6">    
                        	<br/><br/>                    	
                            <p class="lead">Ces références permettront aux étudiants nouvellement arrivés de s'orienter et de trouver des solutions à leurs éventuels problèmes</p>
							<br/>                           
                            <p> Rubrique Complémentaire Santé & Mutuelle du site etudiant.gouv : <a href="https://www.etudiant.gouv.fr/cid114047/complementaire-sante-mutuelles.html"> <strong>https://www.etudiant.gouv.fr/cid114047/complementaire-sante-mutuelles.html</strong></a> </p> 
                        	<br/><br/>
                        	<p> Rubrique Vie Associative & Assos Étudiantes du site etudiant.gouv : <a href="https://www.etudiant.gouv.fr/pid35087/vie-associative-les-assos-etudiantes.html"> <strong>https://www.etudiant.gouv.fr/pid35087/vie-associative-les-assos-etudiantes.html</strong></a> </p> 
                         	<br/><br/>
                        	<p> Site du CROUS de Paris : <a href="https://www.crous-paris.fr/"> <strong>https://www.crous-paris.fr/</strong></a> </p> 
                         
                         </div>
                         
                         

                        
                    </div>
                    <br/><br/><br/><br/>
                    
                </div>

            </div>
        </section>

		<br/><br/><br/>
	<jsp:include page="/WEB-INF/footer.jsp" />
	    

	</body>
</html>