<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<title>Liste des Réunions</title>
 <link href="assets/css/bootstrap.min.css" rel="stylesheet">
	    <link href="assets/css/animate.css" rel="stylesheet">
	    <link href="assets/css/plugins.css" rel="stylesheet">
	
	    <!-- Custom CSS -->
	    <link href="assets/css/listReunion.css" rel="stylesheet">
	
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
	<section class="dark-wrapper opaqued parallax" data-parallax="scroll" data-image-src="assets/img/reunion.jpg" data-speed="0.7">
            <div class="section-inner pad-top-200">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12 mt30 wow text-center">
                            <h2 class="section-heading">Réunions de l'association</h2>
                        </div>
                    </div>
                </div>
            </div>
     </section>
	</div>
	<jsp:include page="/WEB-INF/header.jsp" />
	
	<br/><br/>
	<%if(request.getAttribute("list") != null  ){%>
			<legend class="text-center">Liste des réunions</legend>
			<table class="table table-striped">
					<tr>
				      <th class="text-center">Titre de la réunion</th>
				      <th class="text-center">Type de réunion</th>
				      <th class="text-center">Sujet</th>
				      <th class="text-center">Date et horraires de début</th>
				      <th class="text-center">Date et horraires de fin</th>
				      <th class="text-center">Adresse</th>
				      <th class="text-center">Description</th>
				    </tr>
			    <c:forEach items="${list}" var="reunion">
			    <tr>
					<td><c:out value="${reunion.titre}" /></td>
					<td><c:out value="${reunion.type}" /></td>
					<td><c:out value="${reunion.sujet}" /></td>
					<td><c:out value="${reunion.date_debut}" /></td>
					<td><c:out value="${reunion.date_fin}" /></td>
					<td><c:out value="${reunion.lieu}" /></td>
					<td><c:out value="${reunion.description}" /></td>
				</tr>

				  </c:forEach>
			</table>
		<%} %>
		
		<br/><br/><br/><br/><br/><br/><br/>
		
		<jsp:include page="/WEB-INF/footer.jsp" />
</body>
</html>