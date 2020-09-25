<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.ArrayList"%>
<%@page import="modele.services.ForumS.Sujet"%>
<%@page import="modele.services.ForumS.Utilisateur"%>


<!DOCTYPE html>
<html>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	 <link href="assets/css/bootstrap.min.css" rel="stylesheet">
	    <link href="assets/css/animate.css" rel="stylesheet">
	    <link href="assets/css/plugins.css" rel="stylesheet">
	
	    <!-- Custom CSS -->
	    <link href="assets/css/discussion.css" rel="stylesheet">
	
	    <!-- Custom Fonts -->
	    <link href="assets/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	    <link href="assets/css/pe-icons.css" rel="stylesheet">
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<head>
<meta charset="UTF-8">
<title>Forum</title>
</head>
<body>
	<div id="menu">
		 <jsp:include page="/WEB-INF/header.jsp" /> <br/><br/><br/><br/>
		</div>
<ul id="listeSujets">

<%
HttpSession a = request.getSession();
Utilisateur mail = (Utilisateur) a.getAttribute("user");


if (mail != null) {
ArrayList<Sujet> mesSujet = (ArrayList<Sujet>)request.getAttribute("sujet"); 
for(Sujet e : mesSujet){
	out.println("<a href=\"/EnsemblePourAfrique/discussion?idSujet=" + e.getM_idSujet() + "&idTheme=" + e.getM_idTheme() + "\">" +e.getM_nomSujet() + "</li><br>") ; 
}
}else{
	out.println(
			"<div class=\"alert alert-danger\" role=\"alert\"> <strong>You've to be logged !</strong> <a href=\"/EnsemblePourAfrique/login\" class=\"alert-link\">Click here to be logged</a></div>");
}

%>
</ul>
</body>
</html>