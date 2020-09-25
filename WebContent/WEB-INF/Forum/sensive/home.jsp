<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="modele.services.ForumS.Sujet"%>
<%@page import="modele.services.ForumS.Utilisateur"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.concurrent.TimeUnit"%>
<%@page import="java.time.LocalDate"%>
<%@page import="modele.services.ForumS.Theme"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>All4Africa-Forum</title>
<link rel="icon" href="assets/img/logo.png" type="image/png">

<link rel="stylesheet"
	href="assets/sensitive/vendors/bootstrap/bootstrap.min.css">
<link rel="stylesheet"
	href="assets/sensitive/vendors/fontawesome/css/all.min.css">
<link rel="stylesheet"
	href="assets/sensitive/vendors/themify-icons/themify-icons.css">
<link rel="stylesheet"
	href="assets/sensitive/vendors/linericon/style.css">
<link rel="stylesheet"
	href="assets/sensitive/vendors/owl-carousel/owl.theme.default.min.css">
<link rel="stylesheet"
	href="assets/sensitive/vendors/owl-carousel/owl.carousel.min.css">

<link rel="stylesheet" href="assets/sensitive/css/style.css">
</head>


<body>

	<!--================Header Menu Area =================-->
	<header class="header_area">
		<div class="main_menu">
			<nav class="navbar navbar-expand-lg navbar-light">
				<div class="container box_1620">
					<!-- Brand and toggle get grouped for better mobile display -->
					<a class="navbar-brand logo_h" href="HomeForum"><img
						src="assets/img/logo.png" alt=""
						style="width: 70px; height: 70px;"></a>
					<button class="navbar-toggler" type="button" data-toggle="collapse"
						data-target="#navbarSupportedContent"
						aria-controls="navbarSupportedContent" aria-expanded="false"
						aria-label="Toggle navigation">
						<span class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<!-- Collect the nav links, forms, and other content for toggling -->
					<div class="collapse navbar-collapse offset"
						id="navbarSupportedContent">
						<ul class="nav navbar-nav menu_nav justify-content-center">
							<li class="nav-item active"><a class="nav-link"
								href="HomeForum">Acceuil</a></li>
							<li class="nav-item"><a class="nav-link" href="category">Abonnements</a></li>
							<!--<li class="nav-item submenu dropdown">
                <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                  aria-expanded="false">Pages</a>
                <ul class="dropdown-menu">
                  <li class="nav-item"><a class="nav-link" href="blog-details.html">Blog Details</a></li>
                </ul>
              </li>-->
							<li class="nav-item"><a class="nav-link" href="EditProfile">Editer
									Profil</a></li>

							<li class="nav-item"><a class="nav-link" href="HomePage">Retour
									vers le site</a></li>

						</ul>
						<ul class="nav navbar-nav navbar-right navbar-social">
							<li><a href="#"><i class="ti-facebook"></i></a></li>
							<li><a href="#"><i class="ti-twitter-alt"></i></a></li>
							<li><a href="#"><i class="ti-instagram"></i></a></li>
						</ul>
					</div>
				</div>
			</nav>
		</div>
	</header>
	<%
		Sujet banner = (Sujet) request.getAttribute("banner");
	%>
	<main class="site-main">
		<section class="mb-30px">
			<div class="container">
				<div class="hero-banner"
					style="background-image:url('http://localhost/Images/<%out.print(banner.getLink());%>'); background-size: cover;">
					<div class="hero-banner__content">
						<h3>
							<%
								out.println(banner.getNomTheme());
							%>
						</h3>
						<h1>
							<%
								out.println("<a href=\"http://localhost:8080/EnsemblePourAfrique/discussion?idSujet=" +banner.getM_idSujet() + "&idTheme=" + banner.getM_idTheme() + "\">"+ banner.getM_nomSujet()+"</a>");
							%>
						</h1>
						<h4>
							<%
								DateFormat mediumDateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
							out.println(mediumDateFormat.format(banner.getMaDate()));
							%>
						</h4>
					</div>
				</div>
			</div>
		</section>

		<section>
			<div class="container">
				<div class="owl-carousel owl-theme blog-slider">
					<%ArrayList<Sujet> mesSujet = (ArrayList<Sujet>) request.getAttribute("sujetUtilisateur") ;

					System.out.println(mesSujet.size()) ; 
            for(Sujet un : mesSujet){
            	long diffInMillies = Math.abs(un.getMaDate().getTime() - new Date().getTime());
            	long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            	out.print("<div class=\"card blog__slide text-center\"><div class=\"blog__slide__img\"><img class=\"card-img rounded-0\" src=\"http://localhost/Images/" + un.getLink() + "\"" + "alt=\"\" ></div><div class=\"blog__slide__content\"><a class=\"blog__slide__label\" href=\"http://localhost:8080/EnsemblePourAfrique/sujet?id=" +un.getM_idTheme()  +"\">" +un.getNomTheme() + "</a><h3><a href=\"http://localhost:8080/EnsemblePourAfrique/discussion?idSujet="+ un.getM_idSujet()+ "&idTheme="+  un.getM_idTheme()+"\">" + un.getM_nomSujet() + "</a></h3><p>Depuis " +diff +" Jours</p></div></div>" ) ; 
            	
            }
            
            
            %>
				</div>
			</div>
		</section>


		<section class="blog-post-area section-margin mt-4">
			<div class="container">
				<div class="row">
					<div class="col-lg-8">
						<%
            for(Sujet un : mesSujet){
                out.print("<div class=\"single-recent-blog-post\"><div class=\"thumb\"><img class=\"img-fluid\" src=\"http://localhost/Images/" + un.getLink() + "\" alt=\"\"> <ul class=\"thumb-info\"> "+ "<li><a href=\"http://localhost:8080/EnsemblePourAfrique/discussion?idSujet=" + un.getM_idSujet() + "&idTheme=" + un.getM_idTheme() +   "\"><i class=\"ti-notepad\"></i>" + mediumDateFormat.format(un.getMaDate()) + "</a></li>" + "<li><a href=\"http://localhost:8080/EnsemblePourAfrique/discussion?idSujet=" + un.getM_idSujet() + "&idTheme=" + un.getM_idTheme() +"\"><i class=\"ti-themify-favicon\"></i>" +un.getNbCommentaire() + " Commentaires</a></li></ul></div><div  class=\"details mt-20\"> <a href=\"http://localhost:8080/EnsemblePourAfrique/discussion?idSujet=" + un.getM_idSujet() + "&idTheme=" + un.getM_idTheme() + "\"><h3>" + un.getM_nomSujet() + "</h3></a><p class=\"tag-list-inline\">Theme : <a href=\"http://localhost:8080/EnsemblePourAfrique/discussion?idSujet=" + un.getM_idSujet() + "&idTheme=" + un.getM_idTheme() +"\">" + un.getNomTheme() + "</a></p><p>");
                String[] contenu = un.getContenu().split(" ") ;
                if(contenu.length > 50){
                	for(int i=0 ; i < 50 ; i++){
                		out.print(contenu[i] + " ") ; 
                	}
                	}else{
                	out.print(un.getContenu()) ; 
                	}
                out.print("</p><a class=\"button\" href=\"http://localhost:8080/EnsemblePourAfrique/discussion?idSujet=" + un.getM_idSujet() + "&idTheme=" + un.getM_idTheme() +"\">En savoir plus ...<i class=\"ti-arrow-right\"></i></a></div></div>");
                

            	
            }
            
            
            %>

					</div>
					<div class="col-lg-4 sidebar-widgets">
						<div class="widget-wrap">
							<div class="single-sidebar-widget post-category-widget">
								<h4 class="single-sidebar-widget__title">Thèmes</h4>
								<%
              ArrayList<Theme> mesTheme = (ArrayList<Theme>) request.getAttribute("themeUser") ;
              for(Theme un : mesTheme){
            	  out.print("<ul class=\"cat-list mt-20\"><li><a href=\"http://localhost:8080/EnsemblePourAfrique/sujet?id=" + un.getId()+"\" class=\"d-flex justify-content-between\"><p>" + un.getNom() + "</p></a></li></ul>") ; 
              }
              
              
              %>

							</div>
						</div>
					</div>
		</section>
	</main>

	<footer class="footer-area section-padding">
		<div class="container">
			<div class="row">
				<div class="col-lg-3  col-md-6 col-sm-6">
					<div class="single-footer-widget">
						<h6>L'association</h6>
						<p>Elle est née d’une prise de conscience collective devant la gravité des violences survenues au Rwanda en 1994, puis en Côte d'ivoire en 1999. Pour conjurer le sentiment d'impuissance et de culpabilité ressenti en pareille circonstance, le meilleur moyen était de nous engager dans le projet de développement durable de l'Afrique.</p>
					</div>
				</div>
				<div class="col-lg-4  col-md-6 col-sm-6"></div>

				<div class="col-lg-2 col-md-6 col-sm-6">
					<div class="single-footer-widget">
						<h6>Suivez Nous !</h6>
						<div class="footer-social d-flex align-items-center">
							<a href="#"> <i class="fab fa-facebook-f"></i>
							</a> <a href="#"> <i class="fab fa-twitter"></i>
							</a> <a href="#"> <i class="fab fa-dribbble"></i>
							</a> <a href="#"> <i class="fab fa-behance"></i>
							</a>
						</div>
					</div>
				</div>
			</div>

		</div>
	</footer>
	<script src="assets/sensitive/vendors/jquery/jquery-3.2.1.min.js"></script>
	<script
		src="assets/sensitive/vendors/bootstrap/bootstrap.bundle.min.js"></script>
	<script src="assets/sensitive/vendors/owl-carousel/owl.carousel.min.js"></script>
	<script src="assets/sensitive/js/jquery.ajaxchimp.min.js"></script>
	<script src="assets/sensitive/js/mail-script.js"></script>
	<script src="assets/sensitive/js/main.js"></script>



</body>
<!--================Header Menu Area =================-->
</html>