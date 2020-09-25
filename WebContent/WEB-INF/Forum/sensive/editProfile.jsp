<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="modele.services.ForumS.Utilisateur"%>
    
<!DOCTYPE html>
<html>
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
<body>

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
							<li class="nav-item"><a class="nav-link"
								href="HomeForum">Acceuil</a></li>
							<li class="nav-item"><a class="nav-link" href="category">Abonnements</a></li>
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
			style="background-image:url('http://localhost/Images/edition.png'); background-size: cover;">
				<div class="hero-banner__content">
					<h1>Edition</h1>
					<nav aria-label="breadcrumb" class="banner-breadcrumb">
						
					</nav>
				</div>
			</div>
		</div>
	</section>
<section class="section-margin--small section-margin">
    <div class="container">
    
<%
			HttpSession a = request.getSession();
		Utilisateur mail = (Utilisateur) a.getAttribute("user");%>

      <div class="row">
        <div class="col-md-8 col-lg-9">
          <form action="updateProfile" class="form-contact contact_form"  method="post" enctype="multipart/form-data">
            <div class="row">
              <div class="col-lg-5">
                <div class="form-group">
                  <input class="form-control" name="name" id="name" type="text" placeholder="Pseudo">
                </div>
                 <div class="form-group">
                 <p>Modifier Avatar</p>
                 <img src="http://localhost/Images/<% out.print(mail.getAvatar()) ; %>" alt="">
                </div>
                <div class="form-group">
                 <input class="form-control" name="img" id="img" type="file"  >
                </div>
                <div class="form-group text-center text-md-right mt-3">
              <button type="submit" class="button button--active button-contactForm">Modifier</button>
            </div>
               
              </div>
              
            </div>
            
          </form>
        </div>
      </div>
    </div>
  </section>
	<!-- ================ contact section end ================= -->

  


  <!--================ Start Footer Area =================-->
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
  <script src="assets/sensitive/vendors/bootstrap/bootstrap.bundle.min.js"></script>
  <script src="assets/sensitive/vendors/owl-carousel/owl.carousel.min.js"></script>
  <script src="assets/sensitive/js/jquery.ajaxchimp.min.js"></script>
  <script src="assets/sensitive/js/mail-script.js"></script>
  <script src="assets/sensitive/js/main.js"></script>



</body>
<!--================Header Menu Area =================-->
</html>