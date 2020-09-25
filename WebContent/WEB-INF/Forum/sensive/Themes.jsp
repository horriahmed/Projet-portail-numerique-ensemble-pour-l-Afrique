<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="modele.services.ForumS.Sujet"%>
<%@page import="modele.services.ForumS.Utilisateur"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.time.temporal.ChronoUnit"%>
<%@page import="java.time.LocalDate"%>
<%@page import="modele.services.ForumS.Theme"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link href="assets/css/bootstrap.min.css" rel="stylesheet">
<link href="assets/css/animate.css" rel="stylesheet">
<link href="assets/css/plugins.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="assets/css/category.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="assets/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link href="assets/css/pe-icons.css" rel="stylesheet">
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/gh/gitbrent/bootstrap-switch-button@1.1.0/css/bootstrap-switch-button.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/gh/gitbrent/bootstrap-switch-button@1.1.0/dist/bootstrap-switch-button.min.js"></script>
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
	<script>
		function update(id) {

			var url = "http://localhost:8080/EnsemblePourAfrique/refresh";
			var params = "identifiant=" + id;
			var http = new XMLHttpRequest();
			http.open("GET", url + "?" + params, true);
			http.send(null);
			http.onreadystatechange = function() {
				if (http.readyState == 4 && http.status == 200) {
					document.location.reload(true);
				}
			}
		}
	</script>
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
							<li class="nav-item "><a class="nav-link" href="HomeForum">Acceuil</a></li>
							<li class="nav-item active"><a class="nav-link"
								href="category">Abonnements</a></li>
							<!--<li class="nav-item submenu dropdown">
                <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                  aria-expanded="false">Pages</a>
                <ul class="dropdown-menu">
                  <li class="nav-item"><a class="nav-link" href="blog-details.html">Blog Details</a></li>
                </ul>
              </li>-->
                            				<li class="nav-item"><a class="nav-link" href="EditProfile">Editer Profil</a></li>
              
							<li class="nav-item"><a class="nav-link" href="HomePage">Retour vers le site</a></li>

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

	<section class="mb-30px">
		<div class="container">
			<div class="hero-banner hero-banner--sm"
			style="background-image:url('http://localhost/Images/thm.png'); background-size: cover;">
				<div class="hero-banner__content">
					<h1>Thèmes</h1>
					<nav aria-label="breadcrumb" class="banner-breadcrumb">
						
					</nav>
				</div>
			</div>
		</div>
	</section>

	<section class="blog-post-area section-margin">
		<div class="container">
			<div class="row">
				<div class="col-lg-8">
					<div class="row">
						<%
							HttpSession a = request.getSession();
						Utilisateur mail = (Utilisateur) a.getAttribute("user");

						ArrayList<Theme> mesThemes = (ArrayList<Theme>) request.getAttribute("theme");
						for (Theme e : mesThemes) {

							if (e.getSub()) {
								out.print(
								"<div class=\"col-md-6\"><div class=\"single-recent-blog-post card-view\"> <div class=\"thumb\"> <img class=\"card-img rounded-0\" src=\"http://localhost/Images/"
										+ e.getLink()
										+ "\" alt=\"\"></div> <div class=\"details mt-20\"><a href=\"http://localhost:8080/EnsemblePourAfrique/sujet?id=" + e.getId()+"\"><h3>"
										+ e.getNom() + "</h3></a></br><input type=\"checkbox\" checked onchange=\"update( " + e.getId()
										+ ")\" data-toggle=\"switchbutton\" data-size=\"small\"  data-onlabel=\"Actif\" data-offlabel=\"Inactif\" data-onstyle=\"success\" data-offstyle=\"danger\"  data-style=\"fast\" ></div></div></div>");

							} else {
								out.print(
								"<div class=\"col-md-6\"><div class=\"single-recent-blog-post card-view\"> <div class=\"thumb\"> <img class=\"card-img rounded-0\" src=\"http://localhost/Images/"
										+ e.getLink() + "\" alt=\"\"></div> <div class=\"details mt-20\"><h3>" + e.getNom()
										+ "</h3></a></br><input type=\"checkbox\"  onchange=\"update( " + e.getId()
										+ ")\" data-toggle=\"switchbutton\" data-size=\"small\"  data-onlabel=\"Actif\" data-offlabel=\"Inactif\" data-onstyle=\"success\" data-offstyle=\"danger\"  data-style=\"fast\" ></div></div></div>");

							}
						}
						%>
					</div>
				</div>
				<div class="col-lg-4 sidebar-widgets">
					<div class="widget-wrap">
						<div class="single-sidebar-widget post-category-widget">
							<h4 class="single-sidebar-widget__title">Thèmes</h4>

							<%
								ArrayList<Theme> mesTheme = (ArrayList<Theme>) request.getAttribute("theme");
							for (Theme un : mesTheme) {
								if (un.getSub()) {
									out.print("<ul class=\"cat-list mt-20\"><li><a href=\"http://localhost:8080/EnsemblePourAfrique/sujet?id=" + un.getId()+ "\"class=\"d-flex justify-content-between\"><p>"
									+ un.getNom() + "</p></a></li></ul>");

								}
							}
							%>


						</div>
					</div>







				</div>
			</div></div>
	</section>



















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
</html>