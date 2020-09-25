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
<%@page import="modele.services.ForumS.Commentaire"%>
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
	<iframe width="0" height="0" border="0" name="dummyframe"
		id="dummyframe"></iframe>
<script>


function update() {
	
	var millisecondsToWait = 1500;
	setTimeout(function() {
		document.location.reload(true);
	   
	}, millisecondsToWait);
	

	    
	}
	
</script>
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
	<%
	  Sujet sjt = (Sujet) request.getAttribute("sjt");
	ArrayList<Commentaire> comm = (ArrayList<Commentaire>) request.getAttribute("commentaire") ;
	%>
		  <section class="mb-30px">
    <div class="container">
      <div class="hero-banner hero-banner--sm"
      					style="background-image:url('http://localhost/Images/<%out.print(sjt.getLinkTheme());%>'); background-size: cover;">
      
        <div class="hero-banner__content">
          <h1><% out.print(sjt.getM_nomSujet()); %></h1>
          <nav aria-label="breadcrumb" class="banner-breadcrumb">
            <ol class="breadcrumb">
              <li class="breadcrumb-item"><a href="http://localhost:8080/EnsemblePourAfrique/sujet?id=<%  out.print(sjt.getM_idTheme()) ;%>">Retour</a></li>
              <li class="breadcrumb-item active" aria-current="page"><% out.print(sjt.getNomTheme()) ;  %></li>
            </ol>
          </nav>
        </div>
      </div>
    </div>
  </section>

<section class="blog-post-area section-margin">
    <div class="container">
      <div class="row">
        <div class="col-lg-8">
            <div class="main_blog_details">
            <img class="img-fluid" src="http://localhost/Images/<%out.print(sjt.getLink());%>" alt="">
            <h4><% out.print(sjt.getM_nomSujet());%></h4>
            <p><%out.print(sjt.getContenu()) ;%></p>
            </div>
             <div class="comments-area">
                    <h4><%out.print(sjt.getNbCommentaire()) ;DateFormat mediumDateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM); %> Commentaires</h4>
				    <div class="comment-list">
				    <%
				    for(Commentaire e : comm){
				    	out.print("<div class=\"single-comment justify-content-between d-flex\"> <div class=\"user justify-content-between d-flex\"><div class=\"thumb\"><img src=\"http://localhost/Images/" + e.getImage() + "\" alt=\"\"></div><div class=\"desc\"><h5>"+ e.getMail() +"</h5><p class=\"date\">" + mediumDateFormat.format(e.getData()) + "</p><p class=\"comment\">"  ) ;
				    	int res = 0 ; 
				    	for(String r : e.getCommentaire().split(" ")){
				    		if(res>10){
				    			out.print("</p><p class=\"comment\">");
				    			res=0;
				    		}else{
				    			res++;
				    			out.print(" "+ r);
				    			
				    		}
				    		
				    	}
				    	out.print("</p>");
				    	
				    	for(String z : e.getFichier()){
				    		out.print("<a href=\"http://localhost/Images/" + z+ "\" download=\"" +z +"\"><i class=\"fas fa-file\">"+ z+"</i></a>                ") ; 
				    	}
				    	out.print("</div></div></div>");
				    	
				    }
				    
				    
				    %>
				    </div></div>
				 <div class="comment-form">
                    <h4>Réagir</h4>
                    <form method="post" action="RefreshCommentaireFichier?idSujet=<% out.print(sjt.getM_idSujet());%>&idTheme=<% out.print(sjt.getM_idTheme()); %>" enctype="multipart/form-data" target="dummyframe">
					<div class="form-group">
                            <textarea class="form-control mb-10" rows="5" name="msg" id="msg" placeholder="Messege" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Messege'" required=""></textarea>
                            <input type="file" name="img" multiple="multiple"/>
                        </div>
                        <%out.print("<input type=\"submit\" class=\"button submit_btn\" onclick=\"update()\">"); %><!--  onclick=\"update(\'idSujet=" + sjt.getM_idSujet() + "&idTheme="  +sjt.getM_idTheme()+"\')\"-->
                        
                        
                    </form>
                </div>
        </div>
        
         <div class="col-lg-4 sidebar-widgets">
              <div class="widget-wrap">
              <div class="single-sidebar-widget post-category-widget"><h4 class="single-sidebar-widget__title">Thèmes</h4>
              <%
              ArrayList<Theme> mesTheme = (ArrayList<Theme>) request.getAttribute("themeUser") ;
              for(Theme un : mesTheme){
            	  out.print("<ul class=\"cat-list mt-20\"><li><a href=\"http://localhost:8080/EnsemblePourAfrique/sujet?id=" + un.getId()+"\" class=\"d-flex justify-content-between\"><p>" + un.getNom() + "</p></a></li></ul>") ; 
              }
              
              
              %>
	</div></div></div></div></section>
          
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