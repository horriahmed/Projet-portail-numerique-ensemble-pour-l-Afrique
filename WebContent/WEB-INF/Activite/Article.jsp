<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <!--[if IE]><meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'><![endif]-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="assets/img/ico/favicon.ico">
    <link rel="apple-touch-icon" sizes="144x144" href="assets/img/ico/apple-touch-icon-144x144.png">
    <link rel="apple-touch-icon" sizes="114x114" href="assets/img/ico/apple-touch-icon-114x114.png">
    <link rel="apple-touch-icon" sizes="72x72" href="assets/img/ico/apple-touch-icon-72x72.png">
    <link rel="apple-touch-icon" href="assets/img/ico/apple-touch-icon-57x57.png">

	<% if(request.getAttribute("activite") != null){%>
	    <title><c:out value="${activite.titre}" /></title>
	<%}else{ %>
		<title>Article</title>
	<%} %>
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
    <script src="assets/js/init.js"></script>
</head>
<body>

	
	<% if(request.getAttribute("activite") != null){%>
	<!-- L'image va évoluer -->
		<section class="dark-wrapper opaqued parallax" data-parallax="scroll" data-image-src="Images/${activite.image_link}" >
            <div class="section-inner text-center">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-8 col-sm-offset-2 mt30 wow">
                            <h2 class="section-heading"><c:out value="${activite.titre}" /></h2>
                            <div class="item-metas text-muted mb30 white">
                                <span class="meta-item"><i class="pe-icon pe-7s-folder"></i> Sujet <span><c:out value="${activite.sujet}" /></span></span>
                                <span class="meta-item "><i class="pe-icon pe-7s-folder"></i> Type d'activité <span><c:out value="${activite.type}" /></span></span>
                                <span class="meta-item "><i class="pe-icon pe-7s-folder"></i> Adresse <span><c:out value="${activite.lieu}" /></span></span>
                                <span class="meta-item post-date"><i class="pe-icon pe-7s-clock"></i> Débute le <span><c:out value="${activite.date_debut}" /></span></span>
                               <span class="meta-item post-date"><i class="pe-icon pe-7s-clock"></i> Fini le <span><c:out value="${activite.date_fin}" /></span></span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <jsp:include page="/WEB-INF/header.jsp" />

        <section>
            <div class="section-inner">
                <div class="container">
                    <div class="row">
                        <div id="post-content" class="col-sm-8 col-sm-offset-2 blog-item mb60 wow">
                            <div class="row">
                                <div class="col-sm-12 single-post-content">
                                	
                                	<h6><c:out value="${activite.stitre1}" /></h6>
                                    <p> <c:out value="${activite.description1}" /></p>

									<br/> <br/>
									
									<c:if test = "${not empty activite.stitre2}">
	                                    <h6><c:out value="${activite.stitre2}" /></h6>
	                                    <p> <c:out value="${activite.description2}" /></p>
                                    </c:if>
                                    
                                    <br/> <br/>
									
									<c:if test = "${not empty activite.stitre3}">
         									 <h6><c:out value="${activite.stitre3}" /></h6>
                                    		<p> <c:out value="${activite.description3}" /></p>
									</c:if>
                                   <br/><br/>
                                   <p> Si vous voulez plus d'informations sur ce sujet, rendez-vous à l'adresse suivante : <c:out value="${activite.lien}" /></p>
                                </div>
                            </div>
                        </div>

                        
                    </div>
                </div>
            </div>
        </section>
      <%} %>
        
    <br/><br/><br/><br/><br/><br/><br/>
		
	<jsp:include page="/WEB-INF/footer.jsp" />
</body>
</html>