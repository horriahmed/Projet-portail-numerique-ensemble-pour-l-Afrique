<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="modele.services.ForumS.Utilisateur"%>
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
	
	    <title>Ensemble pour l'Afrique</title>
	
	    <!-- Bootstrap Core CSS -->
	    <link href="assets/css/bootstrap.min.css" rel="stylesheet">
	    <link href="assets/css/animate.css" rel="stylesheet">
	    <link href="assets/css/plugins.css" rel="stylesheet">
	
	    <!-- Custom CSS -->
	    <link href="assets/css/style.css" rel="stylesheet">
	
	    <!-- Custom Fonts -->
	    <link href="assets/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	    <link href="assets/css/pe-icons.css" rel="stylesheet">
	</head>
	<body>
		<%
		HttpSession a = request.getSession();
		Utilisateur user = (Utilisateur) a.getAttribute("user");
		List<Integer> roles = null;
		if(user != null){
			roles = user.getRoles();
		}
	
		%>
	
		<!-- Header -->
	    <header id="headerwrap" class="dark-wrapper backstretched special-max-height no-overlay">
	         <div class="container vertical-center">
	         	<div class="intro-text vertical-center text-left smoothie">
	         		<div class="intro-heading wow fadeIn heading-font" data-wow-delay="0.2s">Ensemble Pour l'Afrique</div>
	         		<% if(user == null){ %>
	         		<div class="intro-sub-heading wow fadeIn secondary-font" data-wow-delay="0.4s"> Agir ensemble pour l'Afrique est <span class="rotate">notre ambition, notre aspiration, notre objectif de vie</span></div>
	         		<%} else{%>
	         		<div class="intro-sub-heading wow fadeIn secondary-font" data-wow-delay="0.4s"> Bonjour <% out.print(user.getPrenom() + " "); out.println(user.getNom().toUpperCase()); %> </div>
	         		<div class="intro-sub-heading wow fadeIn secondary-font" data-wow-delay="0.4s"> bienvenue dans l'espace : <% out.print(user.roleHomePage().toString()); %>.</div>
	         		<%} %>
	         	</div>
	          </div>
	    </header>
	    <jsp:include page="/WEB-INF/header.jsp" />
	    

	    <%if(request.getAttribute("list") != null){ %>
	    <section class="white-bg">
            <div class="section-inner masonry-portfolio container demo-selector" data-masonry-cols="4">
                <div>
                    <div>
                        <ul class="masonry-portfolio-items isotope list-unstyled">
                        <c:forEach items="${list}" var="activite">
	                            <li class="col-sm-3 col-xs-6 masonry-portfolio-item apps isotope-item mb30">
	                                <div class="hover-item">
	                                    <img src="Images/${activite.image_link}" class="img-responsive smoothie" alt="">
	                                    <div class="overlay-item-caption smoothie"></div>
	                                    <div class="hover-item-caption smoothie">
	                                        <div class="vertical-center smoothie">
	                                            <h3 class="smoothie mb30"><a href="" title="view project"><c:out value="${activite.titre}" /></a></h3>
	                                            <a href="/EnsemblePourAfrique/article?idactivite=${activite.idactivite}" class="smoothie btn btn-primary">En savoir plus</a>
	                                        </div>
	                                    </div>
	                                </div>
	                            </li>
                            </c:forEach>
                         </ul>
                    </div>
                  </div>
               </div>
          </section>
          <% }%>
        <jsp:include page="/WEB-INF/footer.jsp" />
		<script src="assets/js/jquery.js"></script>
	    <script src="assets/js/bootstrap.min.js"></script>
	    <script src="assets/js/plugins.js"></script>
	    <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
	    <script src="assets/js/init.js"></script>
	
	    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	    <!--[if lt IE 9]>
	        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
	    <![endif]-->
	
	    <script type="text/javascript">
	    $(document).ready(function() {
	        'use strict';
	        jQuery('#headerwrap').backstretch([
	            "assets/img/bg/bg1.jpg",
	            "assets/img/bg/bg2.jpg",
	            "assets/img/bg/bg3.jpg"
	        ], {
	            duration: 8000,
	            fade: 500
	        });
	    });
	    </script>

	</body>
</html>