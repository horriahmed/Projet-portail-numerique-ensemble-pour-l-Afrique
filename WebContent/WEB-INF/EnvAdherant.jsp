<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	
	    <title>Adhérant</title>
	
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
		<!-- Header -->
	    <header id="headerwrap" class="dark-wrapper backstretched special-max-height no-overlay">
	         <div class="container vertical-center">
	         	<div class="intro-text vertical-center text-left smoothie">
	         		<div class="intro-heading wow fadeIn heading-font" data-wow-delay="0.2s">Ensemble Pour l'Afrique</div>
	         		<div class="intro-sub-heading wow fadeIn secondary-font" data-wow-delay="0.4s"> Agir ensemble pour l'Afrique est <span class="rotate">notre ambition, notre aspriration, notre objectif de vie</span></div>
	         	</div>
	          </div>
	    </header>
	    <jsp:include page="/WEB-INF/header.jsp" />
	    
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