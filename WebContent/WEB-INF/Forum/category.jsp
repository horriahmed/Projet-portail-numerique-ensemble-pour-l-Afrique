<%@page import="java.util.ArrayList"%>
<%@page import="modele.services.ForumS.Theme"%>
<%@page import="modele.services.ForumS.Utilisateur"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	 <link href="assets/css/bootstrap.min.css" rel="stylesheet">
	    <link href="assets/css/animate.css" rel="stylesheet">
	    <link href="assets/css/plugins.css" rel="stylesheet">
	
	    <!-- Custom CSS -->
	    <link href="assets/css/category.css" rel="stylesheet">
	
	    <!-- Custom Fonts -->
	    <link href="assets/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	    <link href="assets/css/pe-icons.css" rel="stylesheet">
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link href="https://cdn.jsdelivr.net/gh/gitbrent/bootstrap-switch-button@1.1.0/css/bootstrap-switch-button.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/gh/gitbrent/bootstrap-switch-button@1.1.0/dist/bootstrap-switch-button.min.js"></script>
<head>
<meta charset="UTF-8">
<title>Category</title>

</head>
<body>
		<div id="menu">
		 <jsp:include page="/WEB-INF/header.jsp" /> <br/><br/><br/><br/>
		</div>
<script>
function update(id) {
	
	var url = "http://localhost:8080/EnsemblePourAfrique/refresh";
	var params = "identifiant="+ id;
	var http = new XMLHttpRequest();
	http.open("GET", url+"?"+params, true);
	http.send(null);
	http.onreadystatechange = function()
	{
	    if(http.readyState == 4 && http.status == 200) {
	    	document.location.reload(true);
	    }
	}
	}
</script>	
	<div id ="listeThemes">
	<ul>
		<%
			HttpSession a = request.getSession();
		Utilisateur mail = (Utilisateur) a.getAttribute("user");
		if (mail != null) {
			ArrayList<Theme> mesThemes = (ArrayList<Theme>) request.getAttribute("theme");

			for (Theme e : mesThemes) {
				if (e.getSub()) {
					out.println("<center><table border=5 width=600 height=300>  <tr> <td><img src=\"http://93.22.122.96:280/Images/" + e.getLink() +"\"width=\"600\" height=\"300\"></td></tr><tr><td>"+ "<a href=\"/EnsemblePourAfrique/sujet?id=" + e.getId() + "\">" +e.getNom() + "</a></td></tr><tr><td><input type=\"checkbox\" checked onchange=\"update( " + e.getId() +")\" data-toggle=\"switchbutton\" data-size=\"small\"  data-onlabel=\"Actif\" data-offlabel=\"Inactif\" data-onstyle=\"success\" data-offstyle=\"danger\"  data-style=\"fast\" ></td></tr></table></center> <br>") ; 			
					}
				else{
					out.println("<center><table border=5 width=600 height=300>  <tr> <td><img src=\"http://93.22.122.96:280/Images/" + e.getLink() +"\"width=\"600\" height=\"300\" runat=\"server\"></td></tr><tr><td>"  +e.getNom() + "</a></td></tr><tr><td><input type=\"checkbox\"  onchange=\"update( " + e.getId() +")\" data-toggle=\"switchbutton\" data-size=\"small\"  data-onlabel=\"Actif\" data-offlabel=\"Inactif\" data-onstyle=\"success\" data-offstyle=\"danger\"  data-style=\"fast\" ></td></tr></table></center> <br>") ; 			
					
				
			}

			}
			}

		 else {

			out.println(
			"</div><div class=\"alert\" role=\"alert\"> <strong>Vous n'êtes pas connecté !</strong> <a href=\"/EnsemblePourAfrique/login\" class=\"alert-link\">Cliquez ici pour vous connecter</a></div>");

		}
		%>

	</ul>

</body>
</html>