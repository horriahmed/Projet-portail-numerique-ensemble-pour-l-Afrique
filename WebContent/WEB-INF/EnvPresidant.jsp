<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>President</title>
	 <link href="assets/css/bootstrap.min.css" rel="stylesheet">
	    <link href="assets/css/animate.css" rel="stylesheet">
	    <link href="assets/css/plugins.css" rel="stylesheet">
	
	    <!-- Custom CSS -->
	    <link href="assets/css/envPresident.css" rel="stylesheet">
	
	    <!-- Custom Fonts -->
	    <link href="assets/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	    <link href="assets/css/pe-icons.css" rel="stylesheet">
</head>
<body>
	<div id="menu">
		 <jsp:include page="/WEB-INF/header.jsp" /> <br/><br/><br/><br/>
	</div>
<ul>

	<li>  <a href="http://localhost:8080/EnsemblePourAfrique/listUtilisateur">Voir la liste des utilisateurs</a>  </li>
	<li id="listeContenu">  <a href="http://localhost:8080/EnsemblePourAfrique/listReunion">Consulter les réunions.</a>  </li>
	<li id="listeContenu">  <a href="http://localhost:8080/EnsemblePourAfrique/createDocument?role=1">Rédiger une lettre d'informations</a>  </li>
</ul>


</body>
</html>